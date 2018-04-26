/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.EmployeeDTO;
import entity.UserDetailsError;
import entity.WorkingTimeDTO;
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
import model.EmployeeDAO;
import model.WorkingTimeDAO;
import utils.Validate;

/**
 *
 * @author junge
 */
@WebServlet(name = "UpdateEmployeeServlet", urlPatterns = {"/UpdateEmployeeServlet"})
public class UpdateEmployeeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String updatePage = "GetListWorkingScheduleServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String phone = request.getParameter("phone");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String gender = request.getParameter("sex");
        String date = request.getParameter("dob");
        String workingTimeID = request.getParameter("workingTimeID");
        String parkName = request.getParameter("parkName");
        String parkID = request.getParameter("parkID");
        String url = updatePage;
        boolean isError = false;
        UserDetailsError error = new UserDetailsError();
        EmployeeDTO dto = null;
        try {
            if (phone == null || email == null || fullname == null || address == null
                    || gender == null || date == null || workingTimeID == null) {
                isError = true;
                error.setCreateError("Thất bại. Có lỗi xảy ra. Vui lòng thử lại");
                return;
            }
            if (fullname.trim().isEmpty()) {
                isError = true;
                error.setNameIsEmpty("Tên không thể bỏ trống.");
            }

            boolean sex = gender.equals("Male");

            if (email.isEmpty() || !Validate.validEmail(email)) {
                isError = true;
                error.setEmailIsEmpty("Email sai định dạng");
            }

            if (address.trim().isEmpty()) {
                isError = true;
                error.setAddressIsEmpty("Địa chỉ không thể bỏ trống");
            }

            if (fullname.contains("<") || fullname.contains(">")) {
                isError = true;
                error.setNameIsEmpty("Tên sai định dạng.");
            }

//            if (address.contains("<") || address.contains(">")) {
//                isError = true;
//                error.setAddressIsEmpty("Địa chỉ sai định dạng.");
//            }

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

            fullname = fullname.trim();
            address = address.trim();

            dto = new EmployeeDTO(phone, fullname, email, address, sex, dob, workingTimeID);
//bo sung info cho dto
            WorkingTimeDTO worktime = getTimeStartEnd(workingTimeID);

            dto.setParkID(parkID);
            dto.setParkName(parkName);
            dto.setTimeStart(worktime.getTimeStart());
            dto.setTimeEnd(worktime.getTimeEnd());
//-----
            if (!isError) {
                EmployeeDTO emp = new EmployeeDTO(phone, fullname, email, address, sex, dob, workingTimeID);
                if (!updateEmployee(emp)) {
                    isError = true;
                    error.setCreateError("Cập nhật thất bại. Có lỗi xảy ra. Vui lòng thử lại.");
                } else {
                    //change session USERDTO
                    HttpSession session = request.getSession();
                    EmployeeDTO USERDTO = (EmployeeDTO) session.getAttribute("USERDTO");
                    if (USERDTO.getPhone().equals(emp.getPhone())) {
                        USERDTO.setTimeStart(worktime.getTimeStart());
                        USERDTO.setTimeEnd(worktime.getTimeEnd());
                        session.setAttribute("USERDTO", USERDTO);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log(e.getMessage());
            isError = true;
            String msg = e.getMessage();
            if (msg.contains("PRIMARY")) {
                error.setPhoneIsExisted("SĐT " + phone + " đã được đăng ký");
            } else if (msg.contains("Email_UNIQUE")) {
                error.setEmailIsEmpty("Email " + email + " đã được đăng ký");
            } else {
                log(msg);
            }
        } finally {
            if (isError) {
                request.setAttribute("ERROR", error);
                request.setAttribute("PROFILEUSER", dto);

            } else {
                request.setAttribute("SUCCESS", "Cập nhật thành công.");
                HttpSession session = request.getSession();
                Object tmp = session.getAttribute("USERDTO");
                if (tmp instanceof EmployeeDTO) {
                    EmployeeDTO emp = (EmployeeDTO) session.getAttribute("USERDTO");
                    if (dto != null && emp.getPhone().equals(dto.getPhone())) {
                        dto.setRole(3);
                        session.setAttribute("USERDTO", dto);
                    } else {
                        request.setAttribute("PROFILEUSER", dto);
                    }
                } else {
                    request.setAttribute("PROFILEUSER", dto);
                }
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

    private boolean updateEmployee(EmployeeDTO emp) throws ClassNotFoundException, SQLException {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.updateEmployee(emp);
    }

    private WorkingTimeDTO getTimeStartEnd(String workingTimeID) throws ClassNotFoundException, SQLException {
        WorkingTimeDAO dao = new WorkingTimeDAO();
        return dao.getTimeStartEnd(workingTimeID);
    }

}
