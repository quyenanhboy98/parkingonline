/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.UserDetailsDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Util;

/**
 *
 * @author Yuu
 */
public class UserDetailsDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public UserDetailsDAO() {
    }

    public boolean addUser(UserDetailsDTO user) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "INSERT INTO userdetails(Phone, Password, Fullname, Sex, Email, Address, DoB, Role) VALUES(?,?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, user.getPhone());
            preStm.setString(2, user.getPassword());
            preStm.setString(3, user.getFullName());
            preStm.setBoolean(4, user.isSex());
            preStm.setString(5, user.getEmail());
            preStm.setString(6, user.getAddress());
            preStm.setDate(7, user.getDob());
            preStm.setInt(8, user.getRole());
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public boolean changeActive(String phone, boolean active) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "UPDATE userdetails SET Active = ? WHERE Phone = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, active);
            preStm.setString(2, phone);
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public boolean checkActive(String phone) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "SELECT Active FROM userdetails WHERE Phone = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            rs = preStm.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("Active");
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return false;
    }

    public UserDetailsDTO checkLogin(String phone, String password) throws SQLException, ClassNotFoundException {
        UserDetailsDTO dto = null;
        try {
            conn = Util.getConnection();
            String sql = "SELECT * FROM userdetails AS U, userrole AS R "
                    + "WHERE Phone = ? AND Password = ? AND U.Role = R.RoleID";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String email = rs.getString("Email");
                String fullname = rs.getString("FullName");
                String address = rs.getString("Address");
                boolean sex = rs.getBoolean("Sex");
                Date dob = rs.getDate("DoB");
                int role = rs.getInt("Role");
                String roleName = rs.getString("RoleName");
                boolean active = rs.getBoolean("Active");
                dto = new UserDetailsDTO(phone, fullname, sex, email, address, dob, role, roleName, active);
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return dto;
    }

    public String getUserEmail(String phone) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "SELECT Email FROM userdetails WHERE Phone = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            rs = preStm.executeQuery();
            if (rs.next()) {
                return rs.getString("Email");
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public List<UserDetailsDTO> getListUser(String role) throws SQLException, ClassNotFoundException {
        List<UserDetailsDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT Phone, Fullname, Sex, Email, Address, DoB, Active, Role, RoleName, Point "
                    + "FROM userdetails AS UD, userrole AS R "
                    + "WHERE UD.Role = R.RoleID ";

            if (role == null) {
                sql += "AND (R.RoleID = 1 OR R.RoleID = 2) ";
                preStm = conn.prepareStatement(sql);
            } else {
                sql += "AND R.RoleName = ? ";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, role);
            }
            
            rs = preStm.executeQuery();
            while (rs.next()) {
                UserDetailsDTO user = new UserDetailsDTO();
                user.setPhone(rs.getString("Phone"));
                user.setFullName(rs.getString("Fullname"));
                user.setSex(rs.getBoolean("Sex"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setDob(rs.getDate("DoB"));
                user.setActive(rs.getBoolean("Active"));
                user.setRole(rs.getInt("Role"));
                user.setRoleName(rs.getString("RoleName"));
                user.setPoint(rs.getInt("Point"));
                result.add(user);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<UserDetailsDTO> searchListUser(String value, String role) throws SQLException, ClassNotFoundException {
        List<UserDetailsDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT Phone, Fullname, Sex, Email, Address, DoB, Active, Role, RoleName, Point "
                    + "FROM userdetails AS UD, userrole AS R "
                    + "WHERE UD.Role = R.RoleID "
                    + "AND (UD.Phone LIKE ? OR UD.Fullname LIKE ?) ";

            if (role.isEmpty()) {
                sql += "AND (R.RoleID = 1 OR R.RoleID = 2)";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + value + "%");
                preStm.setString(2, "%" + value + "%");
            } else {
                sql += "AND R.RoleName LIKE ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + value + "%");
                preStm.setString(2, "%" + value + "%");
                preStm.setString(3, "%" + role + "%");
            }

            rs = preStm.executeQuery();
            while (rs.next()) {
                UserDetailsDTO user = new UserDetailsDTO();
                user.setPhone(rs.getString("Phone"));
                user.setFullName(rs.getString("Fullname"));
                user.setSex(rs.getBoolean("Sex"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setDob(rs.getDate("DoB"));
                user.setActive(rs.getBoolean("Active"));
                user.setRole(rs.getInt("Role"));
                user.setRoleName(rs.getString("RoleName"));
                user.setPoint(rs.getInt("Point"));
                result.add(user);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public boolean updateUser(UserDetailsDTO user) throws ClassNotFoundException, SQLException {
        boolean isUpdate = false;
        try {
            conn = Util.getConnection();
            String sql = "UPDATE userdetails SET Fullname = ?, Email = ?, Address = ?, Sex = ?, DoB = ? "
                    + "WHERE Phone = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, user.getFullName());
            preStm.setString(2, user.getEmail());
            preStm.setString(3, user.getAddress());
            preStm.setBoolean(4, user.isSex());
            preStm.setDate(5, user.getDob());
            preStm.setString(6, user.getPhone());
            isUpdate = (preStm.executeUpdate() > 0);

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return isUpdate;
    }

    public boolean updateWorkingTimeUser(String workingTimeID) throws ClassNotFoundException, SQLException {
        boolean isUpdate = false;
        try {
            conn = Util.getConnection();
            String sql = "UPDATE userdetails SET Fullname = ?, Email = ?, Address = ?, Sex = ?, DoB = ? "
                    + "WHERE Phone = ?";
            preStm = conn.prepareStatement(sql);
            isUpdate = (preStm.executeUpdate() > 0);

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return isUpdate;
    }

    public UserDetailsDTO getUserDetail(String phone) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM userdetails WHERE Phone = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            rs = preStm.executeQuery();

            if (rs.next()) {
                UserDetailsDTO user = new UserDetailsDTO();
                user.setPhone(rs.getString("Phone"));
                user.setFullName(rs.getString("Fullname"));
                user.setAddress(rs.getString("Address"));
                user.setEmail(rs.getString("Email"));
                return user;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public boolean changePass(String phone, String pass) throws ClassNotFoundException, SQLException {
        boolean isUpdate = false;
        try {
            conn = Util.getConnection();
            String sql = "UPDATE userdetails SET Password = ? WHERE Phone = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, pass);
            preStm.setString(2, phone);
            isUpdate = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return isUpdate;
    }

    public Integer getUserPoint(String phone) throws ClassNotFoundException, SQLException {
        try {
            conn = Util.getConnection();
            String sql = "SELECT Point FROM userdetails WHERE Phone = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            rs = preStm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Point");
            }

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public boolean changePoint(String phone, Integer point) throws ClassNotFoundException, SQLException {
        boolean isUpdate = false;
        try {
            conn = Util.getConnection();
            String sql = "UPDATE userdetails SET Point = ? WHERE Phone = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, point);
            preStm.setString(2, phone);
            isUpdate = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return isUpdate;
    }

    public boolean validPhoneIsUser(String phone) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "SELECT * FROM userdetails WHERE Phone = ? AND Role = 2";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            rs = preStm.executeQuery();

            return rs.next();

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }
}
