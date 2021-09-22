package cn.chihsien.fun.chihsien_lib.JUC.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @describe 什么是CAS ? ===> compareAndSet
 *                     比较并交换
 * @auther chihsien
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        /**
         * @describe
         *       boolean compareAndSet(int expectedValue, int newValue)期望值 更新值
         *       上面我们new出在主内存的AtomicInteger为5 由main线程执行
         *       compareAndSet方法的线程获取该资源的时候，期望主内存的5和自己的快照进行比较时发现没有被修改，
         *              如果还是5，则把他改为2021,
         *       此时进来的其他线程发现被修改之后，
         *              进行对比已经不再是之前的值，无法再进行修改，并且进行更新
         *
         * @param: args
         * @return void
         * @author ChiHsien<br>
         * @version
         */
        System.out.println( atomicInteger.compareAndSet(5,2021) + "现在的值:"+atomicInteger.get());
        System.out.println( atomicInteger.compareAndSet(5,1212) + "现在的值:"+atomicInteger.get());
        System.out.println( atomicInteger.compareAndSet(2021,1212) + "现在的值:"+atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
