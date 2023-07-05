package pl.dariusz.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dariusz.db.user.User;
import pl.dariusz.db.user.UserSearchable;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE"
            + " (:#{#searchable.firstName} IS NULL OR u.firstName = :#{#searchable.firstName})"
            + " AND (:#{#searchable.lastName} IS NULL OR u.lastName = :#{#searchable.lastName})"
            + " AND (:#{#searchable.gender} IS NULL OR u.gender = :#{#searchable.gender})"
            + " AND (:#{#searchable.age} = 0 OR u.age >= :#{#searchable.age})")
    Page<User> findAllWithSearchAndPagination(Pageable pageable, @Param("searchable") UserSearchable searchable);

}
