package cn.chihsien.fun.chihsien_lib.thread;


import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * @describe
 * @auther chihsien
 */
public class BankDemo {

    public static void main(String[] args) {
        threadPoolInit();
    }

    private static void threadPoolInit() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,    //当值窗口
                5, //最大窗口
                100L, //空余线程最大存活时间
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),//等候区 队列
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 1; i <= 6; i++) { //模拟6个顾客来办理业务 受理窗口max只有5个
                final int tmpI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "号窗口，" + "服务顾客" + tmpI);
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
