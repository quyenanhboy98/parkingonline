/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.UserDetailsDTO;
import entity.UserDetailsError;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserDetailsDAO;
import utils.Encrypt;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/ChangePasswordServlet"})
public class ChangePasswordServlet extends HttpServlet {

    private final String changepassPage = "changepass.jsp";

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
        String current = request.getParameter("current");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");

        String url = changepassPage;
        boolean isError = false;
        UserDetailsError error = new UserDetailsError();
        try {
            if (phone == null || current == null || password == null || confirm == null) {
                isError = true;
                error.setCreateError("Thất bại. Có lỗi xảy ra. Vui lòng thử lại");
                return;
            }

            if (current.isEmpty()) {
                isError = true;
                error.setCurrentPasswordIsEmpty("Mật khẩu hiện tại không thể bỏ trống");
            } else {
                current = Encrypt.EncryptPwd(current);
            }

            if (password.isEmpty() || password.length() < 6) {
                isError = true;
                error.setPasswordIsEmpty("Mật khẩu ít nhất 6 ký tự");
            } else {
                if (!password.equals(confirm)) {
                    isError = true;
                    error.setConfirmIsNotMatch("Xác nhận mật khẩu không chính xác");
                } else {
                    password = Encrypt.EncryptPwd(password);
                }
            }

            if (!isError) {
                if (checkLogin(phone, current) != null) {
                    if (!changePass(phone, password)) {
                        isError = true;
                        error.setCreateError("Đổi mật khẩu thất bại.");
                    }
                } else {
                    isError = true;
                    error.setCurrentPasswordIsWrong("Mật khẩu hiện tại không chính xác");
                }
            }
        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
            log(e.getMessage());
        } finally {
            if (isError) {
                request.setAttribute("ERROR", error);
            } else {
                request.setAttribute("SUCCESS", "Đổi mật khẩu thành công");
            }
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

    private boolean changePass(String phone, String pass) throws ClassNotFoundException, SQLException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.changePass(phone, pass);
    }

    private UserDetailsDTO checkLogin(String phone, String pwd) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.checkLogin(phone, pwd);
    }
}
