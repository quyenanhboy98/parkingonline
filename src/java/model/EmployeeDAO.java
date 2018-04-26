/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.EmployeeDTO;
import entity.UserDetailsDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import utils.Util;

/**
 *
 * @author Yuu
 */
public class EmployeeDAO extends UserDetailsDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public EmployeeDAO() {
        super();
    }

    public EmployeeDTO checkActivationParkEmployee(UserDetailsDTO user) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT U.ParkID, P.ParkName, WKT.TimeStart, WKT.TimeEnd "
                    + "FROM userdetails AS U, park AS P, workingtime AS WKT "
                    + "WHERE U.ParkID =  P.ParkID AND U.WorkingTimeID = WKT.ID "
                    + "AND P.Active = TRUE "
                    + "AND U.Phone = ? ";

            preStm = conn.prepareStatement(sql);
            preStm.setString(1, user.getPhone());
            rs = preStm.executeQuery();
            if (rs.next()) {
                EmployeeDTO emp = new EmployeeDTO();
                emp.setPhone(user.getPhone());
                emp.setFullName(user.getFullName());
                emp.setSex(user.isSex());
                emp.setEmail(user.getEmail());
                emp.setAddress(user.getAddress());
                emp.setDob(user.getDob());
                emp.setActive(user.isActive());
                emp.setRole(user.getRole());
                emp.setRoleName(user.getRoleName());
                emp.setParkID(rs.getString("ParkID"));
                emp.setParkName(rs.getString("ParkName"));
                emp.setTimeStart(rs.getTime("TimeStart"));
                emp.setTimeEnd(rs.getTime("TimeEnd"));
                return emp;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public List<EmployeeDTO> getListEmployee(String parkID) throws SQLException, ClassNotFoundException {
        List<EmployeeDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT Phone, Fullname, Sex, Email, UD.Address, DoB, UD.Active, Role, RoleName, ParkName, P.ParkID, WKT.TimeStart, WKT.TimeEnd "
                    + "FROM userdetails AS UD, userrole AS R, park AS P, workingtime AS WKT "
                    + "WHERE UD.WorkingTimeID = WKT.ID AND UD.Role = R.RoleID AND UD.ParkID = P.ParkID AND (R.RoleID = 3  OR R.RoleID = 4) ";

            if (parkID == null || parkID.isEmpty()) {
                preStm = conn.prepareStatement(sql);
            } else {
                sql += "AND UD.ParkID = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, parkID);
            }

            rs = preStm.executeQuery();
            while (rs.next()) {
                EmployeeDTO emp = new EmployeeDTO();
                emp.setPhone(rs.getString("Phone"));
                emp.setFullName(rs.getString("Fullname"));
                emp.setSex(rs.getBoolean("Sex"));
                emp.setEmail(rs.getString("Email"));
                emp.setAddress(rs.getString("Address"));
                emp.setDob(rs.getDate("DoB"));
                emp.setActive(rs.getBoolean("Active"));
                emp.setRole(rs.getInt("Role"));
                emp.setRoleName(rs.getString("RoleName"));
                emp.setParkName(rs.getString("ParkName"));
                emp.setParkID(rs.getString("ParkID"));
                emp.setTimeStart(rs.getTime("TimeStart"));
                emp.setTimeEnd(rs.getTime("TimeEnd"));
                result.add(emp);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<EmployeeDTO> searchListEmployee(String value, String parkID) throws SQLException, ClassNotFoundException {
        List<EmployeeDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT Phone, Fullname, Sex, Email, UD.Address, DoB, UD.Active, Role, RoleName, ParkName, WKT.TimeStart, WKT.TimeEnd, P.ParkID "
                    + "FROM userdetails AS UD, userrole AS R, park AS P, workingtime AS WKT "
                    + "WHERE WKT.ID = UD.WorkingTimeID AND UD.Role = R.RoleID AND UD.ParkID = P.ParkID AND (R.RoleID = 3 OR R.RoleID = 4) "
                    + "AND (UD.Phone LIKE ? OR UD.Fullname LIKE ?) ";

            if (parkID.isEmpty()) {
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + value + "%");
                preStm.setString(2, "%" + value + "%");
            } else {
                sql += "AND UD.ParkID = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + value + "%");
                preStm.setString(2, "%" + value + "%");
                preStm.setString(3, parkID);

            }

            rs = preStm.executeQuery();
            while (rs.next()) {
                EmployeeDTO emp = new EmployeeDTO();
                emp.setPhone(rs.getString("Phone"));
                emp.setFullName(rs.getString("Fullname"));
                emp.setSex(rs.getBoolean("Sex"));
                emp.setEmail(rs.getString("Email"));
                emp.setAddress(rs.getString("Address"));
                emp.setDob(rs.getDate("DoB"));
                emp.setActive(rs.getBoolean("Active"));
                emp.setRole(rs.getInt("Role"));
                emp.setRoleName(rs.getString("RoleName"));
                emp.setParkName(rs.getString("ParkName"));
                emp.setTimeStart(rs.getTime("TimeStart"));
                emp.setTimeEnd(rs.getTime("TimeEnd"));
                emp.setParkID(rs.getString("ParkID"));
                result.add(emp);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<EmployeeDTO> searchListStaff(String value, String parkID) throws SQLException, ClassNotFoundException {
        List<EmployeeDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT Phone, Fullname, Sex, Email, UD.Address, DoB, UD.Active, Role, RoleName, ParkName, WKT.TimeStart, WKT.TimeEnd "
                    + "FROM userdetails AS UD, userrole AS R, park AS P, workingtime AS WKT "
                    + "WHERE WKT.ID = UD.WorkingTimeID AND UD.Role = R.RoleID AND UD.ParkID = P.ParkID AND R.RoleID = 4 "
                    + "AND (UD.Phone LIKE ? OR UD.Fullname LIKE ?) ";

            if (parkID.isEmpty()) {
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + value + "%");
                preStm.setString(2, "%" + value + "%");
            } else {
                sql += "AND UD.ParkID = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + value + "%");
                preStm.setString(2, "%" + value + "%");
                preStm.setString(3, parkID);

            }

            rs = preStm.executeQuery();
            while (rs.next()) {
                EmployeeDTO emp = new EmployeeDTO();
                emp.setPhone(rs.getString("Phone"));
                emp.setFullName(rs.getString("Fullname"));
                emp.setSex(rs.getBoolean("Sex"));
                emp.setEmail(rs.getString("Email"));
                emp.setAddress(rs.getString("Address"));
                emp.setDob(rs.getDate("DoB"));
                emp.setActive(rs.getBoolean("Active"));
                emp.setRole(rs.getInt("Role"));
                emp.setRoleName(rs.getString("RoleName"));
                emp.setParkName(rs.getString("ParkName"));
                emp.setTimeStart(rs.getTime("timeStart"));
                emp.setTimeEnd(rs.getTime("timeEnd"));
                result.add(emp);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public EmployeeDTO getEmployeeDetail(String phone) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM userdetails WHERE Phone = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            rs = preStm.executeQuery();

            if (rs.next()) {
                EmployeeDTO emp = new EmployeeDTO();
                emp.setPhone(rs.getString("Phone"));
                emp.setFullName(rs.getString("Fullname"));
                emp.setParkID(rs.getString("ParkID"));
                return emp;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public boolean addEmployee(EmployeeDTO emp) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "INSERT INTO userdetails(Phone, Password, Fullname, Sex, Email, Address, DoB, Role, ParkID, WorkingTimeID) VALUES(?,?,?,?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, emp.getPhone());
            preStm.setString(2, emp.getPassword());
            preStm.setString(3, emp.getFullName());
            preStm.setBoolean(4, emp.isSex());
            preStm.setString(5, emp.getEmail());
            preStm.setString(6, emp.getAddress());
            preStm.setDate(7, emp.getDob());
            preStm.setInt(8, emp.getRole());
            preStm.setString(9, emp.getParkID());
            preStm.setString(10, emp.getWorkingTimeID());
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public boolean updateEmployee(EmployeeDTO emp) throws ClassNotFoundException, SQLException {
        try {
            conn = Util.getConnection();
            String sql = "UPDATE userdetails SET Fullname = ?, Email = ?, Address = ?, Sex = ?, DoB = ?, WorkingTimeID = ? WHERE Phone = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, emp.getFullName());
            preStm.setString(2, emp.getEmail());
            preStm.setString(3, emp.getAddress());
            preStm.setBoolean(4, emp.isSex());
            preStm.setDate(5, emp.getDob());
            preStm.setString(6, emp.getWorkingTimeID());
            preStm.setString(7, emp.getPhone());
            return preStm.executeUpdate() > 0;

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

    public boolean updateWorkingTimeForEmp() throws ClassNotFoundException, SQLException {
        try {
            conn = Util.getConnection();
            String sql = "UPDATE userdetails SET WorkingTimeID = 'NWKT' WHERE WorkingTimeID IS NULL";
            preStm = conn.prepareStatement(sql);
            return preStm.executeUpdate() > 0;

        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

    public List<LocalTime> checkEmployeeWorkingTime(String phone) throws ClassNotFoundException, SQLException {
        try {
            conn = Util.getConnection();
            String sql = "SELECT TimeStart, TimeEnd FROM workingtime AS W, userdetails as U "
                    + "WHERE W.ID = U.WorkingTimeID "
                    + "AND  U.Phone = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            rs = preStm.executeQuery();
            if (rs.next()) {
                Time startSql = rs.getTime("TimeStart");
                Time endSql = rs.getTime("TimeEnd");
                if (startSql != null && endSql != null) {
                    List<LocalTime> result = new ArrayList<>();
                    LocalTime start = startSql.toLocalTime();
                    LocalTime end = endSql.toLocalTime();
                    result.add(start);
                    result.add(end);
                    return result;
                }

            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }
}
