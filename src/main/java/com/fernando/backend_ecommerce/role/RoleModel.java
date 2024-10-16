package com.fernando.backend_ecommerce.role;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.backend_ecommerce.user.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", nullable = false, length = 90)
    private String roleName;


    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<UserModel> users;

    public RoleModel() {
    }

    public RoleModel(Long roleId, String roleName, List<UserModel> users) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    
    
}
