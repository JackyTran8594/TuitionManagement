package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.config.security.JwtTokenProvider;
import com.hta.tuitionmanagement.constants.MessageConstant;
import com.hta.tuitionmanagement.dto.request.LoginRequest;
import com.hta.tuitionmanagement.security.JwtAuthenticationResponse;
import com.hta.tuitionmanagement.security.MessageResponse;
import com.hta.tuitionmanagement.service.impl.UserDetailsServiceImpl;
import com.hta.tuitionmanagement.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername().isEmpty()) {
            return new ResponseEntity(new MessageResponse(false, MessageConstant.USERNAME_OR_PASSWORD_EMPTY), HttpStatus.BAD_REQUEST);
        }
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword())
            );

            List<String> permissions = new ArrayList<>();
            UserDetails userDetails = null;
            String jwt = null;
            String role = null;

            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (principal instanceof UserDetails) {
                    userDetails = (UserDetails) principal;
                    if (userDetails.getUsername().equals("admin")) {
                        role = "ADMIN";
                        permissions.add(role);
                        jwt = jwtTokenProvider.generateToken(userDetails.getUsername(), role, permissions);
                    } else {
                        log.info("----SecurityContextHolder getPrincipal UserDetails:" + userDetails.getUsername());
                        if (DataUtils.notNullOrEmpty(userDetails.getAuthorities())) {
                            role = "USER";
                            permissions = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
                            jwt = jwtTokenProvider.generateToken(userDetails.getUsername(), role, permissions);
                        }
                    }
                } else {
                    log.info("----SecurityContextHolder getPrincipal UserDetails:" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());

                }

            }
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwt, userDetails);
            return ResponseEntity.ok().body(jwtAuthenticationResponse);

        } catch (BadCredentialsException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new MessageResponse(false, MessageConstant.USERNAME_OR_PASSWORD_INVALID), HttpStatus.BAD_REQUEST);
        } catch (UsernameNotFoundException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new MessageResponse(false, MessageConstant.USERNAME_NOTFOUND), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new MessageResponse(false, MessageConstant.SYSTEM_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
