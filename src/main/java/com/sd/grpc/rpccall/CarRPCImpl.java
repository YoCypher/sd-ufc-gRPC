package com.sd.grpc.rpccall;

import com.sd.grpc.models.*;
import com.sd.grpc.repositories.CarRepository;
import com.sd.grpc.rpc.CarRPCGrpc;
import com.sd.grpc.rpc.CreateCarRequest;
import com.sd.grpc.rpc.CreateCarResponse;
import com.sd.grpc.rpc.UpdateCarRequest;
import com.sd.grpc.rpc.UpdateCarResponse;
import com.sd.grpc.rpc.CarMessage;
import com.sd.grpc.rpc.FindByIdRequest;
import com.sd.grpc.rpc.FindByIdResponse;
import com.sd.grpc.rpc.FindAllResponse;
import com.sd.grpc.rpc.FindAllRequest;
import com.sd.grpc.rpc.DeleteRequest;
import com.sd.grpc.rpc.DeleteResponse;
import io.grpc.stub.StreamObserver;
import java.util.List;

public class CarRPCImpl extends CarRPCGrpc.CarRPCImplBase {

    private final CarRepository carRepository = new CarRepository();

    @Override
    public void create(CreateCarRequest request, StreamObserver<CreateCarResponse> responseObserver) {
        Car car = new Car(request);
        car = carRepository.add(car);
        CarMessage carMessage = car.toCarMessage();
        CreateCarResponse response = CreateCarResponse
                .newBuilder()
                .setCar(carMessage)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void update(UpdateCarRequest request, StreamObserver<UpdateCarResponse> responseObserver) {
        CarMessage carMessage = request.getCar();
        Car car = new Car(carMessage);
        car = carRepository.update(car);
        carMessage = car.toCarMessage();
        UpdateCarResponse response = UpdateCarResponse
                .newBuilder()
                .setCar(carMessage)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(FindByIdRequest request, StreamObserver<FindByIdResponse> responseObserver) {
        int id = request.getProductId();
        Car car = carRepository.findById(id);
        CarMessage carMessage = car.toCarMessage();
        FindByIdResponse response = FindByIdResponse
                .newBuilder()
                .setCar(carMessage)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(FindAllRequest request, StreamObserver<FindAllResponse> responseObserver) {
        List<Car> cars = carRepository.findAll();
        FindAllResponse.Builder responseBuilder = FindAllResponse
                .newBuilder();
        cars.forEach(car -> responseBuilder.addCar(car.toCarMessage()));
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<DeleteResponse> responseObserver) {
        Car car = new Car(request.getCar());
        carRepository.remove(car);
        DeleteResponse response = DeleteResponse
                .newBuilder()
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
