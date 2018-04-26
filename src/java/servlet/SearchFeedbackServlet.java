/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.FeedbackDTO;
import entity.ParkDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FeedbackDAO;
import model.ParkDAO;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "SearchFeedbackServlet", urlPatterns = {"/SearchFeedbackServlet"})
public class SearchFeedbackServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String listFeedbackPage = "listfeedback.jsp";
    private final String GetListFeedbackServlet = "GetListFeedbackServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String parkID = request.getParameter("parkID");
        String url = listFeedbackPage;
        try {
            if (fromDate == null || toDate == null || fromDate.isEmpty() || toDate.isEmpty()) {
                url = GetListFeedbackServlet;
                return;
            }

            Timestamp from = Timestamp.valueOf(fromDate + " 00:00:00");
            Timestamp to = Timestamp.valueOf(toDate + " 23:59:59");

            List<FeedbackDTO> listFeedback = searchFeedbackByDate(from, to, parkID);
            request.setAttribute("LISTFEEDBACK", listFeedback);
            if (listFeedback == null) {
                request.setAttribute("LISTEMPTY", "Không tìm thấy feedback dựa trên kết quả tìm kiếm.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            log(e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    private List<FeedbackDTO> searchFeedbackByDate(Timestamp from, Timestamp to, String parkID) throws SQLException, ClassNotFoundException {
        FeedbackDAO dao = new FeedbackDAO();
        return dao.searchFeedbackByDate(from, to, parkID);
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

}
