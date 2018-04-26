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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ParkDAO;
import utils.Time;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "CheckParkBookingAjaxServlet", urlPatterns = {"/CheckParkBookingAjaxServlet"})
public class CheckParkBookingAjaxServlet extends HttpServlet {

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

        String parkID = request.getParameter("parkID");
        String bookTime = request.getParameter("bookTime");

        if (parkID == null || parkID.isEmpty() || bookTime == null || bookTime.isEmpty()) {
            return;
        }

        try {
            boolean isAvailable = checkEmptySlot(parkID);
            if (isAvailable) {

                boolean isNext = false;
                LocalTime time = LocalTime.parse(bookTime.trim());
                LocalDate bookingDate = LocalDate.now();
                if (LocalTime.now().minusMinutes(10).isAfter(time)){
                    bookingDate.plusDays(1);
                    isNext = true;               
                }

                Timestamp bookingTime = Timestamp.valueOf(LocalDateTime.of(bookingDate, time.plusMinutes(10)));
                response.getWriter().write(isNext ? bookingTime.toString() + "|Next day" :  bookingTime.toString());
                
            } else {
                response.getWriter().write("None");
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

    public boolean checkEmptySlot(String parkID) throws SQLException, ClassNotFoundException {
        ParkDAO dao = new ParkDAO();
        return dao.checkEmptySlot(parkID);
    }
}
