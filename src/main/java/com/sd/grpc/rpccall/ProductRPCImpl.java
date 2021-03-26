package com.sd.grpc.rpccall;

import com.sd.grpc.models.*;
import com.sd.grpc.repositories.ProductRepository;
import com.sd.grpc.rpc.ProductRPCGrpc;
import com.sd.grpc.rpc.CreateProductRequest;
import com.sd.grpc.rpc.CreateProductResponse;
import com.sd.grpc.rpc.ProductMessage;
import com.sd.grpc.rpc.UpdateProductRequest;
import com.sd.grpc.rpc.UpdateProductResponse;
import com.sd.grpc.rpc.FindByIdRequest;
import com.sd.grpc.rpc.FindByIdResponse;
import com.sd.grpc.rpc.FindAllResponse;
import com.sd.grpc.rpc.DeleteRequest;
import com.sd.grpc.rpc.DeleteResponse;
import io.grpc.stub.StreamObserver;
import java.util.List;

public class ProductRPCImpl extends ProductRPCGrpc.ProductRPCImplBase {

    private final ProductRepository productRepository = new ProductRepository();

    @Override
    public void create(CreateProductRequest request, StreamObserver<CreateProductResponse> responseObserver) {
        Product product = new Product(request);
        product = productRepository.add(product);
        ProductMessage productMessage = product.toProductMessage();
        CreateProductResponse response = CreateProductResponse
                .newBuilder()
                .setProduct(productMessage)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void update(UpdateProductRequest request, StreamObserver<UpdateProductResponse> responseObserver) {
        ProductMessage productMessage = request.getProduct();
        Product product = new Product(productMessage);
        product = productRepository.update(product);
        productMessage = product.toProductMessage();
        UpdateProductResponse response = UpdateProductResponse
                .newBuilder()
                .setProduct(productMessage)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(FindByIdRequest request, StreamObserver<FindByIdResponse> responseObserver) {
        int id = request.getProductId();
        Product product = productRepository.findById(id);
        ProductMessage productMessage = product.toProductMessage();
        FindByIdResponse response = FindByIdResponse
                .newBuilder()
                .setProduct(productMessage)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(FindByIdRequest request, StreamObserver<FindAllResponse> responseObserver) {
        List<Product> products = productRepository.findAll();
        FindAllResponse.Builder responseBuilder = FindAllResponse
                .newBuilder();
        products.forEach(product -> responseBuilder.addProducts(product.toProductMessage()));
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<DeleteResponse> responseObserver) {
        Product product = new Product(request.getProduct());
        productRepository.remove(product);
        DeleteResponse response = DeleteResponse
                .newBuilder()
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
