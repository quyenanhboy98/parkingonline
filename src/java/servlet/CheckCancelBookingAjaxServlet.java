/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BookingDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "CheckCancelBookingAjaxServlet", urlPatterns = {"/CheckCancelBookingAjaxServlet"})
public class CheckCancelBookingAjaxServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String UNDER_POINT = "5";
    private static final String UPPER_POINT = "2";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String bookingID = request.getParameter("bookingID");

        if (bookingID == null || bookingID.isEmpty()) {
            return;
        }

        try {
            boolean isBooking = checkStatusBooking(bookingID);
            if (isBooking) {
                Timestamp now = Timestamp.valueOf(LocalDateTime.now());
                Timestamp expired = getBookineExperiedTime(bookingID);
                long diff = (expired.getTime() - now.getTime()) / 1000 / 60;
                if (diff <= 0) {
                    response.getWriter().write("NotBooking");
                } else if (diff < 30) {
                    response.getWriter().write(UNDER_POINT);
                } else {
                    response.getWriter().write(UPPER_POINT);
                }
            } else {
                response.getWriter().write("NotBooking");
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            log(e.getMessage());
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

    private boolean checkStatusBooking(String bookingID) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.checkStatusBooking(bookingID);
    }

    private Timestamp getBookineExperiedTime(String bookingID) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.getBookineExperiedTime(bookingID);
    }
}
