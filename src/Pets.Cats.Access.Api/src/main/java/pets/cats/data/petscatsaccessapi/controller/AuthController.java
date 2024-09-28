package pets.cats.data.petscatsaccessapi.controller;

import java.util.Collections;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pets.cats.data.petscatsaccessservices.dto.UserDTO;
import pets.cats.data.petscatsaccesspersistence.entity.Role;
import pets.cats.data.petscatsaccesspersistence.entity.User;
import pets.cats.data.petscatsaccesspersistence.repository.RoleRepository;
import pets.cats.data.petscatsaccesspersistence.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("register")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO)
            throws Exception {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException();
        }

        User user =
                User.builder()
                        .username(userDTO.getUsername())
                        .password(passwordEncoder.encode(userDTO.getPassword()))
                        .build();

        Role roles =
                roleRepository
                        .findByName("USER")
                        .orElseThrow(() -> new NoSuchElementException("USER"));

        user.setRoles(Collections.singletonList(roles));

        userRepository.saveAndFlush(user);

        return new ResponseEntity<>("User created successfully!", HttpStatus.OK);
    }
}