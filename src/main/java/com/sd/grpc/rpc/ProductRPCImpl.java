package com.sd.grpc.rpc;

import com.sd.grpc.models.Product;
import com.sd.grpc.repositories.ProductRepository;
import io.grpc.stub.StreamObserver;

public class ProductRPCImpl extends ProductRPCGrpc.ProductRPCImplBase {
    
	private ProductRepository productRepository = new ProductRepository();
	
	@Override
    public void create(CreateProductRequest request, StreamObserver<com.sd.grpc.rpc.CreateProductResponse> responseObserver) {
        Product product = new Product(-1, request.getName(), request.getBrand(), request.getFabricationYear(), request.getModelYear(), request.getPrice() );
        product = productRepository.add(product);
        ProductMessage productMessage = ProductMessage.newBuilder()
        		.setId(product.getId())
        		.setName(product.getName())
        		.setBrand(product.getBrand())
        		.setFabricationYear(product.getFabricationYear())
        		.setModelYear(product.getModelYear())
        		.setPrice(product.getPrice()).build();
        
        CreateProductResponse response = CreateProductResponse.newBuilder()
        		.setProduct(productMessage).build();
        
       responseObserver.onNext(response);
       responseObserver.onCompleted();
	}
	
    @Override
    public void update(UpdateProductRequest request, StreamObserver<com.sd.grpc.rpc.Product> responseObserver) {
    	ProductMessage productMessage = request.getProduct();
    	Product product = new Product(productMessage.getId(), productMessage.getName(), productMessage.getBrand(), productMessage.getFabricationYear(), productMessage.getModelYear(), productMessage.getPrice());
    	product = productRepository.update(product);
    	
    			productMessage = ProductMessage.newBuilder()
        		.setId(product.getId())
        		.setName(product.getName())
        		.setBrand(product.getBrand())
        		.setFabricationYear(product.getFabricationYear())
        		.setModelYear(product.getModelYear())
        		.setPrice(product.getPrice()).build();
    			
    		UpdateProductResponse response = UpdateProductResponse.newBuilder()
    	        		.setProduct(productMessage).build();
    	        
    			responseObserver.onNext(response);
    			responseObserver.onCompleted();
    }

    @Override
    public void findById(FindByIdRequest request, StreamObserver<com.sd.grpc.rpc.FindByIdResponse> responseObserver) {
        super.findById(request, responseObserver);
    }

    @Override
    public void findAll(FindByIdRequest request, StreamObserver<com.sd.grpc.rpc.FindAllResponse> responseObserver) {
        super.findAll(request, responseObserver);
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<com.sd.grpc.rpc.DeleteResponse> responseObserver) {
        super.delete(request, responseObserver);
    }
}
