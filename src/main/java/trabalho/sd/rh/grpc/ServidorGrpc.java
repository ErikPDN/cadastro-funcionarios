package trabalho.sd.rh.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class ServidorGrpc {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(PORT)
            .addService(new FuncionarioServiceImpl())
            .build()
            .start();
        
        System.out.println("Servidor gRPC iniciado na porta " + PORT);
        server.awaitTermination();
    }

}