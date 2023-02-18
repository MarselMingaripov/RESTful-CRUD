package ru.min.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.min.entity.Erole;
import ru.min.entity.Role;
import ru.min.entity.User;
import ru.min.repository.RoleRepository;
import ru.min.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "API для работы с пользователями")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(path = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "получить всех пользователей из бд")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "запрос успешно выполнен")
    })
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "получить пользователя по ид")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "запрос успешно выполнен"),
            @ApiResponse(responseCode = "403", description = "запрещен доступ")
    })
    public ResponseEntity<?> find(@PathVariable("id") long id) {
        boolean isAdmin = false;
        User user = userRepository.findById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findUserByUsername(auth.getName());
        Set<Role> roles = currentUser.getRoles();

        for (Role role : roles) {
            if (role.getName().toString() == "ROLE_ADMIN")
                isAdmin = true;
        }
        if (user.getUsername().equals(auth.getName()) || isAdmin) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PostMapping(path = "/", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Operation(summary = "добавить пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "запрос успешно выполнен"),
            @ApiResponse(responseCode = "401", description = "пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "запрещен доступ")
    })
    public ResponseEntity<User> create(@RequestBody User user) {
        User newUser = new User(user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getEmail());
        Set<Role> reqRoles = user.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles.isEmpty()) {
            Role userRole = roleRepository
                    .findByName(Erole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);

        } else {
            for (Role role : reqRoles) {
                if (Erole.ROLE_ADMIN.equals(role.getName())) {
                    Role adminRole = roleRepository
                            .findByName(Erole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                    roles.add(adminRole);
                } else if (Erole.ROLE_MODERATOR.equals(role.getName())) {
                    Role modRole = roleRepository
                            .findByName(Erole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error, Role MODERATOR is not found"));
                    roles.add(modRole);
                } else {
                    Role userRole = roleRepository
                            .findByName(Erole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                    roles.add(userRole);
                }
            }
        }
        newUser.setRoles(roles);
        userRepository.save(newUser);

        return ResponseEntity.ok().body(newUser);
    }


    @DeleteMapping(path = "/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "удалить пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "запрос успешно выполнен"),
            @ApiResponse(responseCode = "401", description = "пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "запрещен доступ")
    })
    public ResponseEntity<Void> delete(@PathVariable("username") String username) {
        userRepository.deleteById(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "изменить пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "запрос успешно выполнен"),
            @ApiResponse(responseCode = "401", description = "пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "запрещен доступ")
    })
    public ResponseEntity<User> update(@PathVariable("username") String username, @RequestBody User user) throws Exception {
        if (userRepository.existsById(username)) {
            user.setUsername(username);
            return ResponseEntity.ok().body(userRepository.save(user));
        } else {
            throw new Exception("Bad Request!!!");
        }
    }
}
