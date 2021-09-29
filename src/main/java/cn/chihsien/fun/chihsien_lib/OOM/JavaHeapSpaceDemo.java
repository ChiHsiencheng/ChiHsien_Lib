package cn.chihsien.fun.chihsien_lib.OOM;

import java.util.Random;

/**
 * @describe
 * @auther chihsien
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "chihsien";
        while (true){
            str+= str + new Random().nextInt(1111111111)+new Random().nextInt(222222222);
            str.intern();
            //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        }
    }
}
