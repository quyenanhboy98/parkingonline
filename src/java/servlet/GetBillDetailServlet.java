/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BillDTO;
import entity.BookingDTO;
import entity.EmployeeDTO;
import entity.ParkDTO;
import entity.UserDetailsDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BillDAO;
import model.BookingDAO;
import model.EmployeeDAO;
import model.ParkDAO;
import model.UserDetailsDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "GetBillDetailServlet", urlPatterns = {"/GetBillDetailServlet"})
public class GetBillDetailServlet extends HttpServlet {

    private static final String page = "bill.jsp";

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

        String billID = request.getParameter("billID");

        String url = page;

        try {

            if (billID == null || billID.isEmpty()) {
                request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại");
                return;
            }

            BillDTO bill = getBillDetail(billID);

            if (bill != null) {

                EmployeeDTO emp = getEmployeeDetail(bill.getEmpID());
                BookingDTO booking = getBookingDetail(bill.getBookingID());
                UserDetailsDTO user = getUserDetail(booking.getPhone());
                ParkDTO park = getParkByID(booking.getParkID());

                request.setAttribute("EMP", emp);
                request.setAttribute("BOOKING", booking);
                request.setAttribute("USER", user);
                request.setAttribute("PARK", park);
                request.setAttribute("BILL", bill);
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

    private BillDTO getBillDetail(String billID) throws SQLException, ClassNotFoundException {
        BillDAO dao = new BillDAO();
        return dao.getBillDetail(billID);
    }

    private EmployeeDTO getEmployeeDetail(String phone) throws SQLException, ClassNotFoundException {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.getEmployeeDetail(phone);
    }

    private UserDetailsDTO getUserDetail(String phone) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.getUserDetail(phone);
    }

    public BookingDTO getBookingDetail(int bookingID) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.getBookingDetail(bookingID);
    }

    public ParkDTO getParkByID(String parkID) throws SQLException, ClassNotFoundException {
        ParkDAO dao = new ParkDAO();
        return dao.getParkByID(parkID);
    }
}
