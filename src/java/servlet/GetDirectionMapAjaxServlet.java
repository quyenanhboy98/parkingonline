/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.ParkDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ParkDAO;
import utils.Direction;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "GetDirectionMapAjaxServlet", urlPatterns = {"/GetDirectionMapAjaxServlet"})
public class GetDirectionMapAjaxServlet extends HttpServlet {

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

        if (parkID == null || parkID.isEmpty()) {
            return;
        }

        try {
            ParkDTO park = getParkByID(parkID);
            if (park != null) {

                HttpSession session = request.getSession();
                String lat = (String) session.getAttribute("CURRENTLAT");
                String lng = (String) session.getAttribute("CURRENTLNG");

                String origin = lat + "," + lng;
                String destination = park.getLat() + "," + park.getLng();

                String url = Direction.getDirection(origin, destination);
                response.getWriter().write(url);
            }
        } catch (ClassNotFoundException | SQLException e) {
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

    public ParkDTO getParkByID(String parkID) throws SQLException, ClassNotFoundException {
        ParkDAO dao = new ParkDAO();
        return dao.getParkByID(parkID);
    }
}
