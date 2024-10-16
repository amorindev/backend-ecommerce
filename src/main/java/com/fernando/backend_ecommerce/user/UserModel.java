package com.fernando.backend_ecommerce.user;

import java.util.List;

import com.fernando.backend_ecommerce.role.RoleModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false, length = 250)
    private String userName;

    @Column(name = "user_email", nullable = false, length = 200)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 250)
    private String userPassword;

    @Column(name = "user_address", nullable = false, length = 250)
    private String userAddress;

    @Column(name = "user_phone", nullable = false, length = 20)
    private String userPhone;

    
    @ManyToMany(
        fetch = FetchType.LAZY,    
        cascade = CascadeType.ALL
    )
    @JoinTable(
        name = "tb_user_role", 
        joinColumns = @JoinColumn(name= "user_id"),
        inverseJoinColumns = @JoinColumn(name ="role_id")
    )
    private List<RoleModel> roles;



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


    public String getUserPassword() {
        return userPassword;
    }


    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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


    public UserModel(Long userId, String userName, String userEmail, String userPassword, String userAddress,
            String userPhone, List<RoleModel> roles) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.roles = roles;
    }


    public UserModel(String userName, String userEmail, String userPassword, String userAddress, String userPhone,
            List<RoleModel> roles) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.roles = roles;
    }


    public UserModel() {
    }
}
