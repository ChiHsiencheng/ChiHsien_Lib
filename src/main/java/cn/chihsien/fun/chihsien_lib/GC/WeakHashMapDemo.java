package cn.chihsien.fun.chihsien_lib.GC;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @describe
 * @auther chihsien
 *  该map会在key被置空的时候被GC
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashmap();

        System.out.println("-----------");
        myWeakHashMap();

    }

    private static void myWeakHashMap() {

        WeakHashMap<Integer, String> map = new WeakHashMap<>();

        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t"+ map.size());
    }

    private static void myHashmap() {
        HashMap<Integer, String> map = new HashMap<>();

        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t"+ map.size());
    }


}
