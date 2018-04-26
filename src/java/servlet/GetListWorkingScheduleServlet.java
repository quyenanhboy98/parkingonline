/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.ParkDTO;
import entity.WorkingTimeDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ParkDAO;
import model.WorkingTimeDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "GetListWorkingScheduleServlet", urlPatterns = {"/GetListWorkingScheduleServlet"})
public class GetListWorkingScheduleServlet extends HttpServlet {

//    private static String page = "listworkingschedule.jsp";
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
        String srcPage = request.getParameter("srcPage");
        String url = srcPage;
        try {
            List<ParkDTO> listPark = getListParkActive();
            request.setAttribute("LISTPARK", listPark);
            if (parkID == null || parkID.trim().isEmpty()) { // role Admin, khong co parkID
                parkID = listPark.get(0).getParkID();
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("SELECTEDPARK", parkID);
            }
            List<WorkingTimeDTO> list = getListWorkingTime(parkID);
            request.setAttribute("LISTWORKINGTIME", list);
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

    private List<WorkingTimeDTO> getListWorkingTime(String parkID) throws SQLException, ClassNotFoundException {
        WorkingTimeDAO dao = new WorkingTimeDAO();
        return dao.getListWorkingTime(parkID);
    }

    private List<ParkDTO> getListParkActive() throws SQLException, ClassNotFoundException {
        ParkDAO dao = new ParkDAO();
        return dao.getListParkActive();
    }
}
