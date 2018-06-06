package pl.kriscool.transport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kriscool.transport.dao.PersonDataRepository;
import pl.kriscool.transport.domain.Person_data;
@Service
public class PersonDataServiceImpl implements PersonDataServiceInterface{
	@Autowired
	PersonDataRepository personDataRepository;

	@Override
	public Person_data findByToken(String token) {
		return personDataRepository.findByToken(token);
	}
	
}
