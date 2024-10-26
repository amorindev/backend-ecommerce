package com.fernando.backend_ecommerce.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.backend_ecommerce.role.RoleModel;
import com.fernando.backend_ecommerce.sale.SaleModel;
import com.fernando.backend_ecommerce.userproduct.UserProductModel;

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
import jakarta.persistence.OneToMany;
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


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserProductModel> userProducts;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<SaleModel> sales;

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

    public List<UserProductModel> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(List<UserProductModel> userProducts) {
        this.userProducts = userProducts;
    }

    public List<SaleModel> getSales() {
        return sales;
    }

    public void setSales(List<SaleModel> sales) {
        this.sales = sales;
    }

    public UserModel(Long userId) {
        this.userId = userId;
    }

    public UserModel() {
    }

    
    
}
