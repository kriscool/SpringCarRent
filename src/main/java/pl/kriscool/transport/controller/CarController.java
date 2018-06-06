package pl.kriscool.transport.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.kriscool.transport.dao.CarRepository;
import pl.kriscool.transport.domain.Car;
import pl.kriscool.transport.services.CarServiceInterface;
@Controller
public class CarController {
	
	//@Autowired
	//CarRepository carRepository;
	
	@Autowired
	CarServiceInterface carServiceInterface;
	
	@RequestMapping(value = "/employee/car", method= RequestMethod.GET)
	public ModelAndView employeeCar(Locale locale, Model model) {
		return new ModelAndView("car");
	}
	
	
	@RequestMapping(value = "/employee/car/form", method= RequestMethod.GET)
	public ModelAndView employeeCarAdd(Locale locale, Model model) {
		return new ModelAndView("carAddForm","car",new Car());
	}
	
	
	@RequestMapping(value = "/employee/car/add", method= RequestMethod.POST)
	public String employeeCarAddPost(@ModelAttribute("car") Car car,BindingResult result,HttpServletRequest request,Locale locale, Model model) {
		carServiceInterface.save(car);
		return "home2";
	}
	
	
	@RequestMapping(value = "/employee/car/delete", method= RequestMethod.GET)
	public String employeeCarAddDelete(@RequestParam(value = "tablica", required = true) String tab,Locale locale, Model model) {
		Car c =carServiceInterface.findByTablica(tab);
		carServiceInterface.delete(c);
		return "home2";
	}
}
