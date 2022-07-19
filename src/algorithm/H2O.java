package algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
 *
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 *
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 *
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 *
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 *
 * 换句话说:
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * 书写满足这些限制条件的氢、氧线程同步代码。
 *
 */
public class H2O {

    public H2O() {

    }

    private AtomicInteger hydrogen = new AtomicInteger(0);

    final Lock lock = new ReentrantLock();
    final Condition hydrogenCondition = lock.newCondition();
    final Condition oxygenCondition = lock.newCondition();

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (hydrogen.get() == 2) {
                hydrogenCondition.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hydrogen.incrementAndGet();
            oxygenCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (hydrogen.get() < 2) {
                oxygenCondition.await();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hydrogen.set(0);
            hydrogenCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        Runnable releaseHydrogen = () -> {
            System.out.println("H");
        };

        Runnable releaseOxygen = () -> {
            System.out.println("O");
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        H2O h2O = new H2O();
        String water = "OOHHHHOOHHHHOHHHOH";
        for (int i = 0; i < water.length(); i++) {
            if ('O' == water.charAt(i)) {
                pool.submit(() -> {
                    try {
                        h2O.oxygen(releaseOxygen);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                pool.submit(() -> {
                    try {
                        h2O.hydrogen(releaseHydrogen);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        pool.shutdown();

    }

}
