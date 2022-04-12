package esercizio4_6;

import java.awt.*;
import java.util.concurrent.Semaphore;

/**
 * @author gubbriaco
 */
public class AA_BABC {

    private static int cntA=1, cnt=0, seq=1;
    private static Semaphore mutexA=new Semaphore(cntA),
                             mutex=new Semaphore(1),
                             mutexB=new Semaphore(0),
                             mutexC=new Semaphore(0);

    /**
     *
     * @param strings
     */
    public static void main(String...strings){

        while(true){
            new A().start();
            new B().start();
            new C().start();
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }


    private static class A extends Thread{
        @Override public void run(){
            try{
                mutexA.acquire();

                mutex.acquire();
                System.out.print("A");
                cnt++;
                if(cnt==cntA) //stampa A...A
                    mutexB.release();
                if(seq==0) //stampa ABC
                    mutexB.release();
                mutex.release();

            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class B extends Thread{
        @Override public void run(){
            try{
                mutexB.acquire();
                System.out.print("B");
                if(seq==1){
                    mutexA.release();
                    seq=0;
                }
                else //stampa C
                    mutexC.release();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class C extends Thread{
        @Override public void run(){
            try{
                mutexC.acquire();
                System.out.println("C");
                seq=1;
                cnt=0;
                cntA++;
                mutexA.release(cntA);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
