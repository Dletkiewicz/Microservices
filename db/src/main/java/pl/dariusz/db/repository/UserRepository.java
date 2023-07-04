package pl.dariusz.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dariusz.db.user.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) = LOWER(:firstName)")
    List<User> findByFirstNameIgnoreCase(@Param("firstName") String firstName);

    @Query("SELECT u FROM User u WHERE LOWER(u.lastName) = LOWER(:lastName)")
    List<User> findByLastNameIgnoreCase(@Param("lastName") String lastName);

    @Query("SELECT u FROM User u ORDER BY u.firstName ASC")
    List<User> findAllByFirstNameAsc();

    @Query("SELECT u FROM User u ORDER BY u.firstName DESC")
    List<User> findAllByFirstNameDesc();

    @Query("SELECT u FROM User u WHERE u.age BETWEEN :minAge AND :maxAge")
    List<User> findByAgeBetween(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    @Query("SELECT u FROM User u WHERE lower(u.gender) = lower(:gender)")
    List<User> findByGender(@Param("gender") String gender);

    Page<User> findAll(Pageable pageable);

}
