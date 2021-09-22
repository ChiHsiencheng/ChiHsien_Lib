package cn.chihsien.fun.chihsien_lib.JUC;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        closeDoor();
        sixCountry();

    }

    /**
     * 关门案例
     *
     * @throws InterruptedException
     */
    private static void closeDoor() throws InterruptedException {
        int num = 6;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "上完自习");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t班长锁门离开教室");
    }


        /**
         * 秦灭六国 一统华夏
         * @throws InterruptedException
         */
        private static void sixCountry() throws InterruptedException {
            CountDownLatch countDownLatch = new CountDownLatch(6);
            for (int i = 1; i <= 6; i++) {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "国,灭亡");
                    countDownLatch.countDown();
                }, CountryEnum.forEach(i).getName()).start();
            }
            countDownLatch.await();
            System.out.println("秦统一");
        }


    }

