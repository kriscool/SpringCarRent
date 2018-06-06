package pl.kriscool.transport.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import pl.kriscool.transport.domain.Car;

public interface CarServiceInterface {
	@PreAuthorize("isAuthenticated()")
	Car findById(int car);
	@PreAuthorize("isAuthenticated()")
	Car findByTablica(String tablica);

	@PreAuthorize("hasRole('EMPLOYEE')")
	void save(Car car);
	@PreAuthorize("hasRole('EMPLOYEE')")
	void delete(Car car);
	@PreAuthorize("hasRole('EMPLOYEE')")
	List<Car> findAll();
}
