package pl.kriscool.transport.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.kriscool.transport.domain.Car;
@Transactional 
@Repository
public interface CarRepository extends JpaRepository<Car, Long>  {
	Car findById(int car);
	Car findByTablica(String tablica);
}
