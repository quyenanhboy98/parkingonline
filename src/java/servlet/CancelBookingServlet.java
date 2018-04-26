/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BookingDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "CancelBookingServlet", urlPatterns = {"/CancelBookingServlet"})
public class CancelBookingServlet extends HttpServlet {

   
    private final String page = "CheckCurrentBookingServlet";

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
        
        String bookingIDStr = request.getParameter("bookingID");
        String parkID = request.getParameter("parkID");
        String phone = request.getParameter("phone");
        String pointStr = request.getParameter("point");
       
        String url = page;
        boolean isError = false;
        
        try {
            if (parkID == null || phone == null || pointStr == null || bookingIDStr == null || 
                parkID.isEmpty() || phone.isEmpty() || pointStr.isEmpty() || bookingIDStr.isEmpty()) {
                isError = true;
                return;
            }
            
            int point = Integer.parseInt(pointStr);
            int bookingID = Integer.parseInt(bookingIDStr);
            
            BookingDTO booking = new BookingDTO();
            booking.setParkID(parkID);
            booking.setPhone(phone);
            booking.setBookingID(bookingID);
            
            if (!cancelBooking(booking, point)) {
                isError = true;
            } 
        } catch (ClassNotFoundException | SQLException e) {
            isError = true;
            log(e.getMessage());
        } finally {
            if (isError) {
                request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại");
            } else {
                request.setAttribute("SUCCESS", "Huỷ thành công.");
            }
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

    private boolean cancelBooking(BookingDTO booking, int point) throws ClassNotFoundException, SQLException {
        BookingDAO dao = new BookingDAO();
        return dao.cancelBooking(booking, point);
    }
}
