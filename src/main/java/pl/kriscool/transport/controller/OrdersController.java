package pl.kriscool.transport.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.DocumentException;

import pl.kriscool.transport.dao.CarRepository;
import pl.kriscool.transport.dao.OrderRepository;
import pl.kriscool.transport.dao.UserRepository;
import pl.kriscool.transport.domain.Car;
import pl.kriscool.transport.domain.Order;
import pl.kriscool.transport.domain.User;
import pl.kriscool.transport.services.CarServiceInterface;
import pl.kriscool.transport.services.OrderServiceInterface;
import pl.kriscool.transport.services.UserServiceInterface;


@Controller
public class OrdersController {
	//@Autowired
	//OrderRepository orderRepository;
	//@Autowired
//	CarRepository carRepository;
//	@Autowired
	//UserRepository userRepository;
	@Autowired
	OrderServiceInterface orderServiceIntreface;
	@Autowired
	CarServiceInterface carServiceInterface;
	@Autowired
	UserServiceInterface userServiceInterface;
	
	@RequestMapping(value = "/user/order", method= RequestMethod.GET)
	public String userOrders(Locale locale, Model model) {
		return "orders";
	}
	
	@RequestMapping(value = "/user/generate/pdf", method= RequestMethod.GET)
	public void getCarInTime(HttpServletResponse response,@RequestParam("id") int id,Locale locale,Model model) throws IOException {
		Order o = orderServiceIntreface.findById(id);
		PdfGenerator pdf = new PdfGenerator();
		try {
			pdf.PdfOrder(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 File file = new File("C:\\Users\\kriscool\\Desktop\\Transport\\kapj2018-transport\\order-"+o.getId() +".pdf");
         
	      
	            
	        
	         
	        if(!file.exists()){
	            String errorMessage = "Sorry. The file you are looking for does not exist";
	            System.out.println(errorMessage);
	            OutputStream outputStream = response.getOutputStream();
	            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
	            outputStream.close();
	            return;
	        }
	         
	        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null){
	            System.out.println("mimetype is not detectable, will take default");
	            mimeType = "application/octet-stream";
	        }
	         
	        response.setContentType(mimeType);
	         
	        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
	            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
	        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
	 
	         
	        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
	        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
	         
	        response.setContentLength((int)file.length());
		//InputStream sourceFromXml = new StreamSource("C:\\Users\\kriscool\\Desktop\\Transport\\kapj2018-transport\\order-114.pdf");
	        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping(value = "/user/order/car", method= RequestMethod.GET)
	public ModelAndView getUserOrdersForm(Locale locale,Model model,@RequestParam("tablica") String tablica,@RequestParam("Date") String date) throws ParseException {
		Order o = new Order();
		
		o.setDateorder(java.sql.Date.valueOf(date));
		ModelAndView modelAndView = new ModelAndView("orderFrom");
		modelAndView.addObject("command", o);
		modelAndView.addObject("tab", tablica);
		return modelAndView;
	}
	
	@RequestMapping(value = "/user/order/addOrder", method= RequestMethod.POST)
	public String addOrder(@RequestParam("tablica") String tablica,@ModelAttribute("order") Order order,Locale locale, Model model){ 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User myUser = (org.springframework.security.core.userdetails.User) ((auth!=null) ? auth.getPrincipal() : null);
		myUser.getUsername();
		order.setUser(userServiceInterface.findByLogin(myUser.getUsername()));
		Set<Car> car = new  HashSet<Car>();
		car.add(carServiceInterface.findByTablica(tablica));
		order.setCars(car);
		orderServiceIntreface.save(order);
		
		return "userOrders";
	}
}
