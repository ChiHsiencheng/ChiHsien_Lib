package cn.chihsien.fun.chihsien_lib.OOM;

import java.util.ArrayList;

/**
 * @describe
 * @auther chihsien
 * VM配置:
 *   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *    GC回收时间过长抛出 java.lang.OutOfMemoryError: GC overhead limit exceeded
 *    超过98%的时间用来做GC并且回收了不到2%的堆内存
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        ArrayList<Object> list = new ArrayList<>();

        try{
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch(Throwable e){
            System.out.println("***********"+i);
            e.printStackTrace();
            throw  e;
        }
    }
}
