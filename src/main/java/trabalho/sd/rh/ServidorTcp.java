package trabalho.sd.rh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTcp {
    private static final int PORT = 12345;
    private static final int THREAD_POOL_SIZE = 10;
    private static final List<Funcionario> funcionarios = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Servidor TCP iniciado na porta " + PORT);

            while (true) {
                Socket clientSocket = server.accept();
                Funcionario funcionario = new Funcionario();
                pool.submit(() -> handleClient(clientSocket, funcionario));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    private static void handleClient(Socket clientSocket, Funcionario funcionario) {
        try (clientSocket;
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true)
        ) {
            boolean running = true;
            while (running) {
                String option = input.readLine();
                if (option == null) break;
                
                switch (option) {
                    case "1" -> { 
                        output.println("Digite o nome do funcionário:");
                        String nome = input.readLine();

                        output.println("Digite o cargo do funcionário:");
                        String cargo = input.readLine();

                        output.println("Digite o salário do funcionário:");
                        double salario = Double.parseDouble(input.readLine());

                        funcionario.cadastrarFuncionario(nome, cargo, salario);
                        funcionarios.add(funcionario);
                    }
                    case "2" -> {
                        output.println("Lista de funcionários:");
                        for (Funcionario f : funcionarios) {
                            output.println(f);
                            
                        }
                        output.println();
                        output.println("END"); // Indica fim da lista
                    }
                    case "3" -> running = false;
                    default -> output.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}
