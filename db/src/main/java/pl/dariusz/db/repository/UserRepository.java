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


}
