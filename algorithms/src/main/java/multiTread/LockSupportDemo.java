package multiTread;

import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/11/27 11:11 上午
 */
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            LockSupport.park();
            System.out.println(sum);
            System.out.println("subThread finished");
        });

        a.start();
        LockSupport.unpark(a);
        System.out.println("main thread finished");
    }
}
