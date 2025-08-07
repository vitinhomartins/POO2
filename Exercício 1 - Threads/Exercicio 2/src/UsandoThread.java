public class UsandoThread {
    public static void main(String[] args) {
        System.out.println("Usando as threads:");
        MyThread m1 = new MyThread("Manuela");
        MyThread m2 = new MyThread("Isabella");
        MyThread m3 = new MyThread("Rafael");
        Thread thread1 = new Thread(m1);
        Thread thread2 = new Thread(m2);
        Thread thread3 = new Thread(m3);

        thread1.start();
        thread2.start();
        thread3.start();
        for(int i = 0; i < 10; i++) {
            System.out.println("Main - " + i);
            try{
                Thread.sleep(2000);
            } catch(InterruptedException exc) {
                System.out.println("Parou aqui!");
            }
        }
    }
}
