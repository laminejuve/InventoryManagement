package com.lamine.InventoryManagement.controller;

import com.lamine.InventoryManagement.dto.auth.AuthenticationRequest;
import com.lamine.InventoryManagement.dto.auth.AuthenticationResponse;
import com.lamine.InventoryManagement.service.auth.MyUserDetailsService;
import com.lamine.InventoryManagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

@RestController
@RequestMapping (APP_ROOT+"/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @PostMapping ("/token")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){

     authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(
                     request.getLogin(),request.getPassword()
             )
     );

     final  UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getLogin());

     final String jwt = jwtUtil.generateToken(userDetails);

     return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
}
