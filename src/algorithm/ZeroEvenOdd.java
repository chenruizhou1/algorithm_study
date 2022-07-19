package algorithm;

import java.util.function.IntConsumer;

/**
 * zero()输出0，even()输出偶数，odd()输出奇数
 * 输入：n = 5
 * 输出："0102030405"
 * 解释：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102030405"。
 */
public class ZeroEvenOdd {

    private int n;

    private final Object object = new Object();

    private volatile boolean flag1 = false;
    private volatile boolean flag2 = false;
    private volatile boolean flag3 = true;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (object) {
                while (flag1) {
                    object.wait();
                }
                printNumber.accept(0);
                flag1 = true;
                object.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i = i + 2) {
            synchronized (object) {
                while (flag2 || !flag1) {
                    object.wait();
                }
                printNumber.accept(i);
                flag2 = true;
                flag1 = false;
                flag3 = false;
                object.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i = i + 2) {
            synchronized (object) {
                while (flag3 || !flag1) {
                    object.wait();
                }
                printNumber.accept(i);
                flag3 = true;
                flag1 = false;
                flag2 = false;
                object.notifyAll();
            }
        }
    }

}
