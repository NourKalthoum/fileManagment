package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.request.VerificationRequest;
import com.example.demo.payload.response.FullResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
 
    @Autowired
    AuthenticationManager authenticationManager;
  
    @Autowired
    UserRepository userRepository;
  
    @Autowired
    UserRepo userRepo;
  
    @Autowired
    RoleRepo roleRepository;
  
    @Autowired
    PasswordEncoder encoder;
  
    @Autowired
    JwtUtils jwtUtils;
  
    @Autowired
    UserService userService;
  
  
  
    @PostMapping("/signin")
    public Object authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
      try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
  
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
        User User = new User();
        User = userRepo.findByUsername(userDetails.getUsername());
        if (User.getIsActive() == false)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: You have not completed the registration process. "); 
        FullResponse fullResponse = new FullResponse();
        fullResponse.setId(userDetails.getId());
        fullResponse.setUsername(userDetails.getUsername());
        fullResponse.setToken(jwt);
        fullResponse.setRoles(roles);
        fullResponse.setFirstName(User.getFirstName());
        fullResponse.setLastName(User.getLastName());
        fullResponse.setPassword(User.getPassword());
        fullResponse.setMobile(User.getMobile());
        fullResponse.setIsActive(User.getIsActive());
        fullResponse.setStatus(User.getStatus());
        fullResponse.setZipCode(User.getZipCode());
        return fullResponse;
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Username or Password is not true");
      }
    }
  
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
      if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
      }
  
      // Create new user's account
  
      User user = new User(signUpRequest.getUsername(),
          encoder.encode(signUpRequest.getPassword()),
          signUpRequest.getStatus());
  
      user.setFirstName(signUpRequest.getFirstName());
      user.setLastName(signUpRequest.getLastName());
      user.setMobile(signUpRequest.getMobile());
      user.setStatus(false);
      user.setIsActive(false);
      String zipCode = genint(user);
      user.setZipCode(zipCode);
  
      Set<String> strRoles = signUpRequest.getRole();
      Set<Role> roles = new HashSet<>();
  
      if (strRoles == null) {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
      } else {
        strRoles.forEach(role -> {
          switch (role) {
            case "admin":
              Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(adminRole);
  
              break;
            default:
              Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(userRole);
          }
        });
      }
  
      user.setRoles(roles);
      userRepository.save(user);
  
      return ResponseEntity
          .ok(new MessageResponse("Please enter a code number to evaluate the completion of the registration process "));
    }
  
    @PostMapping(path = "/verification")
    public Object verification(@RequestBody VerificationRequest verificationRequest) {
      Optional<User> user = userRepository.findByUsername(verificationRequest.getUsername());
      
      try {
        if (user == null) {
          return ResponseEntity.badRequest().body(new MessageResponse("Error: Username not Found"));
        }
        if (verificationRequest.getZipCode().equals(user.get().getZipCode()) == false) {
          return ResponseEntity.badRequest().body(new MessageResponse("Error: Code is not true"));
        }
        boolean isMatch = encoder.matches(verificationRequest.getPassword(), user.get().getPassword());
        if(isMatch == false){
          return ResponseEntity.badRequest().body(new MessageResponse("Error: Password not match"));
        }
        user.get().setStatus(true);
        user.get().setIsActive(true);
        userRepository.save(user.get());
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.get().getUsername(), verificationRequest.getPassword()));
  
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);
  
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream()
          .map(item -> item.getAuthority())
          .collect(Collectors.toList());
      User User = new User();
      User = userRepo.findByUsername(userDetails.getUsername());
  
      FullResponse fullResponse = new FullResponse();
      fullResponse.setId(userDetails.getId());
      fullResponse.setUsername(userDetails.getUsername());
      fullResponse.setToken(jwt);
      fullResponse.setRoles(roles);
      fullResponse.setFirstName(User.getFirstName());
      fullResponse.setLastName(User.getLastName());
      fullResponse.setPassword(User.getPassword());
      fullResponse.setMobile(User.getMobile());
      fullResponse.setIsActive(User.getIsActive());
      fullResponse.setStatus(User.getStatus());
      fullResponse.setZipCode(User.getZipCode());
      return fullResponse;
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
      }
    }
  
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LoginRequest loginRequest) {
      String userName = loginRequest.getUsername();
      Optional<User> user = userRepository.findByUsername(userName);
      user.get().setStatus(false);
      userRepository.save(user.get());
      return null;
    }
  
    @RequestMapping("/testInteger")
    public String genint(User user) {
  
      int intRange;
      try {
  
        for (int i = 0; i < 10; i++) {
          intRange = generateRandomIntIntRange(1000, 10000);
          String num = Integer.toString(intRange);
          user.setZipCode(num);
          userRepository.save(user);
        }
      } catch (Exception e) {
  
        e.printStackTrace();
      }
  
      return user.getZipCode();
  
    }
  
    public static int generateRandomIntIntRange(int min, int max) {
      Random r = new Random();
      return r.nextInt((max - min) + 1) + min;
  
    }    
}
