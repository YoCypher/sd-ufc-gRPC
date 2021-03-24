package com.sd.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
//import com.sd.grpc.rpc.ProductRPCImpl;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                    .forPort(8080)
                   // .addService(new ProductRPCImpl())
                    .build();
        server.start();
        server.awaitTermination();
    }
}
