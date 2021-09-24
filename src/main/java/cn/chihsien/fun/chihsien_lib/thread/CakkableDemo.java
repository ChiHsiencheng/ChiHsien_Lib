package cn.chihsien.fun.chihsien_lib.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("FutureTask Come in");
        try {TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e){ e.printStackTrace();}
        return 1024;
    }
}


public class CakkableDemo {
    public static void main(String[] args) throws Exception{
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();//不会再开线程 同样的任务 共用一个futureTask 复用前面的结果

        System.out.println(Thread.currentThread().getName() + "------");

        int resulte1 = 100;

        //判断
       /* while (!futureTask.isDone()){

        }*/

        //获得Callable线程的计算结果 如果没有计算完成要去强求，会造成堵塞。建议放在最后
        int resulte2 = futureTask.get();
        System.out.println("result:"+(resulte1+resulte2));
    }


}
