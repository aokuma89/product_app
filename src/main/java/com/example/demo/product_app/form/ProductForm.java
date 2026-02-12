package com.example.demo.product_app.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductForm {

	@NotBlank
	@Size(max = 100, message = "商品名は100文字以内で入力してください")
	private String name;

	@NotNull(message = "価格は必須です")
	@Min(value = 0, message = "価格は0以上で入力してください")
	@Max(value = 1000000, message = "価格は1,000,000以下で入力してください")
	private Integer price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
