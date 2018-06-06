package pl.kriscool.transport.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pl.kriscool.transport.dao.RoleRepository;
import pl.kriscool.transport.dao.UserRepository;
import pl.kriscool.transport.dataTableDomain.CaptchaModel;
import pl.kriscool.transport.domain.User;
import pl.kriscool.transport.services.RoleServiceInterface;
import pl.kriscool.transport.services.UserServiceInterface;
import pl.kriscool.transport.validator.PersonValidator;

@Controller
public class UserController {
	
	
	//@Autowired
	//RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	 
	PersonValidator personValidator = new PersonValidator();
	//@Autowired
	//UserRepository userRepository;
	
	@Autowired
	UserServiceInterface userServiceInterface;
	
	@Autowired
	RoleServiceInterface roleServiceInterface;
	

	@RequestMapping(value = "/activate", method= RequestMethod.GET)
	public ModelAndView userActivate(Locale locale, Model model,@RequestParam(value="hash") String hash) {
		User user = userServiceInterface.findByToken(hash);
		user.setActive(true);
		userServiceInterface.save(user);
		return new ModelAndView("activeSucces");
	}
	
	@RequestMapping(value = "/admin/set/role/user", method = RequestMethod.GET)
	public String adminPage(@RequestParam(value="login") String login,@RequestParam(value="role") String role) {
		User u =userServiceInterface.findByLogin(login);
		u.getRoles().add(roleServiceInterface.findByRole(role));
		userServiceInterface.save(u);
		return "activeSucces";
	}
	
	@RequestMapping(value = "/admin/delete/user", method= RequestMethod.GET)
	public String deleteUser(Locale locale,@RequestParam(value="login") String login) {
		User user = userServiceInterface.findByLogin(login);
		userServiceInterface.delete(user);
		return "Succes";
	}
	
	@RequestMapping(value = "/user/chose/time", method= RequestMethod.GET)
	public ModelAndView getCarInTime(Locale locale,Model model) {
		return new ModelAndView("timeChoose","date",new Date(0));
	}
	
	@RequestMapping(value = "/user/get/orders", method= RequestMethod.GET)
	public ModelAndView getUserOrders(Locale locale,Model model) {
		return new ModelAndView("userOrders");
	}
	
	
	@RequestMapping(value = "/addUser", method= RequestMethod.POST)
	public String addUser(@ModelAttribute("command") User user,BindingResult result,HttpServletRequest request,Locale locale, Model model) throws NamingException, MessagingException, IOException { 
		personValidator.validate(user, result);
		if(!result.hasErrors() && verifyRecaptcha(request.getParameter("g-recaptcha-response"))) {
			try {
			user.getRoles().add(roleServiceInterface.findById(1));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setToken(hashEmail(user.getEmail()));
		userServiceInterface.save(user);
		//sendToken(user.getEmail(),hashEmail(user.getEmail()));
		}catch(DataIntegrityViolationException e) {
			model.addAttribute("error","taki email juz jest w bazie");
				return "Registration";
		}
			return "home2";
		}
		return "Registration";
	}
	
	  public Boolean verifyRecaptcha(String responseCaptcha) throws IOException{
		  	String url = "https://www.google.com/recaptcha/api/siteverify?secret=6LdsjFIUAAAAAJ8f9rPYm0wgkxKORloOFSG4I8RL&response="+responseCaptcha+"&remoteip=localhost";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			Gson g = new Gson();
			
			CaptchaModel captcha =g.fromJson(response.toString(),CaptchaModel.class);
			
			return Boolean.valueOf(captcha.getSuccess());

	  }
	  
	public void sendToken(String email,String hash) {
		final String username = "transport.projekt.spring@gmail.com";
		final String password = "";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("transport.projekt.spring@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Testing Subject");
			message.setText("localhost:8080/transport/activate?hash="+hash);
			
			
			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public String hashEmail(String password) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(password.getBytes());
	            byte[] digest = md.digest();
	            StringBuffer hashStringBuffer = new StringBuffer();
	            for (byte b : digest)
	                hashStringBuffer.append(String.format("%02X", b));
	            return new String(hashStringBuffer);
	        } catch (NoSuchAlgorithmException e){
	            throw new RuntimeException(e.getMessage());
	        }
	}
	
	@RequestMapping(value = "/registration", method= RequestMethod.GET)
	public ModelAndView user(Locale locale, Model model) {
		return new ModelAndView("Registration","command",new User());
	}
}
