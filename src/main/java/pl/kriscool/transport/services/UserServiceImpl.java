package pl.kriscool.transport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kriscool.transport.dao.UserRepository;
import pl.kriscool.transport.domain.User;


@Service
public class UserServiceImpl implements UserServiceInterface {
	@Autowired
	UserRepository userRepository;
	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User findByFirstname(String firstname) {
		
		return  userRepository.findByFirstname(firstname);
	}

	@Override
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public List<User> findByActiveTrue() {
		return userRepository.findByActiveTrue();
	}

	@Override
	public List<User> findByActiveFalse() {
		return userRepository.findByActiveFalse();
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
		
	}

	@Override
	public User findByToken(String Token) {
		return userRepository.findByToken(Token);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
		
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
