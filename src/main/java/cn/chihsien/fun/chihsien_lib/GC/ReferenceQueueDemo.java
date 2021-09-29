package cn.chihsien.fun.chihsien_lib.GC;

import lombok.SneakyThrows;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @describe
 * @auther chihsien
 */
public class ReferenceQueueDemo {

    @SneakyThrows
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("=============");
        o1 = null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());//GC之后被放进了引用队列里

    }
}
