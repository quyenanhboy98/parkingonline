/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Yuu
 */
public class EmployeeDTO extends UserDetailsDTO implements Serializable {

    private String parkID;
    private String parkName;
    private Time timeStart;
    private Time timeEnd;
    private String workingTimeID;

    public EmployeeDTO() {
        super();
    }

    //servlet use: GetDetailsUserServlet
    public EmployeeDTO(String phone, String fullName, String email, String address, boolean sex, Date dob, String parkID, String parkName, Time timeStart, Time timeEnd) {
        super(phone, fullName, email, address, sex, dob);
        this.parkID = parkID;
        this.parkName = parkName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public EmployeeDTO(String phone, String fullName, String email, String address, boolean sex, Date dob, String workingTimeID) {
        super(phone, fullName, email, address, sex, dob);
        this.workingTimeID = workingTimeID;
    }

    public EmployeeDTO(String phone, String password, String fullName, boolean sex, String email, String address, Date dob, int role, String parkID) {
        super(phone, password, fullName, sex, email, address, dob, role);
        this.parkID = parkID;
    }

    public EmployeeDTO(String phone, String fullName, boolean sex, String email, String address, Date dob, int role, String roleName, boolean active, String parkID) {
        super(phone, fullName, sex, email, address, dob, role, roleName, active);
        this.parkID = parkID;
    }

    /**
     * @return the parkID
     */
    public String getParkID() {
        return parkID;
    }

    /**
     * @param parkID the parkID to set
     */
    public void setParkID(String parkID) {
        this.parkID = parkID;
    }

    /**
     * @return the parkName
     */
    public String getParkName() {
        return parkName;
    }

    /**
     * @param parkName the parkName to set
     */
    public void setParkName(String parkName) {
        this.parkName = parkName;
    }
    
        /**
     * @return the timeStart
     */
    public Time getTimeStart() {
        return timeStart;
    }

    /**
     * @param timeStart the timeStart to set
     */
    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * @return the timeEnd
     */
    public Time getTimeEnd() {
        return timeEnd;
    }

    /**
     * @param timeEnd the timeEnd to set
     */
    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * @return the workingTimeID
     */
    public String getWorkingTimeID() {
        return workingTimeID;
    }

    /**
     * @param workingTimeID the workingTimeID to set
     */
    public void setWorkingTimeID(String workingTimeID) {
        this.workingTimeID = workingTimeID;
    }

}
