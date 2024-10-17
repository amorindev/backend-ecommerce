package com.fernando.backend_ecommerce.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.backend_ecommerce.utils.ResponseMessage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;   

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> registerUser(@RequestBody UserModel userModel) {
        try {
            
            if(!userService.registerUserDto(userModel)){
                String msg = "El usuario con el correo: " + userModel.getUserEmail() + "ya existe";
                return ResponseEntity.badRequest().body(new ResponseMessage(msg));    
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            String msg = "Error al registrar al usuario: " + e.getMessage();
            return ResponseEntity.badRequest().body(new ResponseMessage(msg));
        }        
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestModel userRequest) {
        Optional<UserModel> user = userService.loginUserDto(userRequest.getUserEmail(),userRequest.getUserPassword());

        if(user.isPresent()) {
            UserModel userModel = user.get();
            UserResponseModel userResponse = new UserResponseModel(
                userModel.getUserId(), 
                userModel.getUserName(),
                 userModel.getUserEmail(),userModel.getUserAddress(),
                 userModel.getUserPhone(),
                 userModel.getRoles());
            return ResponseEntity.ok(userResponse);

        } else {
            String msg = "Credenciales inválidas";
            return ResponseEntity.badRequest().body(new ResponseMessage(msg));
        }
    }
    
}
