package pl.dariusz.db.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dariusz.db.service.UserService;
import pl.dariusz.db.user.User;
import pl.dariusz.db.user.UserSearchable;


import java.util.Optional;

@RestController
@RequestMapping("/db/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public Page<User> getUsersPaged(Pageable pageable, UserSearchable searchable) {
        return userService.findUsers(pageable, searchable);
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUser(@RequestParam Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
       return userService.deleteUser(id);
    }
}
