package esercizio4_6;

import java.util.concurrent.Semaphore;

/**
 * @author gubbriaco
 */
public class ABABC {

    private static int seq=0;
    private static Semaphore mutexA=new Semaphore(1),
                             mutexB=new Semaphore(0),
                             mutexC=new Semaphore(0);

    public static void main(String...strings){

        //ABABC
        //ABABC
        //ABCBC
        //...
        //ABABC
        //...
        while(true) {
            new A().start();
            new B().start();
            new C().start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static class A extends Thread{
        @Override public void run(){
            try{
                mutexA.acquire();
                System.out.print("A");
                if(seq == 0){
                    //per stampare sequenza AB iniziale
                    mutexB.release();
                }
                else //per stampare sequenza ABC finale
                    mutexB.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class B extends Thread{
        @Override public void run(){
            try{
                mutexB.acquire();
                System.out.print("B");
                if(seq == 0){
                    seq++;
                    mutexA.release();
                }
                else
                    mutexC.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class C extends Thread{
        @Override public void run(){
            try{
                mutexC.acquire();
                System.out.println("C");
                //inizializzare seq di nuovo a 0 per stampare di nuovo inizialmente AB
                seq=0;
                mutexA.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
