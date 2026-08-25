package trabalho.sd.rh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClienteTcp {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
           boolean running = true;
           while (running) {
                showOptions();
                String option = consoleInput.readLine();
                output.println(option);

                switch (option) {
                    case "1" -> {
                        System.out.print(input.readLine()); // Nome
                        output.println(consoleInput.readLine());

                        System.out.print(input.readLine()); // Cargo
                        output.println(consoleInput.readLine());

                        System.out.print(input.readLine()); // Salário
                        output.println(consoleInput.readLine());
                    }
                    case "2" -> {
                        String response;
                        while (!(response = input.readLine()).equals("END")) {
                            System.out.println(response);
                        }
                    }
                    case "3" -> {
                        running = false;
                    }
                    default -> {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                }
           }
        }       
    }

    private static void showOptions() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar funcionário");
        System.out.println("2. Listar funcionários");
        System.out.println("3. Sair");
    }
}
