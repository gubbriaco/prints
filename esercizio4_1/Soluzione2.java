package esercizio4_1;

import java.util.concurrent.Semaphore;

public class Soluzione2 {

    private static Semaphore mutex = new Semaphore(0);

    /**
     *
     * @param strings
     */
    public static void main(String...strings){

        Thread p1 = new A();
        Thread p2 = new B();

        p1.start();
        p2.start();

    }

    private static class A extends Thread {
        @Override public void run(){
            System.out.print("A");
            mutex.release();
        }
    }

    private static class B extends Thread {
        @Override public void run(){
            try {
                mutex.acquire();
                System.out.println("B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
