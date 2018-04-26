/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.UserDetailsDTO;
import entity.UserDetailsError;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDetailsDAO;
import utils.Validate;

/**
 *
 * @author junge
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/UpdateUserServlet"})
public class UpdateUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String updatePage = "userdetails.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String phone = request.getParameter("phone");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String gender = request.getParameter("sex");
        String date = request.getParameter("dob");
        String srcPage = request.getParameter("srcPage");
        String roleStr = request.getParameter("role");
        boolean sex = false;
        Date dob = null;
        String url = updatePage;
        boolean isError = false;
        UserDetailsError error = new UserDetailsError();
        UserDetailsDTO dto = null;
        try {

            if (srcPage != null && srcPage.equals("profileuser.jsp")) {
                url = srcPage;
            }

            if (phone == null || email == null || fullname == null || address == null
                    || gender == null || date == null) {
                isError = true;
                error.setCreateError("Tạo thất bại. Có lỗi xảy ra. Vui lòng thử lại");
                return;
            }

            if (fullname.trim().isEmpty()) {
                isError = true;
                error.setNameIsEmpty("Tên không thể bỏ trống");
            }

            sex = gender.equals("Male");

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
                error.setNameIsEmpty("Tên sai định dạng.");
            }

            if (address.contains("<") || address.contains(">")) {
                isError = true;
                error.setAddressIsEmpty("Địa chỉ sai định dạng.");
            }

            fullname = fullname.trim();
            address = address.trim();

            dob = Validate.validDate(date);
            if (dob == null) {
                isError = true;
                error.setDobIsWrongFormation("Ngày sinh không thể bỏ trống");
            } else {
                if (LocalDate.now().isBefore(dob.toLocalDate())) {
                    isError = true;
                    error.setDobIsWrongFormation("Ngày sinh phải trước ngày hiện tại");
                }
            }

            dto = new UserDetailsDTO(phone, fullname, email, address, sex, dob);

            int role = 0;
            if (roleStr != null) {
                role = Integer.parseInt(roleStr);
                dto.setRole(role);
            }

            if (!isError) {
                UserDetailsDTO user = new UserDetailsDTO(phone, fullname, email, address, sex, dob);
                user.setRole(role);

                if (!updateUser(user)) {
                    isError = true;
                    error.setCreateError("Cập nhật thất bại. Có lỗi xảy ra. Vui lòng thử lại.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log(e.getMessage());
            isError = true;
            String msg = e.getMessage();
            if (msg.contains("PRIMARY")) {
                error.setPhoneIsExisted("SĐT " + phone + " đã được đăng ký.");
            } else if (msg.contains("Email_UNIQUE")) {
                error.setEmailIsEmpty("Email " + email + " đã được đăng ký.");
            } else {
                log(msg);
            }
        } finally {
            if (isError) {
                request.setAttribute("ERROR", error);
            } else {
                request.setAttribute("SUCCESS", "Cập nhật thành công");
            }
            HttpSession session = request.getSession();
            UserDetailsDTO USERDTO = (UserDetailsDTO) session.getAttribute("USERDTO");
            if (dto != null && !dto.getPhone().equals(USERDTO.getPhone())) {
                request.setAttribute("PROFILEUSER", dto);
            } else if (!isError) {
                USERDTO.setFullName(fullname);
                USERDTO.setEmail(email);
                USERDTO.setAddress(address);
                USERDTO.setSex(sex);
                USERDTO.setDob(dob);
                session.setAttribute("USERDTO", USERDTO);
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    private boolean updateUser(UserDetailsDTO user) throws ClassNotFoundException, SQLException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.updateUser(user);
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
