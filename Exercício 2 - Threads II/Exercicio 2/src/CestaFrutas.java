import java.util.ArrayList;

public class CestaFrutas extends Thread{
    public CestaFrutas(String nome) {
        super(nome);
    }
    public void run() {
        try{
            ArrayList<String> frutas = new ArrayList<>();
            frutas.add("Melão");
            frutas.add("Mamão");
            frutas.add("Melancia");
            frutas.add("Uva");
            for(String i : frutas) {
                System.out.println(getName() + " " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException exc) {
            System.out.println("Interrupção na Thread: " + getName());
        }
    }
}
