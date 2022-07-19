package algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;

/**
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当n = 15，输出：1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 */
public class FizzBuzz {

    private int n;

    private volatile int sn = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (sn <= n) {
            if (sn % 3 == 0 && sn % 5 != 0) {
                synchronized (this) {
                    if (sn > n) {
                        break;
                    }
                    if (sn % 3 == 0 && sn % 5 != 0) {
                        printFizz.run();
                        sn++;
                    }
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (sn <= n) {
            if (sn % 5 == 0 && sn % 3 != 0) {
                synchronized (this) {
                    if (sn > n) {
                        break;
                    }
                    if (sn % 5 == 0 && sn % 3 != 0) {
                        printBuzz.run();
                        sn++;
                    }
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (sn <= n) {
            if (sn % 3 == 0 && sn % 5 == 0) {
                synchronized (this) {
                    if (sn > n) {
                        break;
                    }
                    if (sn % 3 == 0 && sn % 5 == 0) {
                        printFizzBuzz.run();
                        sn++;
                    }
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printsn) throws InterruptedException {
        while (sn <= n) {
            if (sn % 3 != 0 && sn % 5 != 0) {
                synchronized (this) {
                    if (sn > n) {
                        break;
                    }
                    if (sn % 3 != 0 && sn % 5 != 0) {
                        printsn.accept(sn);
                        sn++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz(15);

        Runnable printFizz = () -> System.out.println(Thread.currentThread().getName() + "：fizz");
        Runnable printBuzz = () -> System.out.println(Thread.currentThread().getName() + "：buzz");
        Runnable printFizzBuzz = () -> System.out.println(Thread.currentThread().getName() + "：fizzbuzz");
        IntConsumer intConsumer = x -> System.out.println(Thread.currentThread().getName() + "：" + x);

        Runnable runnable1 = () -> {
            try {
                fizzBuzz.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable runnable2 = () -> {
            try {
                fizzBuzz.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable runnable3 = () -> {
            try {
                fizzBuzz.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable runnable4 = () -> {
            try {
                fizzBuzz.number(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        pool.submit(runnable1);
        pool.submit(runnable2);
        pool.submit(runnable3);
        pool.submit(runnable4);
        pool.submit(runnable1);
        pool.submit(runnable2);
        pool.submit(runnable3);
        pool.submit(runnable4);

        pool.shutdown();

    }

}
