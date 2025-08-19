import java.util.Scanner;
import java.util.ArrayList;

class Conta{
    private String nome;
    private int id;
    private double saldo;

    public Conta(String nome, int id, double saldo){
        this.nome = nome;
        this.id = id;
        this.saldo = saldo;
    }

    public void depositar(double valor){
        if(valor > 0){
            saldo += valor;
            System.out.println("Depósito feito com sucesso!");
        }

        else{
            System.out.println("ERRO! Valor inválido. Faça um depósito de um valor maior que 0!");
        }
    }

    public void sacar(double valor){
        if(valor > 0){
            saldo -= valor;
            System.out.println("Saque feito com sucesso!");
        }
        if(valor > saldo){
            System.out.println("Saldo insuficiente! Tente outro valor..");
        }
        else{
            System.out.println("ERRO! Valor inválido. Faça um saque de um valor maior que 0!");
        }
    }

    public void transferir(Conta destino, double valor){
        if(destino == null){
            System.out.println("ERRO! ID inválido ou conta inexistente.");
        }
        if(valor > saldo){
            System.out.println("Saldo insuficiente! Tente outro valor..");
        }
        if(valor <= 0){
            System.out.println("ERRO! Valor inválido. Faça uma transferência de um valor maior que 0!");
        }
        else{
            saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferência realizada com sucesso!");
        }
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

    public void exibirDados(){
        System.out.println("Nome: " + nome + ", ID: " + id + ", Saldo: " + saldo);
    }
}

class Banco{
    private ArrayList<Conta> contas;

    public Banco(){
        contas = new ArrayList<>();
    }

    public Conta criarConta(String nome, int id, double saldo){
        Conta novaConta = new Conta(nome, id , saldo);
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso! ID da conta: " + id);
        return novaConta;
    }

    public Conta buscarConta(int id){
        for(Conta conta : contas){
            if(conta.getId() == id){
                return conta;
            }
        }
        return null;
    }

    public void lista(){
        if(contas.isEmpty()){
            System.out.println("Não existe nenhuma conta bancária registrada..");
        }
        else{
            for(Conta conta : contas){
                conta.exibirDados();
            }
        }
    }


}

public class Bank{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();
        byte op = 0;

        while(op != 9){
            System.out.println("================BankOS 1.0=============");
            System.out.println("1. Criar uma nova conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir saldo");
            System.out.println("5. Consultar conta");
            System.out.println("6. Exibir uma lista de contas");
            System.out.println("9. Sair");
            op = Byte.parseByte(scanner.nextLine());

            switch(op){
                case 1 :
                    System.out.println("==============Nova conta=============");
                    System.out.print("Digite um nome de usuário: ");
                    String nomeCriar = scanner.nextLine();

                    System.out.print("Digite um ID: ");
                    int idCriar = Integer.parseInt(scanner.nextLine());

                    System.out.print("Insira o saldo inicial: ");
                    double saldoCriar = Double.parseDouble(scanner.nextLine());

                    banco.criarConta(nomeCriar, idCriar, saldoCriar);
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 2 :
                    System.out.println("==============Depositar na conta==========");
                    System.out.print("Digite o ID da conta que você quer depositar: ");
                    int idDep = Integer.parseInt(scanner.nextLine());

                    System.out.print("Digite o valor que você quer depositar: ");
                    double valorDep = Double.parseDouble(scanner.nextLine());

                    Conta cDep = banco.buscarConta(idDep);
                    if(cDep != null ) cDep.depositar(valorDep);
                    else System.out.println("Conta não encontrada. Insira um ID válido!");
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("===============Saque===============");
                    System.out.print("Digite o ID da conta que você quer sacar: ");
                    int idSaq = Integer.parseInt(scanner.nextLine());

                    System.out.print("Digite o valor que você quer sacar: ");
                    double valorSaq = Double.parseDouble(scanner.nextLine());

                    Conta cSaq = banco.buscarConta(idSaq);
                    if(cSaq != null) cSaq.depositar(valorSaq);
                    else System.out.println("Conta não encontrada. Insira um ID válido!");
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("==============Transferência============");
                    System.out.print("Digite o ID da conta que você quer sacar para transferência: ");
                    int idTrf1 = Integer.parseInt(scanner.nextLine());

                    System.out.print("Digite o valor que você quer sacar: ");
                    double valorTrf = Double.parseDouble(scanner.nextLine());

                    System.out.print("Digite o ID da conta que você realizar a transferência: ");
                    int idTrf2 = Integer.parseInt(scanner.nextLine());

                    Conta cTrf1 = banco.buscarConta(idTrf1);
                    Conta cTrf2 = banco.buscarConta(idTrf2);
                    if(cTrf1 != null && cTrf2 != null){
                        cTrf1.transferir(cTrf2, valorTrf);
                    }
                    else{
                        System.out.println("Conta não encontrada. Digite um ID válido.");
                    }
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 5:
                    System.out.println("=================Consultaweb============");
                    System.out.print("Digite o ID para encontrar/consultar a conta: ");
                    int idCon = Integer.parseInt(scanner.nextLine());

                    Conta cCon = banco.buscarConta(idCon);
                    if(cCon == null){
                        System.out.println("===========================");
                        System.out.println("Nome: " + cCon.getNome());
                        System.out.println("ID: " + cCon.getId());
                        System.out.println("Saldo: " + cCon.getSaldo());
                        System.out.println("Pressione Enter para continuar...");
                        scanner.nextLine();
                    }
                    else{
                        System.out.println("Conta não encontrada! Tente novamente...");
                    }
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println("==================Lista=================");
                    banco.lista();
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                    break;
            }
        }
    }
}