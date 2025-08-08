import java.util.ArrayList;
import java.util.Scanner;

public class CestaFrutasInterativa extends Thread{
    public CestaFrutasInterativa(String nome) {
        super(nome);
    }
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> cestaFrutas = new ArrayList<>();
            for(int i = 0; i < 5; i++) {
                System.out.println("Digite uma fruta:");
                cestaFrutas.add(scanner.nextLine());
                Thread.sleep(1000);
            }
            for(String i : cestaFrutas) {
                System.out.println(getName() + " " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException exc) {
            System.out.println("Interropendendo a thread: " + getName());
        }
    }
}
