/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.ParkDTO;
import entity.ParkDistanceDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ParkDAO;
import org.apache.catalina.util.URLEncoder;
import utils.DistanceMatrix;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "GetParkDistanceMatrixServlet", urlPatterns = {"/GetParkDistanceMatrixServlet"})
public class GetParkDistanceMatrixServlet extends HttpServlet {

    private static String page = "findpark.jsp";

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

        String location = request.getParameter("location");
        boolean sortByPrice = request.getParameter("sortByPrice") != null;
        String rangeStr = request.getParameter("range");
        String action = request.getParameter("find");

        String url = page;

        try {

            HttpSession session = request.getSession();
            String lat = (String) session.getAttribute("CURRENTLAT");
            String lng = (String) session.getAttribute("CURRENTLNG");

            List<ParkDTO> list = getListParkActive();
            List<ParkDistanceDTO> result = null;
            String current = lat + "," + lng;

            if (action == null) {
                request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại sau.");
                return;
            }

            if (action.equalsIgnoreCase("Tìm hết")) {
                result = DistanceMatrix.getAllParkInDistance(current, list);
            } else {

                if (location == null || rangeStr == null) {
                    request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại sau.");
                    return;
                }

                int range = Integer.parseInt(rangeStr);
                if (range >= 1000) {
                    rangeStr = range / 1000 + "Km";
                } else {
                    rangeStr = rangeStr + "m";
                }

                if (action.equalsIgnoreCase("TÌM") && !location.isEmpty()) {
                    String search = new URLEncoder().encode(location);
                    result = DistanceMatrix.getParkInDistance(current, search, list, range);
                } else if (action.equalsIgnoreCase("GẦN TÔI")) {
                    result = DistanceMatrix.getParkInDistance(current, list, range);
                    if (result != null) {
                        Collections.sort(result, new ParkDistanceDTO.SortByDistance());
                    }
                } else {
                    request.setAttribute("NOTFOUND", "Vui lòng nhập điểm đến.");
                    return;
                }
            }

            if (result == null) {
                request.setAttribute("ERROR", "Lỗi kết nối mạng hoặc google api limit. Vui lòng thử lại sau.");
            } else if (result.isEmpty()) {
                if (rangeStr != null) {
                    request.setAttribute("NOTFOUND", "Không tìm thấy bãi xe nào trong phạm vi " + rangeStr + " gần bạn hoặc địa điểm đến của bạn.");
                } else {
                    request.setAttribute("NOTFOUND", "Không tìm thấy bãi xe nào trong cơ sở dữ liệu");
                }
            } else {
                if (sortByPrice && request.getParameter("sortByPrice").equalsIgnoreCase("on")) {
                    Collections.sort(result, new ParkDistanceDTO.SortByPrice());
                }
                request.setAttribute("PARKDISTANCE", result);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            log(ex.getMessage());
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

    private List<ParkDTO> getListParkActive() throws SQLException, ClassNotFoundException {
        ParkDAO dao = new ParkDAO();
        return dao.getListParkActive();
    }
}
