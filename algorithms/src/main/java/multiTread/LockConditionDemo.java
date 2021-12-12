package multiTread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/11/27 11:21 上午
 */
public class LockConditionDemo {

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();


    final String[] items = new String[1];
    int putStr, takeStr, count;

    public void put(String x) {
        lock.lock();
        try {

            while (count == items.length) {
               notFull.await();
            }
            items[putStr] = x;
            if (++putStr == items.length) {
                putStr = 0;
            }
            count++;
            notEmpty.signal();
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public String take() {
        lock.lock();
        try {

            while (count == 0) {
                notEmpty.await();
            }
            System.out.println(items[takeStr]);
            String x = items[takeStr];
            if (++takeStr == items.length) {
                takeStr = 0;
            }
            count--;
            notFull.signal();
            return x;
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        LockConditionDemo demo = new LockConditionDemo();
        Thread t1 = new Thread(new Runnable() {
            @Override public void run() {
                for (int i = 0; i < 100; i++) {
                    demo.put(String.valueOf(i));
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override public void run() {
                for (int i = 0; i < 50; i++) {
                    demo.take();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override public void run() {
                for (int i = 0; i < 50; i++) {
                    demo.take();
                }
            }
        });

        t2.start();

        t3.start();
        t1.start();
        t1.join();
        t2.join();
        t3.join();

    }
}
