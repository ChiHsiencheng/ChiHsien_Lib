package cn.chihsien.fun.chihsien_lib.GC;

import java.lang.ref.SoftReference;

/**
 * @describe 软引用 内存够用就保留 不够用就回收
 *              内存敏感的程序 比如高速缓存
 *              软引用和弱引用大量用在Mybatis源码里
 */
public class SoftReferrnceDemo {

    public static void main(String[] args) {
        softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }

    /*
    * 内存够用时保留 不够用就回收
    * */
    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);//软引用
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null ;
        System.gc();

        System.out.println(o1);//o1置空
        System.out.println(softReference.get());//内存够用 不会被回收
    }

    /*
     *   JVM配置，故意产生大对象并配置小内存 产生OOM 查看软引用回收情况
     *      -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);//软引用
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null ;
        //OOM自动GC

        try{
            byte[] bytes = new byte[30 * 1024 * 1024 ];
        }catch(Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }
}
