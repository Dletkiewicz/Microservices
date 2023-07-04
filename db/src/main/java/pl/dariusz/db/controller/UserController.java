package pl.dariusz.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dariusz.db.service.UserService;
import pl.dariusz.db.user.User;

import java.util.List;

@RestController
@RequestMapping("/db/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/sort/age")
    public List<User> getUsersSortedByAgeAscending(@RequestParam(defaultValue = "true") boolean ascending) {
        return userService.getUsersSortedByAge(ascending);
    }

    @GetMapping("/users/sort/firstname")
    public List<User> getUsersSortedByFirstName(@RequestParam(defaultValue = "asc") String order) {
        return userService.getUsersSortedByFirstName(order);
    }

    @GetMapping("/users/filter/age")
    public List<User> getUsersFilteredByAge(@RequestParam int minAge, @RequestParam int maxAge) {
        return userService.getUsersFilteredByAge(minAge, maxAge);
    }

    @GetMapping("/users/filter/gender")
    public List<User> getUsersFilteredByGender(@RequestParam String gender) {
        return userService.getUsersFilteredByGender(gender);
    }

    @GetMapping("/users/paged")
    public Page<User> getUsersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUsersPaged(pageable);
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User has been added to db");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User of id: " + id + " has been deleted");
    }
}
