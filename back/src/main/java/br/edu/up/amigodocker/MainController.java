package br.edu.up.amigodocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/amigo") // This means URL's start with /amigo (after Application path)
public class MainController {

	@Autowired // This means to get the bean called amigoRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private AmigoRepository amigoRepository;
	
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String nome
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Amigo amigo = new Amigo();
		amigo.setNome(nome);
		amigo.setEmail(email);
		amigoRepository.save(amigo);
		return "Amigo salvo";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Amigo> getAllUsers() {
		// This returns a JSON or XML with the users
		return amigoRepository.findAll();
	}
}
