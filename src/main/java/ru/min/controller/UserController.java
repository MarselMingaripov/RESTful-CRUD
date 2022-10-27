package ru.min.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.min.entity.Role;
import ru.min.entity.User;
import ru.min.repository.RoleRepository;
import ru.min.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.Set;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EntityManager entityManager;

    @GetMapping(path = "/get-all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public User find(@PathVariable("id") Long id) {
        boolean isAdmin = false;
        User user = userRepository.findById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findUserByUsername(auth.getName());
        Set<Role> roles = currentUser.getRoles();

        for (Role role:roles) {
            if (role.getName().toString() == "ROLE_ADMIN") {
                isAdmin = true;
            }
        }

        if (user.getUsername().equals(auth.getName()) || isAdmin) {
            return user;
        }
        return null;
    }
    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public User create(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping(path = "/{username}")
    public void delete(@PathVariable("username") String username){
        userRepository.deleteById(username);
    }

    @PutMapping(path = "/{username}")
    public User update(@PathVariable("username") String username, @RequestBody User user) throws Exception {
        if (userRepository.existsById(username)){
            user.setUsername(username);
            return userRepository.save(user);
        }else {
            throw new Exception("Bad Request!!!");
        }
    }
}
