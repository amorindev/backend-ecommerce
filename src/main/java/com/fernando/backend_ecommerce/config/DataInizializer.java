package com.fernando.backend_ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.fernando.backend_ecommerce.category.CategoryModel;
import com.fernando.backend_ecommerce.category.ICategoryRepository;
import com.fernando.backend_ecommerce.role.IRoleRepository;
import com.fernando.backend_ecommerce.role.RoleModel;
import com.fernando.backend_ecommerce.user.IUserRepository;
import com.fernando.backend_ecommerce.user.UserModel;

import jakarta.transaction.Transactional;




@Configuration
@Transactional
public class DataInizializer implements CommandLineRunner {

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        //INsert sql
        initializeRoles();
        initializeSQL();

        Optional<UserModel> adminUser = userRepository.findByUserEmail(adminEmail);
        if (adminUser.isPresent()) {
            throw new RuntimeException("El usuario administrador ya existe");
        }

        Optional<RoleModel> adminRole = roleRepository.findByRoleName("ADMIN");
        if (adminRole.isEmpty()) {
            throw new RuntimeException("El rol ADMIN no existe, asegúrate de que esté creado.");
        }

        
        UserModel userAdmin = new UserModel();
        userAdmin.setUserName("Admin");
        userAdmin.setUserEmail(adminEmail);
        userAdmin.setUserPassword(adminPassword);
        userAdmin.setUserAddress("Admin address");
        userAdmin.setUserPhone("963333333");
        
        List<RoleModel> roles = new ArrayList<>();
        roles.add(adminRole.get());
        userAdmin.setRoles(roles);



        userRepository.save(userAdmin); 
    }

    private void initializeRoles(){
        if(roleRepository.findByRoleName("ADMIN").isEmpty()){
            RoleModel adminRole = new RoleModel();
            adminRole.setRoleName("ADMIN");
            roleRepository.save(adminRole);
            System.out.println("Rol ADMIN creado.");
        }
        if (roleRepository.findByRoleName("CLIENT").isEmpty()) {
            RoleModel userRole = new RoleModel();
            userRole.setRoleName("CLIENT");
            roleRepository.save(userRole);
            System.out.println("Rol USER creado.");
        }
    }

    private void initializeSQL() {
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("Hombre");
        categoryNames.add("Mujer");
        categoryNames.add("Niños");
        categoryNames.add("Sports Equipment");

        
        for (var categoryName : categoryNames) {
            CategoryModel category = new CategoryModel();
            category.setName(categoryName);
            categoryRepository.save(category);
        }
    }
}
