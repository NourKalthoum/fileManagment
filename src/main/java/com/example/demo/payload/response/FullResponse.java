package com.example.demo.payload.response;

import java.util.List;

public class FullResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mobile;
    private Boolean status;
    private Boolean isActive;
    private String zipCode;
    private List<String> roles;
    private String token;
	



    public FullResponse() {
    }

    public FullResponse(Long id, String firstName, String lastName, String username, String password, String mobile, Boolean status, Boolean isActive, String zipCode, List<String> roles, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.status = status;
        this.isActive = isActive;
        this.zipCode = zipCode;
        this.roles = roles;
        this.token = token;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<String> getRoles() {
        return this.roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public FullResponse id(Long id) {
        setId(id);
        return this;
    }

    public FullResponse firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public FullResponse lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public FullResponse username(String username) {
        setUsername(username);
        return this;
    }

    public FullResponse password(String password) {
        setPassword(password);
        return this;
    }

    public FullResponse mobile(String mobile) {
        setMobile(mobile);
        return this;
    }

    public FullResponse status(Boolean status) {
        setStatus(status);
        return this;
    }

    public FullResponse isActive(Boolean isActive) {
        setIsActive(isActive);
        return this;
    }

    public FullResponse zipCode(String zipCode) {
        setZipCode(zipCode);
        return this;
    }

    public FullResponse roles(List<String> roles) {
        setRoles(roles);
        return this;
    }

    public FullResponse token(String token) {
        setToken(token);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", status='" + isStatus() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", roles='" + getRoles() + "'" +
            ", token='" + getToken() + "'" +
            "}";
    }

}
