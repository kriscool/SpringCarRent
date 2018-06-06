package pl.kriscool.transport.services;

import java.sql.Date;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import pl.kriscool.transport.domain.Order;

public interface OrderServiceInterface {
	@PreAuthorize("isAuthenticated()")
	List<Order> findByDateorderNotLike(Date dateorder);
	@PreAuthorize("hasRole('ROLE_USER')")
	Order findById(int id);
	@PreAuthorize("isAuthenticated()")
	void save(Order order);
	@PreAuthorize("isAuthenticated()")
	List<Order> findAll();
}
