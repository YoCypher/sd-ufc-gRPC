package com.sd.grpc.repositories;

import java.util.ArrayList;
import java.util.List;

import com.sd.grpc.models.Product;

public class ProductRepository {

	private List<Product> products;
	private int counter;

	public ProductRepository() {
		products = new ArrayList<>();
		counter = 0;
	}

	public Product add(Product product) {
		product.setId(counter++);
		products.add(product);
		return product;
	}

	public Product update(Product product) {
		int index = products.indexOf(product);
		if (index >= 0) {
			products.set(index, product);
			return product;
		}
		return null;
	}

	public Product findById(int id) {
		for (Product p : products) {
			if (p.getId() == id)
				return p;
		}
		return null;
	}

	public List<Product> findAll() {
		return products;
	}

	public void remove(Product product) {
		products.remove(product);
	}

}
