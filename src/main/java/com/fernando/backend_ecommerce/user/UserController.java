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
                String msg = "E usuarioya existe" + userModel.getUserEmail();
                return ResponseEntity.badRequest().body(new ResponseMessage(msg));    
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            String msg = "Error al registrar al usuario: " + e.getMessage();
            return ResponseEntity.badRequest().body(new ResponseMessage(msg));
        }        
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {
        Optional<UserModel> user = userService.loginUserDto(userRequest.getUserEmail(),userRequest.getUserPassword());

        if(user.isPresent()) {
            UserModel userModel = user.get();
            UserResponse userResponse = new UserResponse(
                userModel.getUserId(), 
                userModel.getUserName(),
                 userModel.getUserEmail(),userModel.getUserAddress(),
                 userModel.getUserPhone(),
                 userModel.getRoles());
            return ResponseEntity.ok(userResponse);

        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
}
