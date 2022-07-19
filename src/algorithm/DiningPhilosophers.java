package algorithm;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    public DiningPhilosophers() {
    }

    ReentrantLock[] lockList = {
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()
    };

    static ThreadLocal<Boolean> flag = new ThreadLocal<>();

    private static final Semaphore semaphore = new Semaphore(4);

    // call the run() method of any runnable to execute its code
    public void wantsToEat2(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        flag.set(false);
        // 左边的叉子的编号
        int leftFork = (philosopher + 1) % 5;
        while (!flag.get()) {
            lockList[leftFork].lock();
            try {
                pickLeftFork.run();
                if (lockList[philosopher].tryLock()) {
                    try {
                        pickRightFork.run();
                        eat.run();
                        flag.set(true);
                    } finally {
                        putLeftFork.run();
                        lockList[philosopher].unlock();
                    }
                }
            } finally {
                putRightFork.run();
                lockList[leftFork].unlock();
            }
        }
        flag.remove();
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        // 左边的叉子的编号
        semaphore.acquire();
        int leftFork = (philosopher + 1) % 5;
        lockList[leftFork].lock();
        lockList[philosopher].lock();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        lockList[leftFork].unlock();
        lockList[philosopher].unlock();
        semaphore.release();
    }

    public static void main(String[] args) {

        final int eatCount = 5;

        Runnable pickLeftFork = () -> {
            System.out.println(Thread.currentThread().getName() + "拿起了左边的叉子");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable pickRightFork = () -> {
            System.out.println(Thread.currentThread().getName() + "拿起了右边的叉子");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable eat = () -> {
            System.out.println(Thread.currentThread().getName() + "正在吃面");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable putLeftFork = () -> {
            System.out.println(Thread.currentThread().getName() + "放下了左边的叉子");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable putRightFork = () -> {
            System.out.println(Thread.currentThread().getName() + "放下了右边的叉子");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();

        Thread t0 = new Thread(() -> {
            try {
                for (int i = 0; i < eatCount; i++) {
                    diningPhilosophers.wantsToEat(0,
                            pickLeftFork,
                            pickRightFork,
                            eat,
                            putLeftFork,
                            putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "哲学家0");
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < eatCount; i++) {
                    diningPhilosophers.wantsToEat(1,
                            pickLeftFork,
                            pickRightFork,
                            eat,
                            putLeftFork,
                            putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "哲学家1");
        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < eatCount; i++) {
                    diningPhilosophers.wantsToEat(2,
                            pickLeftFork,
                            pickRightFork,
                            eat,
                            putLeftFork,
                            putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "哲学家2");
        Thread t3 = new Thread(() -> {
            try {
                for (int i = 0; i < eatCount; i++) {
                    diningPhilosophers.wantsToEat(3,
                            pickLeftFork,
                            pickRightFork,
                            eat,
                            putLeftFork,
                            putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "哲学家3");
        Thread t4 = new Thread(() -> {
            try {
                for (int i = 0; i < eatCount; i++) {
                    diningPhilosophers.wantsToEat(4,
                            pickLeftFork,
                            pickRightFork,
                            eat,
                            putLeftFork,
                            putRightFork);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "哲学家4");

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}
