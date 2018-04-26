/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.FeedbackDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FeedbackDAO;

/**
 *
 * @author Jun
 */
@WebServlet(name = "AddFeedbackServlet", urlPatterns = {"/AddFeedbackServlet"})
public class AddFeedbackServlet extends HttpServlet {

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
        String phone = request.getParameter("phone");
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        String parkID = request.getParameter("parkID");
        String srcPage = request.getParameter("srcPage");

        String url = srcPage;
        boolean isError = false;
        try {

            if (phone == null || title == null || message == null || title.isEmpty() || message.isEmpty()) {
                isError = true;
                request.setAttribute("ERROR", "Gửi phản hồi thất bại. Vui lòng điền đủ thông tin và thử lại.");
                return;
            }

            Timestamp now = new Timestamp(System.currentTimeMillis());

            FeedbackDTO dto = new FeedbackDTO(phone, title, message, now, parkID);

            if (title.isEmpty() || message.isEmpty()) {
                isError = true;
                request.setAttribute("ERROR", "Tiêu đề và nội dung không được để trống.");
                return;
            }

            if (title.length() > 50) {
                isError = true;
                request.setAttribute("ERROR", "Tiêu đề giới hạn 50 ký tự");
                return;
            }

            if (message.length() > 500) {
                isError = true;
                request.setAttribute("ERROR", "Nội dung giới hạn 500 ký tự");
                return;
            }

            if (title.isEmpty() || message.isEmpty()) {
                isError = true;
                request.setAttribute("ERROR", "Tiêu đề và nội dung không được để trống.");
                return;
            }

            if (title.contains("<") || title.contains(">") || message.contains("<") || message.contains(">")) {
                isError = true;
                request.setAttribute("ERROR", "Tiêu đề và nội dung không được có kí tự đặc biệt.");
                return;
            }

            if (!addFeedback(dto) && !isError) {
                isError = true;
                request.setAttribute("ERROR", "Gửi phản hồi thất bại. Có lỗi xảy ra. Vui lòng thử lại");
            }

        } catch (ClassNotFoundException | SQLException e) {
            isError = true;
            String msg = e.getMessage();
            log(msg);
        } finally {
            if (!isError) {
                request.setAttribute("SUCCESS", "Phản hồi đã được gửi. Xin cảm ơn.");
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        }
    }

    private boolean addFeedback(FeedbackDTO dto) throws SQLException, ClassNotFoundException {
        FeedbackDAO dao = new FeedbackDAO();
        return dao.addFeedback(dto);
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
