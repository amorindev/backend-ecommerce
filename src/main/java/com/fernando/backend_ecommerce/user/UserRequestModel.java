package com.fernando.backend_ecommerce.user;

public class UserRequestModel {
    private String userEmail;
    private String userPassword;
    
    public UserRequestModel() {
    }

    public UserRequestModel(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    

   
}
