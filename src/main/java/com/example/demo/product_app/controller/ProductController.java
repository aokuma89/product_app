package com.example.demo.product_app.controller;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.product_app.ProductAppApplication;
import com.example.demo.product_app.entity.Product;
import com.example.demo.product_app.form.ProductForm;
import com.example.demo.product_app.repository.ProductRepository;
import com.example.demo.product_app.service.ProductService;

@Controller
public class ProductController {

	private final AuthController authController;

	private final ProductAppApplication productAppApplication;

	private final ProductService productService;

	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository, ProductService productService,
			ProductAppApplication productAppApplication, AuthController authController) {
		this.productRepository = productRepository;
		this.productService = productService;
		this.productAppApplication = productAppApplication;
		this.authController = authController;
	}

	@GetMapping("/")
	public String index(Model model, Authentication authentication) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("productForm", new ProductForm());
		model.addAttribute("loginUserName", authentication.getName());
		model.addAttribute("isAdmin", hasAdminRole(authentication));

		return "index";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("productForm") ProductForm form, BindingResult bindingResult, Model model,
			Authentication authentication) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("products", productService.findAll());
			model.addAttribute("loginUserName", authentication.getName());
			model.addAttribute("isAdmin", hasAdminRole(authentication));
			return "index";
		}
		Product product = new Product();
		product.setName(form.getName());
		product.setPrice(form.getPrice());
		productService.save(product);

		return "redirect:/";
	}

	@GetMapping("/products/{id}/edit")
	public String edit(@PathVariable Integer id, Model model, Authentication authentication) {
		Product product = productService.findById(id);

		ProductForm form = new ProductForm();
		form.setName(product.getName());
		form.setPrice(product.getPrice());

		model.addAttribute("productId", id);
		model.addAttribute("productForm", form);
		model.addAttribute("loginUserName", authentication.getName());
		model.addAttribute("isAdmin", hasAdminRole(authentication));

		return "edit";
	}

	@PostMapping("/product/{id}/update")
	public String update(@PathVariable Integer id, @Valid @ModelAttribute("productForm") ProductForm form,
			BindingResult bindingResult, Model model, Authentication authentication) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("productId", id);
			model.addAttribute("loginUserName", authentication.getName());
			model.addAttribute("isAdmin", hasAdminRole(authentication));
			return "edit";
		}

		Product product = productService.findById(id);
		product.setName(form.getName());
		product.setPrice(form.getPrice());
		productService.save(product);

		return "redirect:/";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam Integer id) {
		productService.deleteById(id);
		return "redirect:/";
	}

	private boolean hasAdminRole(Authentication authentication) {
		return authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ADMIN"));

	}
}

//	@GetMapping("/products")
//	public String products(Model model) {
//		model.addAttribute("products", productRepository.findAll());
//		return "products";
//	}
//
//	@PostMapping("/products")
//	public String createProduct(@RequestParam String name, @RequestParam Integer price,
//			RedirectAttributes redirectAttributes) {
//		if (name == null || name.isBlank() || price == null || price < 0) {
//			redirectAttributes.addFlashAttribute("error", "入力内容が正しくありません。");
//			return "redirect:/products";
//		}
//		Product product = new Product();
//		product.setName(name.trim());
//		product.setPrice(price);
//		productRepository.save(product);
//
//		return "redirect:/products";
//	}
//
//	@PostMapping("/products/{id}/delete")
//	public String delteProduct(@PathVariable Integer id) {
//		productRepository.deleteById(id);
//		return "redirect:/products";
//	}
