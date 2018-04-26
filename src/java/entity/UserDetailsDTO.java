/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Yuu
 */
public class UserDetailsDTO implements Serializable {

    private String phone;
    private String password;
    private String fullName;
    private boolean sex;
    private String address;
    private String email;
    private Date dob;
    private boolean active;
    private int role;
    private String roleName;
    private Integer point;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(String phone, String fullName, boolean sex, String email, String address, Date dob, int role, String roleName, boolean active) {
        this.phone = phone;
        this.fullName = fullName;
        this.sex = sex;
        this.address = address;
        this.email = email;
        this.dob = dob;
        this.role = role;
        this.roleName = roleName;
        this.active = active;
    }

    public UserDetailsDTO(String phone, String password, String fullName, boolean sex, String email, String address, Date dob, int role) {
        this.phone = phone;
        this.password = password;
        this.fullName = fullName;
        this.sex = sex;
        this.address = address;
        this.email = email;
        this.dob = dob;
        this.role = role;
    }

    public UserDetailsDTO(String phone, String fullName, String email, String address, boolean sex, Date dob) {
        this.phone = phone;
        this.fullName = fullName;
        this.sex = sex;
        this.address = address;
        this.email = email;
        this.dob = dob;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the sex
     */
    public boolean isSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the point
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(Integer point) {
        this.point = point;
    }



}
