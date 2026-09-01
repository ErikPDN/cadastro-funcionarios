package trabalho.sd.rh.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import trabalho.sd.rh.ConexaoDB;

import java.io.IOException;

public class ServidorGrpc {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException, InterruptedException {
        ConexaoDB.inicializarBanco();
        Server server = ServerBuilder.forPort(PORT)
            .addService(new FuncionarioServiceImpl())
            .build()
            .start();
        
        System.out.println("Servidor gRPC iniciado na porta " + PORT);
        server.awaitTermination();
    }

}