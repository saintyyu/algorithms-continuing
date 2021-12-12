package multiTread;

import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/11/29 6:08 下午
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws Exception {
        SemaphoreVariable variable = new SemaphoreVariable();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                variable.addVariable(1);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                variable.addVariable(2);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                variable.addVariable(3);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(variable.getVariable());
    }

}

class SemaphoreVariable {

    private int variable;

    Semaphore semaphore = new Semaphore(1);

    public void addVariable(int value) {
        try {
            semaphore.acquire();
            try {
                this.variable += value;
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            System.out.println("error");
        }
    }

    public int getVariable() {
        return variable;
    }

    public void setVariable(int variable) {
        this.variable = variable;
    }
}
