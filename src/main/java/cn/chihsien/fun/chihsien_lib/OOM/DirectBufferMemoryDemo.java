package cn.chihsien.fun.chihsien_lib.OOM;

import java.nio.ByteBuffer;

/**
 * @describe
 * @auther chihsien
 * VM配置:
 *      -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *      Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 *  写NIO程序经常使用ByteBuffer来读写数据 这是一种基于通道Channel与缓冲区(Buffer)的I/O的方式
 *  它可以使用Native函数库直接分配堆外内存 然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作
 *  这样能够提高性能 避免了在java堆和Native堆中来回copy数据
 *      ByteBuffer.allocate(capability)第一种方式是分配JVM堆内存 属于GC管辖范畴 需要拷贝 所以速度很慢
 *      ByteBuffer.allocateDirect(capability)第二种方式是分配OS本地内存 不属于GC管辖范围 不需要拷贝
 *
 *  但如果不断分配本地内存，堆内存很少使用 那么JVM就要进行GC DirectBuffer对象们就不会被回收
 *  这时候堆内存充足 但本地内存可能已经使用光了 再次尝试分配本地内存就会OutOfMemoryError
 *
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
       // System.out.println("JVM可用MAX内存:"+(sun.misc.VM.maxDirectMemory() / (double)1024/1024 +"MB"));
        try {Thread.sleep(3000);}catch (InterruptedException e){ e.printStackTrace();}
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
