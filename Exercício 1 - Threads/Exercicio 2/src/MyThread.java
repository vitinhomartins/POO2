public class MyThread implements Runnable{
    private String nome;
    public MyThread(String nome){
        this.nome = nome;
    }
    @Override
    public void run() {
        System.out.println(nome + " iniciada!");
        try{
            for(int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(nome + " -> contagem = " + i);
            }
        }
        catch (InterruptedException exc) {
            System.out.println(nome + " interrompida!");
        }
    }
}
