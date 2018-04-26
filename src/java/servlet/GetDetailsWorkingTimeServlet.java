/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.ParkDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ParkDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "GetDetailsWorkingTimeServlet", urlPatterns = {"/GetDetailsWorkingTimeServlet"})
public class GetDetailsWorkingTimeServlet extends HttpServlet {

    private static String page = "addworking.jsp";

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
        String parkSpaceStr = request.getParameter("parkSpace");
        String parkName = request.getParameter("parkName");
        String parkAddress = request.getParameter("parkAddress");
        String costPerHour = request.getParameter("costPerHour");
        String latitudeStr = request.getParameter("latitude");
        String longtitudeStr = request.getParameter("longtitude");
        String url = page;

        if (parkID == null && (parkSpaceStr == null || parkName == null || parkAddress == null
                || latitudeStr == null || longtitudeStr == null)) {
            return;
        }
        ParkDTO dto = null;
        try {

            if (latitudeStr != null && longtitudeStr != null && parkSpaceStr != null && costPerHour != null) {
                float latitude = Float.parseFloat(latitudeStr);
                float longtitude = Float.parseFloat(longtitudeStr);
                int parkSpace = Integer.parseInt(parkSpaceStr);
                int cost = Integer.parseInt(costPerHour);
                dto = new ParkDTO(parkID, parkName, parkAddress, cost, parkSpace, latitude, longtitude);
            } else {
                dto = getParkByID(parkID);
            }
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            log(e.getMessage());

        }
        request.setAttribute("PARK", dto);

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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

    private ParkDTO getParkByID(String parkID) throws SQLException, ClassNotFoundException {
        ParkDAO dao = new ParkDAO();
        return dao.getParkByID(parkID);
    }
}
