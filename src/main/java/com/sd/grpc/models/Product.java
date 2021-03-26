package com.sd.grpc.models;

public class Product {

	private int id;
	private String name;
	private String brand;
	private int fabricationYear;
	private int modelYear;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getFabricationYear() {
		return fabricationYear;
	}

	public void setFabricationYear(int fabricationYear) {
		this.fabricationYear = fabricationYear;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product(com.sd.grpc.rpc.ProductMessage request){
		this(request.getId(), request.getName(), request.getBrand(), request.getFabricationYear(), request.getModelYear(), request.getPrice());
	}

	public Product(com.sd.grpc.rpc.CreateProductRequest request) {
		this(-1, request.getName(), request.getBrand(), request.getFabricationYear(), request.getModelYear(), request.getPrice());
	}

	public Product(int id, String name, String brand, int fabricationYear, int modelYear, double price) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.fabricationYear = fabricationYear;
		this.modelYear = modelYear;
		this.price = price;
	}

	public com.sd.grpc.rpc.ProductMessage toProductMessage() {
		Product product = this;
		com.sd.grpc.rpc.ProductMessage productMessage = com.sd.grpc.rpc.ProductMessage.newBuilder()
				.setId(product.getId())
				.setName(product.getName())
				.setBrand(product.getBrand())
				.setFabricationYear(product.getFabricationYear())
				.setModelYear(product.getModelYear())
				.setPrice(product.getPrice()).build();
		return productMessage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
