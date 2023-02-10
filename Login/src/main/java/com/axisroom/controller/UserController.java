package com.axisroom.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axisroom.dto.LoginDto;
import com.axisroom.dto.RegistrationDto;
import com.axisroom.entity.Role;
import com.axisroom.entity.User;
import com.axisroom.repository.RoleRepository;
import com.axisroom.repository.UserRepository;

@RestController
@RequestMapping("/api/login")
public class UserController {
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("login successfully!.", HttpStatus.OK);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto){

       
        User user = new User();
        user.setName(registrationDto.getName());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

//        Role roles = roleRepository.findByName("roles").get();
//        user.setRoles(Collections.singleton(roles));
        

        if(userRepository.existsByUsername(registrationDto.getUsername())){
            return new ResponseEntity<>("Username is already Present", HttpStatus.BAD_REQUEST);
        }

       
        if(userRepository.existsByEmail(registrationDto.getEmail())){
            return new ResponseEntity<>("Email is already present", HttpStatus.BAD_REQUEST);
        }
        
        userRepository.save(user);

        return new ResponseEntity<>(" registered successfully", HttpStatus.OK);
		
    }
    
}


