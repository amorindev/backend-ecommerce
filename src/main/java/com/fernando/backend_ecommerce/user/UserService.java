package com.fernando.backend_ecommerce.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.backend_ecommerce.role.IRoleRepository;
import com.fernando.backend_ecommerce.role.RoleModel;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;


    // revisar si e tipo de dato es correcto para guardar a contraseña encriptada
    public boolean registerUserDto(UserModel user){
        // verificar si el usuario existe
        Optional<UserModel> existingUser = userRepository.findByUserEmail(user.getUserEmail());
        if(existingUser.isPresent()){
            return false;
        }   
        
        //Asignar un role a un usuario
        List<RoleModel> roles = new ArrayList<>();
        Optional<RoleModel> role =roleRepository.findByRoleName("CLIENTE");
        if (role.isPresent()) {
            roles.add(role.get());            
        }
        user.setRoles(roles);

        //Guardar usuario en la base de datos
        userRepository.save(user);

        return true;
    }

    public Optional<UserModel> loginUserDto(String email, String password) {
        Optional<UserModel> user = userRepository.findByUserEmail(email);

        if(!user.isPresent() ){
            return Optional.empty();
        }

        if(password.equals(user.get().getUserPassword())){
            return user;
        }
        return Optional.empty();
    }
}




