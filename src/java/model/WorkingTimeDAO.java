/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.ParkDTO;
import entity.WorkingTimeDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Util;

/**
 *
 * @author Jun
 */
public class WorkingTimeDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public WorkingTimeDAO() {
    }

    public boolean removeWorkingTime(String workingTimeID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "DELETE FROM workingtime WHERE ID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, workingTimeID);
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public boolean addWorkingTime(WorkingTimeDTO wkt) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "INSERT INTO workingtime(ID, ParkID, TimeStart, TimeEnd) VALUES(?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, wkt.getID());
            preStm.setString(2, wkt.getParkID());
            preStm.setTime(3, wkt.getTimeStart());
            preStm.setTime(4, wkt.getTimeEnd());
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public int updateWorkingTime(WorkingTimeDTO wkt) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "UPDATE workingtime SET TimeStart = ?, TimeEnd = ? WHERE ID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setTime(1, wkt.getTimeStart());
            preStm.setTime(2, wkt.getTimeEnd());
            preStm.setString(3, wkt.getID());
            return preStm.executeUpdate();
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

    public boolean deleteWorkingTime(String workingTimeID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "DELETE FROM workingtime WHERE ID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, workingTimeID);
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public List<WorkingTimeDTO> getListWorkingTime(String parkID) throws SQLException, ClassNotFoundException {
        List<WorkingTimeDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();
            String sql = "SELECT * FROM workingtime WHERE parkID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, parkID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                WorkingTimeDTO worktime = new WorkingTimeDTO();
                worktime.setID(rs.getString("ID"));
                worktime.setParkID(rs.getString("ParkID"));
                worktime.setTimeStart(rs.getTime("TimeStart"));
                worktime.setTimeEnd(rs.getTime("TimeEnd"));
                result.add(worktime);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public WorkingTimeDTO getTimeStartEnd(String workingTimeID) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "SELECT * FROM workingtime WHERE ID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, workingTimeID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                WorkingTimeDTO worktime = new WorkingTimeDTO();
                worktime.setID(rs.getString("ID"));
                worktime.setParkID(rs.getString("ParkID"));
                worktime.setTimeStart(rs.getTime("TimeStart"));
                worktime.setTimeEnd(rs.getTime("TimeEnd"));
                return worktime;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }
}
