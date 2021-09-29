package cn.chihsien.fun.chihsien_lib.OOM;

/**
 * @describe
 * @auther chihsien
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {

        stackOverflowError();
    }
}
