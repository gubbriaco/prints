package esercizio4_4;

import java.util.concurrent.Semaphore;

/**
 * @author gubbriaco
 */
public class AB {

    private static Semaphore mutexA=new Semaphore(1),
                             mutexB=new Semaphore(0);

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
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

    }

    private static class A extends Thread{
        @Override public void run(){
            try{
                mutexA.acquire();
                System.out.print("A");
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
                System.out.println("B");
                mutexA.release();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
