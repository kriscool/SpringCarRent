package pl.kriscool.transport.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kriscool.transport.dao.OrderRepository;
import pl.kriscool.transport.domain.Order;

@Service
public class OrderServiceImpl implements OrderServiceInterface{
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> findByDateorderNotLike(Date dateorder) {
		return orderRepository.findByDateorderNotLike(dateorder);
	}

	@Override
	public Order findById(int id) {
		return orderRepository.findById(id);
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
}
