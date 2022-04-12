package esercizio4_7;

import java.awt.*;
import java.util.concurrent.Semaphore;

/**
 * @author gubbriaco
 */
public class AABB {

    private static Semaphore mutexA=new Semaphore(2),
                             mutexB=new Semaphore(0),
                             mutex=new Semaphore(1);
    private static int cntA=0, cntB=0;

    /**
     *
     * @param strings
     */
    public static void main(String...strings){

        while(true){
            new A().start();
            new B().start();

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }


    private static class A extends Thread{
        @Override public void run(){
            try {
                mutexA.acquire();

                mutex.acquire();
                System.out.print("A");
                cntA++;
                if (cntA == 2) {
                    mutexB.release(2);
                    cntB=0;
                }
                mutex.release();

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class B extends Thread{
        @Override public void run(){
            try{
                mutexB.acquire();

                mutex.acquire();
                System.out.print("B");
                cntB++;
                if(cntB==2) {
                    mutexA.release(2);
                    cntA=0;
                    System.out.println();
                }
                mutex.release();

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
