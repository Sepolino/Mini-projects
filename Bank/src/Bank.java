import java.util.Scanner;
import java.util.HashMap;

class Conta{
    private String nome;
    private int password;
    private int id;
    private double saldo;

    public Conta(String nome, int id){
        this.nome = nome;
        this.id = id;
        this.password = password;
        this.saldo = saldo;
    }

    public void Depositar(double valor){
        if(valor <= 0){
            System.out.println("Insira um valor maior que 0!");
        }
        else{
            saldo += valor;
            System.out.println("Valor adicionado em seu saldo com sucesso!");
        }
    }

    public void Saque(double valor){
        if(valor <= 0){
            System.out.println("Insira um valor maior que 0!");
        }
        else if(valor > saldo){
            System.out.println("Saldo insuficiente!");
            System.out.println("Saldo: R$" + saldo);
        }
        else{
            saldo -= valor;
            System.out.println("Saque concluído com sucesso!");
        }
    }

    public void Transferir(Conta destino, double valor){
        if(destino == null){
            System.out.println("Conta não encontrada! Tente novamente.");
        }
        if(valor <= 0){
            System.out.println("Insira um valor maior que 0!");
        }
        else if(valor > saldo){
            System.out.println("Saldo insuficiente!");
            System.out.println("Saldo: R$" + saldo);
        }
        else{
            saldo -= valor;
            System.out.println("Saque concluído com sucesso!");
        }
    }

    public void exibirDados(){
        System.out.printf("Nome: %s.10 | ID: %d.10 | Saldo: R$%f", nome, id, saldo);
    }

    public String getNome(){
        return nome;
    }

    public int getId(){
        return id;
    }

    public double getSaldo(){
        return saldo;
    }

    public int getPsw(){
        return password;
    }

}

class Banco{
    private HashMap<Integer, Conta> contas;
    private int nmrId = 1;

    public Banco(){
        contas = new HashMap<>();
    }
    public Conta criarConta(String nome, int passw, double saldo){
        Conta novaConta = new Conta(nome, nmrId);
        nmrId++;
        System.out.println("Conta criada com sucesso! ID/Número da conta: " + novaConta.getId());
        return novaConta;
    }

    public Conta acessarConta(int id){
        return contas.get(id);
    }

    public Conta mudarSenha(int password1, int password2){
        if(password1 != password2){
            System.out.println("As senhas digitadas não estão iguais, digite novamente!");
        }
        else{
            System.out.println("Senha alterada com sucesso!");
        }

    }

    public void lista(){
        if(contas.isEmpty()){
            System.out.println("Não existe nenhuma conta criada!");
            return;
        }
        else{
            for (Conta conta: contas.values()){
                conta.exibirDados();
            }
        }
    }


}
