package esercizio4_1;

import java.util.concurrent.Semaphore;

/**
 * Implementare in Java gli scenari descritti nei due esempi di mutua esclusione e sincronizzazione descritti in questa
 * esercitazione, in cui le due istruzioni A e B, eseguite dai thread p1 e p2, consistono in una stampa su terminale di
 * "A" e "B" rispettivamente.
 *
 * @author gubbriaco
 */
public class Soluzione1 {

    private static Semaphore mutex = new Semaphore(1);

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
            try {
                mutex.acquire();
                System.out.print("A");
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class B extends Thread {
        @Override public void run(){
            try {
                mutex.acquire();
                System.out.println("B");
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
