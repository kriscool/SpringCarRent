package pl.kriscool.transport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kriscool.transport.dao.CarRepository;
import pl.kriscool.transport.domain.Car;
@Service
public class CarServiceImpl implements CarServiceInterface{
	@Autowired
	CarRepository carRepository;
	@Override
	public Car findById(int car) {
		return carRepository.findById(car);
	}

	@Override
	public Car findByTablica(String tablica) {
		return carRepository.findByTablica(tablica);
	}

	@Override
	public void save(Car car) {
		carRepository.save(car);
		
	}

	@Override
	public void delete(Car car) {
		carRepository.delete(car);
		
	}

	@Override
	public List<Car> findAll() {
		return carRepository.findAll();
	}

}
