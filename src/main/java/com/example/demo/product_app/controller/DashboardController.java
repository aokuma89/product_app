package com.example.demo.product_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.product_app.repository.ProductRepository;
import com.example.demo.product_app.repository.UserRepostiroy;

@Controller
public class DashboardController {

	private final ProductRepository productRepository;
	private final UserRepostiroy userRepostiroy;

	public DashboardController(ProductRepository productRepository, UserRepostiroy userRepostiroy) {
		this.productRepository = productRepository;
		this.userRepostiroy = userRepostiroy;
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("productCount", productRepository.count());
		model.addAttribute("userCount", userRepostiroy.count());
		return "dashboard";
	}

}
