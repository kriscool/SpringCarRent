package pl.kriscool.transport.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.kriscool.transport.dao.UserRepository;
import pl.kriscool.transport.domain.UserRole;
@Service
public class ServiceUser implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		pl.kriscool.transport.domain.User user = userRepository.findByLogin(login);
		System.out.println("Cel"+login);
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
 
		return buildUserForAuthentication(user, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}

	
	private User buildUserForAuthentication(pl.kriscool.transport.domain.User user, 
			List<GrantedAuthority> authorities) {
			return new User(user.getLogin(), user.getPassword(), 
				user.getActive(), true, true, true, authorities);
		}
}