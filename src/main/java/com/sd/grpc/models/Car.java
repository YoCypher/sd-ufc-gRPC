package com.sd.grpc.models;

public class Car {

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

	public Car(com.sd.grpc.rpc.CarMessage request){
		this(request.getId(), request.getName(), request.getBrand(), request.getFabricationYear(), request.getModelYear(), request.getPrice());
	}

	public Car(com.sd.grpc.rpc.CreateCarRequest request) {
		this(-1, request.getName(), request.getBrand(), request.getFabricationYear(), request.getModelYear(), request.getPrice());
	}

	public Car(int id, String name, String brand, int fabricationYear, int modelYear, double price) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.fabricationYear = fabricationYear;
		this.modelYear = modelYear;
		this.price = price;
	}

	public String toLine() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Id: ")
				.append(id)
				.append("\nName: ")
				.append(name)
				.append("\nBrand: ")
				.append(brand)
				.append("\nFabrication Year: ")
				.append(fabricationYear)
				.append("\nModel Year: ")
				.append(modelYear)
				.append("\nPrice: ")
				.append(price);
		return sb.toString();
	}

	public com.sd.grpc.rpc.CarMessage toCarMessage() {
		Car car = this;
		com.sd.grpc.rpc.CarMessage productMessage = com.sd.grpc.rpc.CarMessage.newBuilder()
				.setId(car.getId())
				.setName(car.getName())
				.setBrand(car.getBrand())
				.setFabricationYear(car.getFabricationYear())
				.setModelYear(car.getModelYear())
				.setPrice(car.getPrice()).build();
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
		Car other = (Car) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
