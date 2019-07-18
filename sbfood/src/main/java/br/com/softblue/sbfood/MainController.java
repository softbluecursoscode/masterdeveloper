package br.com.softblue.sbfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping(path="/add")
	public @ResponseBody String addNewUser() {
		Order order = new Order();
		order.setDescription("Testando");
		orderRepository.save(order);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Order> getAllUsers() {
		return orderRepository.findAll();
	}
}