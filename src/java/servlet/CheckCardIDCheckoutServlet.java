/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BookingDTO;
import entity.ParkDTO;
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
import model.BookingDAO;
import model.ParkDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "CheckCardIDCheckoutServlet", urlPatterns = {"/CheckCardIDCheckoutServlet"})
public class CheckCardIDCheckoutServlet extends HttpServlet {

    private static final String error = "checkout.jsp";
    private static final String page = "CheckoutBookingServlet";

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

        String cardID = request.getParameter("cardID");
        String url = error;

        try {
            if (cardID == null || cardID.isEmpty()) {
                return;
            }
            
            BookingDTO booking = getCheckinBookingByCardID(cardID);
            if (booking != null) {

                Timestamp checkoutTime = Timestamp.valueOf(LocalDateTime.now());
                long test = checkoutTime.getTime() - booking.getCheckinTime().getTime();
                long time = test / 1000 / 60 / 60;
                if (time <= 0) {
                    time = 1;
                }
                float price = getParkByID(booking.getParkID()).getPrice() * time;

                request.setAttribute("BOOKING", booking);
                request.setAttribute("CHECKOUTTIME", checkoutTime);
                request.setAttribute("PRICE", price);
                url = page;

            } else {
                request.setAttribute("ERROR", "Số thẻ này chưa được sử dụng.");
            }
        } catch (ClassNotFoundException | SQLException e) {
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

    private BookingDTO getCheckinBookingByCardID(String bookingID) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.getCheckinBookingByCardID(bookingID);
    }

    private ParkDTO getParkByID(String parkID) throws SQLException, ClassNotFoundException {
        ParkDAO dao = new ParkDAO();
        return dao.getParkByID(parkID);
    }
}
