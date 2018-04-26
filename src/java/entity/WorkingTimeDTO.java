/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author Jun
 */
public class WorkingTimeDTO implements Serializable{
    private String ID;
    private String ParkID;
    private Time timeStart;
    private Time timeEnd;

    public WorkingTimeDTO() {
    }

    public WorkingTimeDTO(String ID, String ParkID, Time timeStart, Time timeEnd) {
        this.ID = ID;
        this.ParkID = ParkID;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getParkID() {
        return ParkID;
    }

    public void setParkID(String ParkID) {
        this.ParkID = ParkID;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }
    
}
