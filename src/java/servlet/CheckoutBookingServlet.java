/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BillDTO;
import entity.BookingDTO;
import entity.EmployeeDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BillDAO;
import model.BookingDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "CheckoutBookingServlet", urlPatterns = {"/CheckoutBookingServlet"})
public class CheckoutBookingServlet extends HttpServlet {

    private static final int POINT_PER_HOUR = 5;
//    private static final String page = "GetBillDetailServlet";
    private static final String page = "checkout.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        BookingDTO booking = (BookingDTO) request.getAttribute("BOOKING");
        Timestamp checkoutTime = (Timestamp) request.getAttribute("CHECKOUTTIME");
        float price = (Float) request.getAttribute("PRICE");
        String url = page;

        try {
            if (booking == null || checkoutTime == null) {
                request.setAttribute("ERROR", "Có lỗi xảy ra. Dữ liệu không đủ, vui lòng thử lại.");
                return;
            }

            booking.setCheckoutTime(checkoutTime);

            long time = (booking.getCheckoutTime().getTime() - booking.getCheckinTime().getTime()) / 1000 / 60 / 60;
            int point = (int) ((time + 1) * POINT_PER_HOUR);
            if (checkoutBooking(booking, point)) {
               
                HttpSession session = request.getSession();
                EmployeeDTO emp = (EmployeeDTO) session.getAttribute("USERDTO");

                BillDTO bill = new BillDTO();
                bill.setMoney(price);
                bill.setPoint(point);
                bill.setBookingID(booking.getBookingID());
                bill.setEmpID(emp.getPhone());
                int billID = addBill(bill);
                if (billID > 0) {
                    bill.setBillID(billID);
                    url = page;
                    request.setAttribute("BILLID", billID);
                } else {
                    request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại");
                }
            } else {
                request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại");
            }

        } catch (ClassNotFoundException | SQLException e) {
            request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại");
            log(e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean checkoutBooking(BookingDTO booking, int point) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.checkoutBooking(booking, point);
    }

    private BookingDTO getCheckinBookingByID(String bookingID) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.getCheckinBookingByID(bookingID);
    }

    private int addBill(BillDTO bill) throws SQLException, ClassNotFoundException {
        BillDAO dao = new BillDAO();
        return dao.addBill(bill);
    }

}
