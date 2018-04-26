/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.WorkingTimeDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WorkingTimeDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "GetListWorkingTimeAjaxServlet", urlPatterns = {"/GetListWorkingTimeAjaxServlet"})
public class GetListWorkingTimeAjaxServlet extends HttpServlet {

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

        try {
            List<WorkingTimeDTO> list = getWorkingTimeList(parkID);
            response.getWriter().write("<option " + "value=\"NWKT\">None</option>");
            if (list != null) {
                for (WorkingTimeDTO dto : list) {
                    response.getWriter().write("<option " + "value=\"" + dto.getID() + " \">" + dto.getTimeStart() + " - " + dto.getTimeEnd() + "</option>");
                }
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

    private List<WorkingTimeDTO> getWorkingTimeList(String parkID) throws SQLException, ClassNotFoundException {
        WorkingTimeDAO dao = new WorkingTimeDAO();
        return dao.getListWorkingTime(parkID);
    }

}
