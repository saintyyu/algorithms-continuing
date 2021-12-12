package multiTread;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/12/3 10:52 上午
 */
public class CyclicPrint {

    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    private static Character[] chars =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R',
                    'S',
                    'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    Set<Character> set = new HashSet<>(Arrays.asList(chars));

    volatile Character lastCh = null;

    volatile boolean charPrinted = false;

    public boolean print(char ch) {
        boolean result = false;
        boolean bool = lock.tryLock();
        if (bool) {
            if (lastCh == null) {
                if (set.contains(ch)) {
                    doPrint(ch);
                    result = true;
                }
            } else if (set.contains(lastCh)) {
                if (!set.contains(ch)) {
                    doPrint(ch);
                    result = true;
                }
            } else {
                if (set.contains(ch)) {
                    doPrint(ch);
                    result = true;
                }
            }
            lock.unlock();
        }
        return result;
    }

    public void doPrint(char ch) {
        this.lastCh = ch;
        System.out.print(ch);
    }

    public void printChar(char ch) {
        lock.lock();
        try {
            System.out.print(ch);
            if (!charPrinted) {
                charPrinted = true;
            }
            condition2.signal();
            condition1.await();
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public void printDigit(char digit) {
        lock.lock();
        try {
            condition1.signal();
            if (!charPrinted) {
                condition2.await();
                System.out.print(digit);
            } else {
                System.out.print(digit);
                condition2.await();
            }


        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {

        CyclicPrint cyclicPrint = new CyclicPrint();

        Thread charThread = new Thread(new Runnable() {
            @Override public void run() {
                int cycle = 0;
                while (true) {
                    cycle++;
                    if (cycle > 10) {
                        break;
                    }
                    for (int i = 0; i < 26; ) {
                        boolean bool = cyclicPrint.print(chars[i]);
                        if (bool) {
                            i++;
                        }
                    }
                }
            }
        });
        Thread digitThread = new Thread(new Runnable() {
            @Override public void run() {
                int cycle = 0;
                while (true) {
                    cycle++;
                    if (cycle > 26) {
                        break;
                    }
                    for (int i = 0; i < 10; ) {
                        boolean bool = cyclicPrint.print(digits[i]);
                        if (bool) {
                            i++;
                        }
                    }
                }
            }
        });

        charThread.start();
        digitThread.start();

        charThread.join();
        digitThread.join();
    }
}


//题目二
class Account {
    private int persistStatus;//持久化状态，repository通过该字段来判断是否需要持久化
    private BigDecimal originAmount;//原始值，用于判断是否支持转账
    private BigDecimal transAmount;//转账值
    private int status;//账号状态，代表账号是否正常
    private int version;//乐观锁版本号
}

class TransformService {

    public void transAmount(Account from, Account to, BigDecimal amount) {
        //略
    }
}


class PrintTest {
    private static Character[] chars =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R',
                    'S',
                    'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static volatile boolean flag = false;
    public static void main(String[] args) throws Exception {
        Thread main = Thread.currentThread();
        Thread digitThread = new Thread(new Runnable() {
            @Override public void run() {
                int cycle = 0;
                while (true) {
                    cycle++;
                    if (cycle > 26) {
                        break;
                    }
                    if (!flag) {
                        LockSupport.park();
                    }
                    for (int i = 0; i < 10;i++) {
                        System.out.print(digits[i]);
                        LockSupport.unpark(main);
                        LockSupport.park();
                    }
                }
            }
        });

        digitThread.start();

        int cycle = 0;
        while (true) {
            cycle++;
            if (cycle > 10) {
                LockSupport.unpark(digitThread);
                break;
            }
            for (int i = 0; i < 26;i++) {
                System.out.print(chars[i]);
                flag = true;
                LockSupport.unpark(digitThread);
                LockSupport.park();
            }
        }

        digitThread.join();

    }


}