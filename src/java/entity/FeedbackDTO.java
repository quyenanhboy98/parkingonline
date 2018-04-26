/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Jun
 */
public class FeedbackDTO implements Serializable {
    private int id;
    private String phone;
    private String title;
    private String message;
    private String repMessage;
    private Timestamp feedbackTime;
    private String parkID;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String phone, String title, String message, Timestamp feedbackTime, String parkID) {
        this.phone = phone;
        this.title = title;
        this.message = message;
        this.feedbackTime = feedbackTime;
        this.parkID = parkID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Timestamp feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getParkID() {
        return parkID;
    }

    public void setParkID(String parkID) {
        this.parkID = parkID;
    }

    /**
     * @return the repMessage
     */
    public String getRepMessage() {
        return repMessage;
    }

    /**
     * @param repMessage the repMessage to set
     */
    public void setRepMessage(String repMessage) {
        this.repMessage = repMessage;
    }
    
    
}
