/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BookingDTO;
import entity.EmployeeDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookingDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "CheckinBookingServlet", urlPatterns = {"/CheckinBookingServlet"})
public class CheckinBookingServlet extends HttpServlet {

    private static final String page = "checkin.jsp";

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

        String bookingID = request.getParameter("bookingID");
        String cardID = request.getParameter("cardID");
        String url = page;

        try {
            if (bookingID == null || bookingID.isEmpty() || cardID == null || cardID.isEmpty()) {
                request.setAttribute("ERROR", "Chưa nhập dữ liệu. Vui lòng thử lại");
                return;
            }

            HttpSession session = request.getSession();
            EmployeeDTO emp = (EmployeeDTO) session.getAttribute("USERDTO");
            if (checkCardIDForCheckin(cardID, emp.getParkID())) {
                request.setAttribute("ERROR", "Thẻ xe này đang checkin cho 1 xe khác trong bãi. Vui lòng kiểm tra lại");
                return;
            }

            BookingDTO booking = getBookingDetail(Integer.parseInt(bookingID));
            if (LocalDateTime.now().isBefore(booking.getBookTime().toLocalDateTime().minusMinutes(30))) {
                request.setAttribute("ERROR", "Chưa đến giờ checkin.\n Thời gian sớm nhất để checkin theo giờ đặt là: " + booking.getBookTime().toLocalDateTime().minusMinutes(30));
                return;
            } else if (LocalDateTime.now().isAfter(booking.getExpiredTime().toLocalDateTime())) {
                request.setAttribute("ERROR", "Lượt đặt đã hết hạn.");
                return;
            }

            Timestamp checkinTime = Timestamp.valueOf(LocalDateTime.now());

            if (checkinBooking(bookingID, cardID, checkinTime)) {
                request.setAttribute("SUCCESS", "Checkin thành công. ID lượt đặt xe: " + bookingID);
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

    private boolean checkinBooking(String bookingID, String cardID, Timestamp checkinTime) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.checkinBooking(bookingID, cardID, checkinTime);
    }

    public BookingDTO getBookingDetail(int bookingID) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.getBookingDetail(bookingID);
    }

    public boolean checkCardIDForCheckin(String cardID, String parkID) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.checkCardIDForCheckin(cardID, parkID);
    }
}
