package com.htp.controller.users;

import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.controller.requests.users.UserUpdateRequest;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.security.model.AuthenticationRequest;
import com.htp.security.model.AuthenticationResponse;
import com.htp.security.util.JwtUtil;
import com.htp.entity.users.Users;
import com.htp.service.users.MyUserDetailsService;
import com.htp.service.users.UsersService;

import java.security.Principal;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsersController {
    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;
    private final ConversionService conversionService;

    @GetMapping("/allusers")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/usersrole")
    public ResponseEntity<?> getUsersRoleAdmin(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(usersService.getRoleFromToken(authHeader));
    }

    @GetMapping("/isowneroradmin")
    public ResponseEntity<?> isUserOwnerOrAdmin(@RequestParam Long id, @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(usersService.isUserOwnerOrAdminByItemId(id, authHeader));
    }

    @GetMapping("/ownername")
    public ResponseEntity<?> ownerUserName(@RequestParam Long id) {
        return ResponseEntity.ok(usersService.ownerUserName(id));
    }

    @GetMapping("/userbyusername")
    public ResponseEntity<?> getUserByUserName(@RequestParam String userName) {
        return ResponseEntity.ok(usersService.findByUserName(userName));
    }

    @PostMapping("/authenticate")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserName(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.ok("");
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PutMapping("/updateuser")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserUpdateRequest request) {
        return ResponseEntity.ok(usersService.updateUser(conversionService.convert(request, Users.class)));
    }

    @PostMapping("/registration")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> createConvertedUser(@RequestBody @Valid UserCreateRequest request) {
        return ResponseEntity.ok(usersService.saveUser(conversionService.convert(request, Users.class)));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok("User was deleted");
    }
}
