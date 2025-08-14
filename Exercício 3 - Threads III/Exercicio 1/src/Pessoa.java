public class Pessoa implements Runnable{
    Thread myThread;
    private ContaBancaria conta;
    public Pessoa(String nomeThread, ContaBancaria conta){
        myThread = new Thread(this, nomeThread);
        this.conta = conta;
        myThread.start();
    }
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            conta.depositar(200);
            conta.sacar(200);
        }
        System.out.println(myThread.getName() + " -> O saldo da conta é: " + conta.getSaldo());
    }
}
