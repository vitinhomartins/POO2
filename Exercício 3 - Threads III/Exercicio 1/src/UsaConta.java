public class UsaConta {
    public static void main(String[] args) {
        ContaBancaria conta1 = new ContaBancaria();
        Pessoa pessoa1 = new Pessoa("Filha 1", conta1);
        Pessoa pessoa2 = new Pessoa("Filha 2", conta1);
        try {
            pessoa1.myThread.join();
            pessoa2.myThread.join();
        } catch (InterruptedException e) {
            System.out.println("Threads interrompidas!");
        }
    }
}
