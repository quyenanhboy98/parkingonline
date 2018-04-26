/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.FeedbackDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.Util;

/**
 *
 * @author Jun
 */
public class FeedbackDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public boolean addFeedback(FeedbackDTO fb) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "INSERT INTO feedback(Phone, Title, Message, FeedbackTime, ParkID) VALUES(?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, fb.getPhone());
            preStm.setString(2, fb.getTitle());
            preStm.setString(3, fb.getMessage());
            preStm.setTimestamp(4, fb.getFeedbackTime());
            if (fb.getParkID().equals("None")) {
                preStm.setNull(5, java.sql.Types.VARCHAR);
            } else {
                preStm.setString(5, fb.getParkID());
            }
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public FeedbackDTO getFeedbackByID(String feedbackID) throws SQLException, ClassNotFoundException {

        try {
            conn = Util.getConnection();
            String sql = "SELECT * FROM feedback WHERE ID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, feedbackID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                FeedbackDTO fb = new FeedbackDTO();
                fb.setId(rs.getInt("ID"));
                fb.setPhone(rs.getString("Phone"));
                fb.setMessage(rs.getString("Message"));
                fb.setRepMessage(rs.getString("ReplyMessage"));
                fb.setTitle(rs.getString("Title"));
                fb.setFeedbackTime(rs.getTimestamp("FeedbackTime"));
                return fb;
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return null;
    }

    public List<FeedbackDTO> getListFeedback(String feedbackID) throws SQLException, ClassNotFoundException {
        List<FeedbackDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM feedback";

            preStm = conn.prepareStatement(sql);
            if (feedbackID != null) {
                sql += " WHERE ParkID = ? ORDER BY FeedbackTime DESC";
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, feedbackID);
            } else {
                sql += " WHERE ParkID IS NULL ORDER BY FeedbackTime DESC";
                preStm = conn.prepareStatement(sql);
            }
            rs = preStm.executeQuery();
            while (rs.next()) {
                FeedbackDTO fb = new FeedbackDTO();
                fb.setId(rs.getInt("ID"));
                fb.setPhone(rs.getString("Phone"));
                fb.setMessage(rs.getString("Message"));
                fb.setRepMessage(rs.getString("ReplyMessage"));
                fb.setTitle(rs.getString("Title"));
                fb.setFeedbackTime(rs.getTimestamp("FeedbackTime"));
                result.add(fb);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<FeedbackDTO> searchFeedbackByDate(Timestamp from, Timestamp to, String feedbackID) throws SQLException, ClassNotFoundException {
        List<FeedbackDTO> result = new ArrayList<>();
        try {
            conn = Util.getConnection();

            String sql = "SELECT * FROM feedback WHERE FeedbackTime >= ? AND FeedbackTime <= ?";

            if (feedbackID != null) {
                sql += " AND ParkID = ? ORDER BY FeedbackTime DESC";
                preStm = conn.prepareStatement(sql);
                preStm.setTimestamp(1, from);
                preStm.setTimestamp(2, to);
                preStm.setString(3, feedbackID);
            } else {
                sql += " AND ParkID IS NULL ORDER BY FeedbackTime DESC";
                preStm = conn.prepareStatement(sql);
                preStm.setTimestamp(1, from);
                preStm.setTimestamp(2, to);
            }

            rs = preStm.executeQuery();
            while (rs.next()) {
                FeedbackDTO fb = new FeedbackDTO();
                fb.setId(rs.getInt("ID"));
                fb.setPhone(rs.getString("Phone"));
                fb.setTitle(rs.getString("Title"));
                fb.setMessage(rs.getString("Message"));
                fb.setRepMessage(rs.getString("ReplyMessage"));
                fb.setFeedbackTime(rs.getTimestamp("FeedbackTime"));
                result.add(fb);
            }
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public boolean deleteFeedback(String feedbackID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = Util.getConnection();
            String sql = "DELETE FROM feedback WHERE ID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, feedbackID);
            result = (preStm.executeUpdate() > 0);
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
        return result;
    }

    public boolean replyFeedback(String feedbackID, String replyMessage) throws SQLException, ClassNotFoundException {
        try {
            conn = Util.getConnection();
            String sql = "UPDATE feedback SET ReplyMessage = ? WHERE ID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, replyMessage);
            preStm.setString(2, feedbackID);
            return preStm.executeUpdate() > 0;
        } finally {
            Util.closeConnection(conn, preStm, rs);
        }
    }

}
