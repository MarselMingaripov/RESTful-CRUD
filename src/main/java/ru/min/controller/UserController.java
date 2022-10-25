package ru.min.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.min.entity.User;
import ru.min.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/{username}")
    public Optional<User> find(@PathVariable("username") String username){
        return userRepository.findById(username);
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
