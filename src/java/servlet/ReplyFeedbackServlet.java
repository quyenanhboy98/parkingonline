/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.FeedbackDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FeedbackDAO;
import model.UserDetailsDAO;
import utils.Mailer;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "ReplyFeedbackServlet", urlPatterns = {"/ReplyFeedbackServlet"})
public class ReplyFeedbackServlet extends HttpServlet {

    private static String page = "SearchFeedbackServlet";

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

        String feedbackID = request.getParameter("feedbackID");
        String mess = request.getParameter("mess");
        String url = page;
        try {
            if (feedbackID != null) {
                FeedbackDTO feedback = getFeedbackByID(feedbackID);
                if (feedback != null && replyFeedback(feedbackID, mess)) {
                    String email = getUserEmail(feedback.getPhone());
                    Mailer.sendReplyFeedbackMail(email, feedback.getTitle(), feedback.getFeedbackTime().toString(), feedback.getMessage(), mess);
                    request.setAttribute("SUCCESS", "Gửi hồi đáp thành công");
                } else {
                    request.setAttribute("ERROR", "Gửi hồi đáp thất bại");
                }
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

    private boolean replyFeedback(String feedbackID, String mess) throws SQLException, ClassNotFoundException {
        FeedbackDAO dao = new FeedbackDAO();
        return dao.replyFeedback(feedbackID, mess);
    }

    private FeedbackDTO getFeedbackByID(String feedbackID) throws SQLException, ClassNotFoundException {
        FeedbackDAO dao = new FeedbackDAO();
        return dao.getFeedbackByID(feedbackID);
    }

    private String getUserEmail(String phone) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.getUserEmail(phone);
    }
}
