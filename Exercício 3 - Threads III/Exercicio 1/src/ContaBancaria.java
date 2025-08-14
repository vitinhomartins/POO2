public class ContaBancaria {
    private double saldo = 1000;
    public synchronized void sacar(double valor) {
        if((saldo-valor) < 0) {
            System.out.println("Nao é possível tirar e deixar saldo negativo");
            return;
        } else {
            saldo-=valor;
        }
    }
    public synchronized void depositar(double valor) {
        saldo+= valor;
    }
    public synchronized double getSaldo() {
        return saldo;
    }
}
