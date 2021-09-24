package cn.chihsien.fun.chihsien_lib.thread;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 如何配置最佳的maximumPoolSize:
 *   1. CPU密集型
 *      需要大量的运算 而且没有阻塞 CPU一直全速运行
 *      CPU密集任务只有在真正的多核CPU才能得到加速
 *
 *      CPU密集型任务尽可能少的配置线程数:
 *      一般公式: CPU核数+1个线程的线程池
 *
 *   2.I/O 密集型
 *      由于I/O密集型任务并不是一直在执行任务 则应该尽可能多的线程 如CPU核数*2
 *      IO密集型 即该任务需要大量的IO 即大量的阻塞
 *      在单线程上运行IO密集型业务会导致浪费大量的CPU运算能力在等待
 *      所以IO密集型任务中使用多线程可以大大的加速程序运行 即使在单核CPU上 这种加速主要就是为了利用被浪费掉的阻塞时间
 *
 *      IO密集型情境下，大部分线程都阻塞，所以需要多配置线程数:
 *       实践参考公式: CPU核数/1-阻塞系数  阻塞系数在0.8~0.9之间 一般取乐观值 0.9
 *
 *       比如八核CPU: 8/1-0.8 =80个线程数
 *       由于超线程技术的广泛应用 该数值会随着不同架构而出现不一样的性能差异 三代线程撕裂者实测存在高达45%的溢值
 *       在云原生下这个数值一般由服务商提供参考量
 */
public class MyThreadPool {

    public static void main(String[] args) {
        //获取硬件CPU核心数和线程数
        System.out.println(Runtime.getRuntime().availableProcessors());

        //自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,//如何配置最佳最大线程数
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                //new java.util.concurrent.ThreadPoolExecutor.AbortPolicy()
                //new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy()
                //new java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy()
                new java.util.concurrent.ThreadPoolExecutor.DiscardPolicy()
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
