package pl.kriscool.transport.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import pl.kriscool.transport.domain.User;
public interface UserServiceInterface {
	@PreAuthorize("isAuthenticated()")
	User findById(int id);
	@PreAuthorize("isAuthenticated()")
	User findByFirstname(String firstname);
	@PreAuthorize("isAuthenticated()")
	User findByLogin(String login);
	
	@PreAuthorize("isAuthenticated()")
	List<User> findByActiveTrue();
	@PreAuthorize("isAuthenticated()")
	List<User> findByActiveFalse();
	
	void save(User user);
	@PreAuthorize("isAuthenticated()")
	User findByToken(String Token);
	
	@PreAuthorize("hasRole('ADMIN')")
	void delete(User user);
	
	@PreAuthorize("hasRole('ADMIN')")
	List<User> findAll();
	
}
