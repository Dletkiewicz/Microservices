package pl.dariusz.db.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<User> getUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Integer age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "age") String[] sort) {

        User example = new User();
        example.setFirstName(firstName);
        example.setLastName(lastName);
        example.setGender(gender);
        example.setAge(age);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return userService.getUsersByExample(example, pageable);
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
