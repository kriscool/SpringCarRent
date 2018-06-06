package pl.kriscool.transport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kriscool.transport.dao.RoleRepository;
import pl.kriscool.transport.domain.UserRole;
@Service
public class RoleServiceImpl implements RoleServiceInterface {
	@Autowired
	RoleRepository roleRepository;
	@Override
	public UserRole findById(Integer id) {
		return roleRepository.findById(id);
	}
	@Override
	public UserRole findByRole(String role) {
		return roleRepository.findByRole(role);
	}

}
