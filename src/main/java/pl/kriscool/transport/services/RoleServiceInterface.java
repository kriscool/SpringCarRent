package pl.kriscool.transport.services;

import org.springframework.security.access.prepost.PreAuthorize;

import pl.kriscool.transport.domain.UserRole;

public interface RoleServiceInterface {
	@PreAuthorize("isAuthenticated()")
	UserRole findById(Integer id);
	@PreAuthorize("isAuthenticated()")
	UserRole findByRole(String role);
}
