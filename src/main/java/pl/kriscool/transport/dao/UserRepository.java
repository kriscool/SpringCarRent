package pl.kriscool.transport.dao;


import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import pl.kriscool.transport.domain.User;
@Transactional 
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findById(int id);
	User findByFirstname(String firstname);
	User findByLogin(String login);
	User findByToken(String token);
	User findByEmail(String email);
	List<User> findByActiveTrue();
	List<User> findByActiveFalse();
}
