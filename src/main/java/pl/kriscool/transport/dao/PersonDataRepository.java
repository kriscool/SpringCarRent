package pl.kriscool.transport.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import pl.kriscool.transport.domain.Person_data;

@Transactional 
@Repository
public interface PersonDataRepository extends JpaRepository<Person_data, Long>{
	Person_data findByToken(String token);
}
