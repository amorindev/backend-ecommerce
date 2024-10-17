package com.fernando.backend_ecommerce.user;

import java.util.List;

import com.fernando.backend_ecommerce.role.RoleModel;

public class UserResponseModel {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userPhone;
    private List<RoleModel> roles;


    public UserResponseModel(Long userId, String userName, String userEmail, String userAddress, String userPhone, List<RoleModel> roles) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }
}
