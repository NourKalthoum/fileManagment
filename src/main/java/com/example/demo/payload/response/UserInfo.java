package com.example.demo.payload.response;

import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

public class UserInfo {
    private String firstName;
    private String lastName;
    private String mobile;
    private Boolean status;
    private Boolean isActive;
    private String zipCode;


    public UserInfo() {
    }

    public UserInfo(String firstName, String lastName, String mobile, Boolean status, Boolean isActive, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.status = status;
        this.isActive = isActive;
        this.zipCode = zipCode;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Boolean isStatus() {
        return this.status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean isIsActive() {
        return this.isActive;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UserInfo firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public UserInfo lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public UserInfo mobile(String mobile) {
        setMobile(mobile);
        return this;
    }

    public UserInfo status(Boolean status) {
        setStatus(status);
        return this;
    }

    public UserInfo isActive(Boolean isActive) {
        setIsActive(isActive);
        return this;
    }

    public UserInfo zipCode(String zipCode) {
        setZipCode(zipCode);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", status='" + isStatus() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            "}";
    }

}
