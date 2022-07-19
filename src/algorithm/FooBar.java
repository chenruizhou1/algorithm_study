package algorithm;

/**
 * 两个不同的线程将会共用一个 FooBar实例：
 * 线程 A 将会调用 foo() 方法，输出"foo"
 * 线程 B 将会调用 bar() 方法，输出"bar"
 * 请设计程序，以确保 "foobar" 被输出 n 次。
 *
 * 示例：
 * 输入：n = 2
 * 输出："foobarfoobar"
 * 解释："foobar" 将被输出两次。
 */
public class FooBar {

    private final int n;

    private volatile boolean flag = false;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n;) {
            if (flag) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                i++;
                flag = false;
            } else {
                Thread.yield();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n;) {
            if (!flag) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                i++;
                flag = true;
            } else {
                Thread.yield();
            }
        }
    }

}
