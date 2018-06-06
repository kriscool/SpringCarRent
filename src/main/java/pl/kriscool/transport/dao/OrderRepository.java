package pl.kriscool.transport.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.kriscool.transport.domain.Order;

@Transactional 
@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
	List<Order> findByDateorderNotLike(Date dateorder);
	Order findById(int id);
}
