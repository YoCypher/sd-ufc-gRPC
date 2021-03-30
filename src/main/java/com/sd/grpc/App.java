package com.sd.grpc;

import com.sd.grpc.rpccall.CarRPCImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                    .forPort(5000)
                    .addService(new CarRPCImpl())
                    .build();
        server.start();
        server.awaitTermination();
    }
}
