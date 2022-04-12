package esercizio4_6;

import java.util.concurrent.Semaphore;

/**
 * @author gubbriaco
 */
public class AAAAB {

    private static int cntA=6, cnt=0;
    private static Semaphore mutexA=new Semaphore(cntA),
                             mutex=new Semaphore(1),
                             mutexB=new Semaphore(0);

    /**
     *
     * @param strings
     */
    public static void main(String...strings){

        while(cntA>0) {
            new A().start();
            new B().start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
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
                mutex.release();
                cnt++;
                if(cnt==cntA)
                    mutexB.release();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class B extends Thread{
        @Override public void run(){
            try{
                mutexB.acquire();
                System.out.println("B");
                cnt=0;
                cntA--;
                if(cntA==0)
                    System.exit(0);
                mutexA.release(cntA);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
