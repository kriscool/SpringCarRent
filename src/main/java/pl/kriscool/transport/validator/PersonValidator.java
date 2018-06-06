package pl.kriscool.transport.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.kriscool.transport.dao.UserRepository;
import pl.kriscool.transport.domain.User;
import org.apache.commons.validator.EmailValidator;
public class PersonValidator implements Validator {

	EmailValidator validator = EmailValidator.getInstance();
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

	public void validate(User user, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "firstname", "error.shortage.firstname");
		ValidationUtils.rejectIfEmpty(errors, "login", "error.shortage.login");
		ValidationUtils.rejectIfEmpty(errors, "lasttname", "error.shortage.lastname");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.shortage.email");
		ValidationUtils.rejectIfEmpty(errors, "telephone", "error.shortage.telephone");
		if(validator.isValid(user.getEmail())) {
			ValidationUtils.rejectIfEmpty(errors, "email", "error.invalid.email");
		
		}
	
		
	}
}
