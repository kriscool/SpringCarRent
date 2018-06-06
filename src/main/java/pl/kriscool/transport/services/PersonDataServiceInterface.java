package pl.kriscool.transport.services;

import pl.kriscool.transport.domain.Person_data;

public interface PersonDataServiceInterface {
	Person_data findByToken(String token);
}
