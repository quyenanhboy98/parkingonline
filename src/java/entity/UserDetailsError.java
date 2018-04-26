/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Yuu
 */
public class UserDetailsError implements Serializable {

    private String phoneInputErrorFormat;
    private String phoneIsExisted;
    private String currentPasswordIsEmpty;
    private String currentPasswordIsWrong;
    private String passwordIsEmpty;
    private String confirmIsNotMatch;
    private String nameIsEmpty;
    private String emailIsEmpty;
    private String addressIsEmpty;
    private String dobIsWrongFormation;
    private String createError;
    private String pointIsWrongFormation;
    
    public UserDetailsError() {
    }

    /**
     * @return the phoneIsExisted
     */
    public String getPhoneIsExisted() {
        return phoneIsExisted;
    }

    /**
     * @param phoneIsExisted the phoneIsExisted to set
     */
    public void setPhoneIsExisted(String phoneIsExisted) {
        this.phoneIsExisted = phoneIsExisted;
    }

    /**
     * @return the passwordIsEmpty
     */
    public String getPasswordIsEmpty() {
        return passwordIsEmpty;
    }

    /**
     * @param passwordIsEmpty the passwordIsEmpty to set
     */
    public void setPasswordIsEmpty(String passwordIsEmpty) {
        this.passwordIsEmpty = passwordIsEmpty;
    }

    /**
     * @return the confirmIsNotMatch
     */
    public String getConfirmIsNotMatch() {
        return confirmIsNotMatch;
    }

    /**
     * @param confirmIsNotMatch the confirmIsNotMatch to set
     */
    public void setConfirmIsNotMatch(String confirmIsNotMatch) {
        this.confirmIsNotMatch = confirmIsNotMatch;
    }

    /**
     * @return the nameIsEmpty
     */
    public String getNameIsEmpty() {
        return nameIsEmpty;
    }

    /**
     * @param nameIsEmpty the nameIsEmpty to set
     */
    public void setNameIsEmpty(String nameIsEmpty) {
        this.nameIsEmpty = nameIsEmpty;
    }

    /**
     * @return the emailIsEmpty
     */
    public String getEmailIsEmpty() {
        return emailIsEmpty;
    }

    /**
     * @param emailIsEmpty the emailIsEmpty to set
     */
    public void setEmailIsEmpty(String emailIsEmpty) {
        this.emailIsEmpty = emailIsEmpty;
    }

    /**
     * @return the addressIsEmpty
     */
    public String getAddressIsEmpty() {
        return addressIsEmpty;
    }

    /**
     * @param addressIsEmpty the addressIsEmpty to set
     */
    public void setAddressIsEmpty(String addressIsEmpty) {
        this.addressIsEmpty = addressIsEmpty;
    }

    /**
     * @return the dobIsWrongFormation
     */
    public String getDobIsWrongFormation() {
        return dobIsWrongFormation;
    }

    /**
     * @param dobIsWrongFormation the dobIsWrongFormation to set
     */
    public void setDobIsWrongFormation(String dobIsWrongFormation) {
        this.dobIsWrongFormation = dobIsWrongFormation;
    }

    /**
     * @return the createError
     */
    public String getCreateError() {
        return createError;
    }

    /**
     * @param createError the createError to set
     */
    public void setCreateError(String createError) {
        this.createError = createError;
    }

    /**
     * @return the phoneInputErrorFormat
     */
    public String getPhoneInputErrorFormat() {
        return phoneInputErrorFormat;
    }

    /**
     * @param phoneInputErrorFormat the phoneInputErrorFormat to set
     */
    public void setPhoneInputErrorFormat(String phoneInputErrorFormat) {
        this.phoneInputErrorFormat = phoneInputErrorFormat;
    }

    /**
     * @return the currentPasswordIsEmpty
     */
    public String getCurrentPasswordIsEmpty() {
        return currentPasswordIsEmpty;
    }

    /**
     * @param currentPasswordIsEmpty the currentPasswordIsEmpty to set
     */
    public void setCurrentPasswordIsEmpty(String currentPasswordIsEmpty) {
        this.currentPasswordIsEmpty = currentPasswordIsEmpty;
    }

    /**
     * @return the currentPasswordIsWrong
     */
    public String getCurrentPasswordIsWrong() {
        return currentPasswordIsWrong;
    }

    /**
     * @param currentPasswordIsWrong the currentPasswordIsWrong to set
     */
    public void setCurrentPasswordIsWrong(String currentPasswordIsWrong) {
        this.currentPasswordIsWrong = currentPasswordIsWrong;
    }

    public String getPointIsWrongFormation() {
        return pointIsWrongFormation;
    }

    public void setPointIsWrongFormation(String pointIsWrongFormation) {
        this.pointIsWrongFormation = pointIsWrongFormation;
    }

}
