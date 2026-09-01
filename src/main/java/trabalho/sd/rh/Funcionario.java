package trabalho.sd.rh;

public class Funcionario {
    private String nome;
    private String cargo;
    private double salario;

    public Funcionario() {
        this.nome = "";
        this.cargo = "";
        this.salario = 0.0;
    }

    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public void cadastrarFuncionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "{ " + nome + ", " + cargo + ", R$ " + salario + " }";
    }
}
