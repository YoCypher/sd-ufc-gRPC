package com.sd.grpc.javafx;

import com.sd.grpc.models.Car;
import com.sd.grpc.rpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import io.grpc.stub.StreamObserver;

import java.net.URL;
import java.util.ResourceBundle;

public class CarController implements Initializable {

    @FXML
    private TextField nameInput;

    @FXML
    private TextField brandInput;

    @FXML
    private TextField fabricationYearInput;

    @FXML
    private TextField modelYearInput;

    @FXML
    private TextField priceInput;

    @FXML
    private TextField carIdInput;

    @FXML
    private ListView<String> carList;

    private Car currentCar;

    private CarRPCGrpc.CarRPCBlockingStub client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(
                "127.0.0.1", 5000)
                .usePlaintext()
                .build();
        this.client = CarRPCGrpc.newBlockingStub(channel);
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearForm();
        carList.getItems().clear();
    }

    void clearForm() {
        nameInput.clear();
        brandInput.clear();
        fabricationYearInput.clear();
        modelYearInput.clear();
        priceInput.clear();
        carIdInput.clear();
        currentCar = null;
    }

    @FXML
    void handleCreate(ActionEvent event) {
        CreateCarRequest request = CreateCarRequest
                .newBuilder()
                .setName(nameInput.getText())
                .setBrand(brandInput.getText())
                .setFabricationYear(Integer.parseInt(fabricationYearInput.getText()))
                .setModelYear(Integer.parseInt(modelYearInput.getText()))
                .setPrice(Double.parseDouble(priceInput.getText()))
                .build();
        try {
            CreateCarResponse response = client.create(request);
            showNotification(NOTIFY_SUCCESS);
            handleClear(event);
        } catch (Exception exception) {
            showNotification(NOTIFY_ERROR);
        }
    }

    private static final int NOTIFY_SUCCESS = 0;
    private static final int NOTIFY_ERROR = 1;

    void showNotification(int code) {
        if(code == NOTIFY_SUCCESS) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ok");
            alert.showAndWait();
        } else {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error occurred");
            alert.showAndWait();
        }
    }

    @FXML
    void handleDelete(ActionEvent event) {
        if (currentCar != null) {
            DeleteRequest request = DeleteRequest
                    .newBuilder()
                    .setCar(currentCar.toCarMessage())
                    .build();
            try {
                DeleteResponse response = this.client.delete(request);
                showNotification(NOTIFY_SUCCESS);
                clearForm();
            } catch (Exception exception) {
                showNotification(NOTIFY_ERROR);
            }
        }
    }

    @FXML
    void handleFindById(ActionEvent event) {
        FindByIdRequest request = FindByIdRequest
                .newBuilder()
                .setProductId(Integer.parseInt(carIdInput.getText()))
                .build();
        try {
            FindByIdResponse response = this.client.findById(request);
            this.currentCar = new Car(response.getCar());
            updateFormView();
        } catch (Exception exception) {
            showNotification(NOTIFY_ERROR);
        }
    }

    void updateFormView() {
        nameInput.setText(currentCar.getName());
        brandInput.setText(currentCar.getBrand());
        fabricationYearInput.setText(currentCar.getFabricationYear() + "");
        modelYearInput.setText(currentCar.getModelYear() + "");
        priceInput.setText(currentCar.getPrice() + "");
    }

    @FXML
    void handleListAll(ActionEvent event) {
        FindAllRequest request = FindAllRequest.newBuilder().build();
        try {
            FindAllResponse response = this.client.findAll(request);
            carList.getItems().clear();
            response.getCarList().forEach(carMessage -> {
                Car car = new Car(carMessage);
                carList.getItems().add(car.toLine());
            });
        } catch (Exception exception) {
            showNotification(NOTIFY_ERROR);
        }
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        if (currentCar != null) {
            currentCar.setName(nameInput.getText());
            currentCar.setBrand(brandInput.getText());
            currentCar.setFabricationYear(Integer.parseInt(fabricationYearInput.getText()));
            currentCar.setModelYear(Integer.parseInt(modelYearInput.getText()));
            currentCar.setPrice(Double.parseDouble(priceInput.getText()));
            UpdateCarRequest request = UpdateCarRequest
                    .newBuilder()
                    .setCar(currentCar.toCarMessage())
                    .build();
            try {
                UpdateCarResponse response = this.client.update(request);
                showNotification(NOTIFY_SUCCESS);
            } catch (Exception e) {
                showNotification(NOTIFY_ERROR);
            }
        }
    }
}

