package com.example.demo.product_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.product_app.entity.Product;
import com.example.demo.product_app.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Transactional
	public Product findById(Integer id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("指定IDの商品が存在しません： " + id));
	}

	@Transactional
	public void save(Product product) {
		productRepository.save(product);
	}

	@Transactional
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}
}
