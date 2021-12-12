package multiTread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/11/27 3:07 下午
 */
public class PrintABC10TimesByOrder {

    public static void main(String[] args) {

        MajusculeABC maj = new MajusculeABC();
        Thread t_a = new Thread(new Thread_ABC(maj, 'A'));
        Thread t_b = new Thread(new Thread_ABC(maj, 'B'));
        Thread t_c = new Thread(new Thread_ABC(maj, 'C'));

        t_a.start();
        t_b.start();
        t_c.start();

    }

}

class MajusculeABC {
    Lock lock = new ReentrantLock();
    private volatile Character lastCh = null;
    public boolean print(char ch) {
        boolean bool = false;
        boolean isLock = lock.tryLock();
        if (isLock) {
            if (lastCh == null) {
                if (ch == 'A') {
                    this.doPrint(ch);
                    bool = true;
                }
            } else if (lastCh == 'A'){
                if (ch == 'B') {
                    this.doPrint(ch);
                    bool = true;
                }
            } else if (lastCh == 'B') {
                if (ch == 'C') {
                    this.doPrint(ch);
                    bool = true;
                }
            } else {  //lastCh == 'C'
                if (ch == 'A') {
                    this.doPrint(ch);
                    bool = true;
                }
            }
            lock.unlock();
        }

        return bool;
    }

    public void doPrint(char ch) {
        this.lastCh = ch;
        System.out.print(ch);
    }
}


class Thread_ABC implements Runnable {
    private MajusculeABC maj;
    private char ch;
    public Thread_ABC(MajusculeABC maj, char ch) {
        this.maj = maj;
        this.ch = ch;
    }

    @Override public void run() {
        int times = 0;
        while (times < 10) {
            boolean bool = maj.print(this.ch);
            if (bool) {
                times++;
            }
        }

    }
}