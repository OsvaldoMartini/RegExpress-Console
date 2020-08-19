package com.shifthunter.regexpress;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;

//@RestController
@Controller
public class RegExpressController {
	
	// @LoadBalanced 
	// this adds the right Interceptors here, 
	// recognizes that i want to do client-side load balancing 
	// and it'll then take that service "id" and swap it out with the URL at runtime
					
	@LoadBalanced 	
	@Bean //To be in a spring container / gett the interceptores to
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	// Now I am using thi object that as autowired
	// It's get all the good stuff from the spring container
	// and then Let's use it
	@Autowired
	private RestTemplate restTemplate;	
	
	//@HystrixCommand(fallbackMethod="getRandomFromExpressionFallBack")
	@RequestMapping("/regexpress/{expression}")
	//public ResponseEntity<?> genRandomFromExpression(@PathVariable String expression, Model m) {
	public String genRandomFromExpression(@PathVariable String expression, Model m) {
		// Not Hard coded anymore
		// RestTemplate rest = new RestTemplate();
		// FastPassCustomer c = rest.getForObject("http://localhost:8086/fastpass?fastpassid=" + fastPassId, FastPassCustomer.class);
		
		// Now I am using the "Name of the Service" that's using dynamic port definition
		// Eureka and Ribbon It will replace in Runtime
		//String url = "http://ShiftHunter-RegExpress-Service/regexpress/" + expression;
		String url = "http://localhost:44444/regexpress/" + expression;
		RegExpress c = restTemplate.getForObject(url, RegExpress.class);
		System.out.println("Url: " + url);
		System.out.println("retrieved RegExpress generated");

//		return new ResponseEntity<List<String>>(c.getExpressionList(), HttpStatus.OK);

		
		//Thymeleaf
		m.addAttribute("regExpression", c);
		return "console";

//		return c.getExpressionListArray();
	}
	
	public ResponseEntity<?>  getRandomFromExpressionFallBack(@PathVariable String expression, Model m) { 
	
		// I cannot Return the same customer here
		// But I can Create one with empty values to return gracefully
		RegExpress c = new RegExpress();
		c.setSingleExpression(expression);
		System.out.println("Fallback operation called");
		
		return new ResponseEntity<List<String>[]>(c.getExpressionListArray(), HttpStatus.OK);
		
		//Thymeleaf
		//m.addAttribute("regExpression", c);
		//return "console";

		//return c.getExpressionListArray(); //"console";
		//ResponseEntity<?>
				
	}
	
	
	
	//@HystrixCommand(fallbackMethod="getExpressionsFromArrayFallBack")
	@PostMapping("/regexpress/{limitEachReg}")
	public ResponseEntity<?> genExpressionsFromArray(@PathVariable int limitEachReg, @Valid @RequestBody String[] expressions, Model m) {
		// Not Hard coded anymore
		// RestTemplate rest = new RestTemplate();
		// FastPassCustomer c = rest.getForObject("http://localhost:8086/fastpass?fastpassid=" + fastPassId, FastPassCustomer.class);
		
		// Now I am using the "Name of the Service" that's using dynamic port definition
		// Eureka and Ribbon It will replace in Runtime
		//String url = "http://ShiftHunter-RegExpress-Service/regexpress/" + limitEachReg;
		String url = "http://localhost:44444/regexpress/" + limitEachReg;
		
		//ResponseEntity<RegExpress> result = restTemplate.postForEntity(url, expressions, RegExpress.class);
		
		RegExpress c = restTemplate.postForObject(url, expressions, RegExpress.class);
		
		System.out.println("Url: " + url);
		System.out.println("retrieved RegExpress generated");
		
		//Thymeleaf
		//m.addAttribute("regExpression", c);
		//return "console";
		
		//ResponseEntity<?>
		return new ResponseEntity<List<String>[]>(c.getExpressionListArray(), HttpStatus.OK);
		//return c.getExpressionListArray();
				
	}
		
	
	public ResponseEntity<?> getExpressionsFromArrayFallBack(@PathVariable int limitEachReg, @Valid @RequestBody String[] expressions, Model m) { 
		// I cannot Return the same customer here
		// But I can Create one with empty values to return gracefully
		RegExpress c = new RegExpress();
		
		List<String> resumeEntry = new ArrayList<String>();
    	
		for(int x=0; x < expressions.length; x++) {
			resumeEntry.add(expressions[x]);
		}
		
		c.setQtdGenRandom(limitEachReg);
		c.setExpressionList(resumeEntry);
		System.out.println("Fallback operation called");
		
		//Thymeleaf
		//m.addAttribute("regExpression", c);
		//return "console";
		
		//ResponseEntity<?>
		return new ResponseEntity<List<String>[]>(c.getExpressionListArray(), HttpStatus.OK);
		//return c.getExpressionListArray();
	}

}
