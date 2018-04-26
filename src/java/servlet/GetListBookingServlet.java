/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BookingDTO;
import entity.ParkDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "GetListBookingServlet", urlPatterns = {"/GetListBookingServlet"})
public class GetListBookingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String page = "listbooking.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String parkID = request.getParameter("parkID");
        String url = page;

        try {
            List<BookingDTO> list = getListBooking(parkID);
            request.setAttribute("LISTBOOKING", list);
            if (list == null) {
                request.setAttribute("LISTEMPTY", "Danh sách đặt chỗ rỗng.");
            }
            if (parkID == null) {
                List<ParkDTO> listPark = getListPark();
                request.setAttribute("LISTPARK", listPark);
            }

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
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

    private List<BookingDTO> getListBooking(String phone) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.getListBooking(phone);
    }

    private List<ParkDTO> getListPark() throws SQLException, ClassNotFoundException {
        ParkDAO dao = new ParkDAO();
        return dao.getListPark();
    }
}
