package com.dh.clinica.controller;

import com.dh.clinica.persistence.entity.AuthenticationRequest;
import com.dh.clinica.persistence.entity.AuthenticationResponse;
import com.dh.clinica.jwt.JwtUtil;
import com.dh.clinica.login.AppUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserService userService;

    Logger logger = Logger.getLogger(PatientController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("<h1>Bienvenido al sistema</h1>");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken (@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /*
    @PostMapping("/")
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUser user){
        logger.info("Se creó un nuevo usuario con email: " + user.getEmail());
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updatePatient(@RequestBody AppUser user, @PathVariable Long id) {
        ResponseEntity<AppUser> response = null;

        if(user.getId() != null && userService.findUserById(user.getId()).isPresent() && user.getId()==id){
            logger.info("Se actualizó el usuario: " + user);
            response = ResponseEntity.ok(userService.updateUser(user));
        } else {
            logger.error("Fallo al intentar actualizar usuario: " + user);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

     */

}






