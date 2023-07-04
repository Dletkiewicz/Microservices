package pl.dariusz.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.dariusz.db.repository.UserRepository;
import pl.dariusz.db.user.User;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersByFirstName(String name){
        return userRepository.findByFirstNameIgnoreCase(name);
    }

    public List<User> getUsersByLastName(String name){
        return userRepository.findByLastNameIgnoreCase(name);
    }

    public List<User> getUsersSortedByAge(boolean ascending) {
        String sortBy = ascending ? "age" : "age desc";
        return userRepository.findAll(Sort.by(sortBy));
    }

    public List<User> getUsersSortedByFirstName(String sortOrder) {
        Sort sort = Sort.by("firstName");
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return userRepository.findAll(sort);
    }

    public List<User> getUsersFilteredByAge(int minAge, int maxAge) {
        return userRepository.findByAgeBetween(minAge, maxAge);
    }

    public List<User> getUsersFilteredByGender(String gender) {
        return userRepository.findByGender(gender);
    }

    public Page<User> getUsersPaged(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
