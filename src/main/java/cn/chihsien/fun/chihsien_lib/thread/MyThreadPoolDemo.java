package cn.chihsien.fun.chihsien_lib.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * @describe
 *  第四种获得/使用java多线程的方式——线程池
 *    底层都是 线程池执行器 ThreadPoolExecutor 这个类
 *      线程池做的工作主要是控制运行的线程的数量,处理过程中将任务加入队列,然后在线程创建后启动这些任务,
 *      如果先生超过了最大数量,超出的数量的线程排队等候,等其他线程执行完毕,再从队列中取出任务来执行.
 *      他的主要特点为:线程复用:控制最大并发数:管理线程.
 *
 *  源码 七个参数
 *     public ThreadPoolExecutor(int corePoolSize,    线程池中的常驻核心线程数
 *                               int maximumPoolSize,  线程池能够容纳同时执行的最大线程数,此值大于等于1
 *                               long keepAliveTime,  多余的空闲线程存活时间,当空间时间达到keepAliveTime值时,多余的线程会被销毁直到只剩下corePoolSize个线程为止
 *                               TimeUnit unit,        keepAliveTime的单位
 *                               BlockingQueue<Runnable> workQueue,   任务队列,被提交但尚未被执行的任务.
 *                               ThreadFactory threadFactory,      表示生成线程池中工作线程的线程工厂,用户创建新线程,一般用默认即可
 *                               RejectedExecutionHandler handler  拒绝策略,表示当线程队列满了并且工作线程大于等于线程池的最大显示 数(maxnumPoolSize)时如何来拒绝.
 *                               ) {
 *         if (corePoolSize < 0 ||
 *             maximumPoolSize <= 0 ||
 *             maximumPoolSize < corePoolSize ||
 *             keepAliveTime < 0)
 *             throw new IllegalArgumentException();
 *         if (workQueue == null || threadFactory == null || handler == null)
 *             throw new NullPointerException();
 *         this.corePoolSize = corePoolSize;
 *         this.maximumPoolSize = maximumPoolSize;
 *         this.workQueue = workQueue;
 *         this.keepAliveTime = unit.toNanos(keepAliveTime);
 *         this.threadFactory = threadFactory;
 *         this.handler = handler;
 *     }
 *
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //获取线程数
        System.out.println(Runtime.getRuntime().availableProcessors());

       // ExecutorService threadPool = Executors.newFixedThreadPool(5);  //一池5个处理线程
       // ExecutorService threadPool = Executors.newSingleThreadExecutor(); //一池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个处理线程
        //模拟10个用户来办理业务 每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                try {TimeUnit.MILLISECONDS.sleep(200);}catch (InterruptedException e){ e.printStackTrace();}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
