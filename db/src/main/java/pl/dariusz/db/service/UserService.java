package pl.dariusz.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.dariusz.db.repository.UserRepository;
import pl.dariusz.db.user.User;
import pl.dariusz.db.user.UserSearchable;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findUsers(Pageable pageable, UserSearchable searchable) {
        return userRepository.findAllWithSearchAndPagination(pageable, searchable);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public ResponseEntity<String> addUser(User user){
        userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<String> deleteUser(Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok("User of id: " + id + " has been deleted");
    }


}
