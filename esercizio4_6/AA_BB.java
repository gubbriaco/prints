package esercizio4_6;

import java.util.concurrent.Semaphore;

/**
 * @author  gubbriaco
 */
public class AA_BB {

    private static int cntA = 1, cnt = 0, cntB = 0;
    private static Semaphore mutexA = new Semaphore(cntA),
            mutex = new Semaphore(1),
            mutexB = new Semaphore(0);

    /**
     * @param strings
     */
    public static void main(String... strings) {

        while (true) {
            new A().start();
            new B().start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    private static class A extends Thread {
        @Override
        public void run() {
            try {
                mutexA.acquire();

                mutex.acquire();
                cnt++;
                System.out.print("A");
                if (cnt == cntA) {
                    cntB=0;
                    mutexB.release(2);
                    cnt = 0;
                    cntA++;
                }
                mutex.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class B extends Thread {
        @Override
        public void run() {
            try {
                mutexB.acquire();

                mutex.acquire();
                cntB++;
                System.out.print("B");
                if (cntB == 2) {
                    cnt = 0;
                    System.out.println();
                    mutexA.release(cntA);
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
