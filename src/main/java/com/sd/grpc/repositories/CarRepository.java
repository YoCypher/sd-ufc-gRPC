package com.sd.grpc.repositories;

import java.util.ArrayList;
import java.util.List;

import com.sd.grpc.models.Car;

public class CarRepository {

	private List<Car> cars;
	private int counter;

	public CarRepository() {
		cars = new ArrayList<>();
		counter = 0;
	}

	public Car add(Car car) {
		car.setId(counter++);
		cars.add(car);
		return car;
	}

	public Car update(Car car) {
		int index = cars.indexOf(car);
		if (index >= 0) {
			cars.set(index, car);
			return car;
		}
		return null;
	}

	public Car findById(int id) {
		for (Car p : cars) {
			if (p.getId() == id)
				return p;
		}
		return null;
	}

	public List<Car> findAll() {
		return cars;
	}

	public void remove(Car car) {
		cars.remove(car);
	}

}
