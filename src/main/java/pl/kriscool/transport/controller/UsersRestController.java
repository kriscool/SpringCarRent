package pl.kriscool.transport.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import pl.kriscool.transport.dao.CarRepository;
import pl.kriscool.transport.dao.OrderRepository;
import pl.kriscool.transport.dao.RoleRepository;
import pl.kriscool.transport.dao.UserRepository;
import pl.kriscool.transport.dataTableDomain.CarDataTable;
import pl.kriscool.transport.dataTableDomain.OrdersDataTable;
import pl.kriscool.transport.dataTableDomain.UserDataTable;
import pl.kriscool.transport.domain.Car;
import pl.kriscool.transport.domain.Order;
import pl.kriscool.transport.domain.User;
import pl.kriscool.transport.domain.UserRole;
import pl.kriscool.transport.services.CarServiceInterface;
import pl.kriscool.transport.services.OrderServiceInterface;
import pl.kriscool.transport.services.RoleServiceInterface;
import pl.kriscool.transport.services.UserServiceInterface;

@RestController
public class UsersRestController {
	//@Autowired
	//UserRepository userRepository;
	//@Autowired
	//OrderRepository orderRepository;
	//@Autowired
	//CarRepository carRepository;
	//@Autowired
	//RoleRepository roleRepository;
	@Autowired
	OrderServiceInterface orderServiceIntreface;
	@Autowired
	CarServiceInterface carServiceInterface;

	@Autowired
	RoleServiceInterface roleServiceInterface;
	@Autowired
	UserServiceInterface userServiceInterface;
	@RequestMapping(value = "/getAllUser", method= RequestMethod.GET)
	@ResponseBody
	public String RESTGETUSERS() {
			Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
			List<User> users = userServiceInterface.findAll();
			JsonElement element = g.toJsonTree(users, new TypeToken<List<User>>(){}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			return jsonArray.toString();
	 }
	
	@RequestMapping(value = "/admin/getUserDataTable", method= RequestMethod.GET)
	@ResponseBody
	public String dataTablesUser() {
		Gson g =  new Gson();
		List<User> users = userServiceInterface.findByActiveTrue();
		JsonArray jsonArray = new JsonArray();
		
		for (User u : users) {
			jsonArray.add(g.toJsonTree(new UserDataTable(u,"<button onclick=deleteUser('/transport/admin/delete/user?login="+u.getLogin()+"')>Usun</button>",
					u.getRoles().iterator().next().getRole(),
					"<button onclick=deleteUser('http://localhost:8080/transport/admin/set/role/user?login="+u.getLogin()+"&role=ROLE_EMPLOYEE')>EMPLOYEE</button><br>"
					+ "<button onclick=deleteUser('http://localhost:8080/transport/admin/set/role/user?login="+u.getLogin()+"&role=ROLE_ADMIN')>ADMIN</button><br>"
					+ "<button onclick=deleteUser('http://localhost:8080/transport/admin/set/role/user?login="+u.getLogin()+"&role=ROLE_USER')>USER</button>")));
			
		}
		JsonObject objectToDataTable = new JsonObject();
		objectToDataTable.add("data", jsonArray);
		return objectToDataTable.toString();
	}
	
	
	@RequestMapping(value = "/user/getOrdersForUser", method= RequestMethod.GET)
	@ResponseBody
	public String getOrdersForUser() {
		
		
		
		
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User myUser = (org.springframework.security.core.userdetails.User) ((auth!=null) ? auth.getPrincipal() : null);
		String user = myUser.getUsername();
		System.out.println(user);
		Gson g = new Gson();
		List<Order> orders = orderServiceIntreface.findAll();
		JsonArray jsonArray = new JsonArray();
		for (Order order : orders) {
			if(order.getUser().getLogin().equals(user)) {
				OrdersDataTable ordersDataTable = new OrdersDataTable();
				Iterator<Car> it = order.getCars().iterator();
				ordersDataTable.setCar(it.next().getMarka());
				ordersDataTable.setDate(order.getDateorder().toString());
				ordersDataTable.setDescription(order.getDescription());
				ordersDataTable.setButton("<a href='http://localhost:8080/transport/user/generate/pdf?id="+order.getId()+"'class=\"btn btn-default\">Generuj rachunek</a>");
				jsonArray.add(g.toJsonTree(ordersDataTable));
			}
		}
		JsonObject objectToDataTable = new JsonObject();
		objectToDataTable.add("data", jsonArray);
		return objectToDataTable.toString();
	}
	
	@RequestMapping(value = "/user/getCarDataTable/Date", method= RequestMethod.GET)
	@ResponseBody
	public String getCarDataTableDate(@RequestParam(value="dzien",required=false) String dzien,@RequestParam(value="miesiac",required=false) String month,@RequestParam(value="rok",required=false) String rok) {
	
		java.sql.Date dat = java.sql.Date.valueOf(rok+"-"+month+"-"+dzien);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dat);
		int monthInt = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		Date d = new Date(year-1900, monthInt, day);
		System.out.println(d.toString());
		int isInDate = 0;
		Gson g = new Gson();
		List<Car> cars = carServiceInterface.findAll();
		
		JsonArray jsonArray = new JsonArray();
		for (Car c : cars) {
			 for (Iterator<Order> it = c.getOrders().iterator(); it.hasNext(); ) {
				 Order f = it.next();
			        if (f.getDateorder().equals(d)) {
			        	isInDate++;
			        }
			    }
			 if(isInDate==0) {
				 jsonArray.add(g.toJsonTree(new CarDataTable(c,"<a href='http://localhost:8080/transport/user/order/car?tablica="+c.getTablica()+"&Date="+d.toString()+"' class=\"btn btn-default\">Rezerwuj</a>")));
						 
			 }
		}
		JsonObject objectToDataTable = new JsonObject();
		objectToDataTable.add("data", jsonArray);
		return objectToDataTable.toString();
	}
	
	
	@RequestMapping(value = "/employee/getCarDataTable", method= RequestMethod.GET)
	@ResponseBody
	public String getCarDataTable() {
		Gson g = new Gson();
		List<Car> cars = carServiceInterface.findAll();
		JsonArray jsonArray = new JsonArray();
		for (Car c : cars) {
			jsonArray.add(g.toJsonTree(new CarDataTable(c,"<button onclick=request('http://localhost:8080/transport/employee/car/delete?tablica="+c.getTablica()+"')>Usun</button>")));
		}
		JsonObject objectToDataTable = new JsonObject();
		objectToDataTable.add("data", jsonArray);
		return objectToDataTable.toString();
	}
	

}
