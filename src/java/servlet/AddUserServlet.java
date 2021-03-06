/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.UserDetailsError;
import entity.UserDetailsDTO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserDetailsDAO;
import utils.Encrypt;
import utils.Validate;

/**
 *
 * @author Yuu
 */
@WebServlet(name = "AddUserServlet", urlPatterns = {"/AddUserServlet"})
public class AddUserServlet extends HttpServlet {

    private final String registerPage = "adduser.jsp";

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
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullName");
        String address = request.getParameter("address");
        String gender = request.getParameter("sex");
        String isAdmin = request.getParameter("role");
        String date = request.getParameter("dob");
        String srcPage = request.getParameter("srcPage");
        String url = registerPage;
        UserDetailsError error = new UserDetailsError();
        boolean isError = false;
        try {

            if (srcPage == null) {
                return;
            } else {
                url = srcPage;
            }

            if (password == null || email == null || phone == null || fullname == null || address == null || gender == null || date == null) {
                isError = true;
                error.setCreateError("Tạo thất bại. Có lỗi xảy ra. Vui lòng thử lại.");
                return;
            }

            if (!phone.matches("^0\\d{9,10}$")) {
                isError = true;
                error.setPhoneInputErrorFormat("SĐT không đúng định dạng");
            }

            if (password.isEmpty() || password.length() < 6) {
                isError = true;
                error.setPasswordIsEmpty("Mật khẩu ít nhất 6 ký tự");
            } else {
                if (!password.equals(confirmPassword)) {
                    isError = true;
                    error.setConfirmIsNotMatch("Xác nhận mật khẩu không trùng");
                } else {
                    password = Encrypt.EncryptPwd(password);
                }
            }

            if (fullname.trim().isEmpty()) {
                isError = true;
                error.setNameIsEmpty("Tên không thể bỏ trống");
            }

            boolean sex = gender.equals("Male");

            if (email.isEmpty() || !Validate.validEmail(email)) {
                isError = true;
                error.setEmailIsEmpty("Email sai định dạng");
            }

//            if (address.trim().isEmpty()) {
//                isError = true;
//                error.setAddressIsEmpty("Địa chỉ không thể bỏ trống");
//            }

            if (fullname.contains("<") || fullname.contains(">")) {
                isError = true;
                error.setNameIsEmpty("Tên sai định dạng");
            }

            if (address.contains("<") || address.contains(">")) {
                isError = true;
                error.setAddressIsEmpty("Địa chỉ sai định dạng");
            }

            Date dob = Validate.validDate(date);
            if (dob == null) {
                isError = true;
                error.setDobIsWrongFormation("Ngày sinh sai định dạng");
            } else {
                if (LocalDate.now().isBefore(dob.toLocalDate())) {
                    isError = true;
                    error.setDobIsWrongFormation("Ngày sinh phải trước ngày hiện tại");
                }
            }

            int role = isAdmin != null ? 1 : 2;

            address = address.trim();
            fullname = fullname.trim();

            if (!isError) {
                UserDetailsDTO user = new UserDetailsDTO(phone, password, fullname, sex, email, address, dob, role);
                if (!addUser(user)) {
                    isError = true;
                }
            }

        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
            isError = true;
            String msg = e.getMessage();
            if (msg.contains("PRIMARY")) {
                error.setPhoneIsExisted("SĐT " + phone + " đã được đăng ký.");
            } else if (msg.contains("Email_UNIQUE")) {
                error.setPhoneIsExisted("Email " + email + " đã được đăng ký.");
            } else {
                log(msg);
            }
        } finally {
            if (isError) {
                request.setAttribute("ERROR", error);
            } else {
                if (srcPage.equals("userregister.jsp")) {
                    url = "login.jsp";
                }
                request.setAttribute("SUCCESS", "Tài khoản " + phone + " tạo thành công.");
            }
            if (srcPage != null) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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

    private boolean addUser(UserDetailsDTO user) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.addUser(user);
    }
}
