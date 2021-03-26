package com.sd.grpc;

import com.sd.grpc.rpccall.ProductRPCImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                    .forPort(8080)
                    .addService(new ProductRPCImpl())
                    .build();
        server.start();
        server.awaitTermination();
    }
}
