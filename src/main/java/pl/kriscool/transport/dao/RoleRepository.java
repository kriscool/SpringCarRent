package pl.kriscool.transport.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.kriscool.transport.domain.UserRole;

@Transactional 
@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long>{
	UserRole findById(Integer id);
	UserRole findByRole(String role);
}
