/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.ui;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XFuture<T> extends CompletableFuture<T> {

    private volatile Future<T> futureFromExecSubmit = null;
    private volatile Thread threadToInterrupt = null;
    private volatile Runnable onCancelAllRunnable = null;

    public Runnable getOnCancelAllRunnable() {
        return onCancelAllRunnable;
    }

    public void setOnCancelAllRunnable(Runnable onCancelAllRunnable) {
        this.onCancelAllRunnable = onCancelAllRunnable;
    }
    

    public Thread getThreadToInterrupt() {
        return threadToInterrupt;
    }

    public void setThreadToInterrupt(Thread threadToInterrupt) {
        this.threadToInterrupt = threadToInterrupt;
    }

    private static class Hider {

        private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {

            private final AtomicInteger count = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread newThraed = new Thread(r);
                newThraed.setName("XFutureThread_" + count.incrementAndGet());
                return newThraed;
            }
        };
        public static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool(THREAD_FACTORY);
    }

    public static ExecutorService getDefaultThreadPool() {
        return Hider.DEFAULT_EXECUTOR_SERVICE;
    }

    public static XFuture<Void> allOf(CompletableFuture<?>... cfs) {
        XFuture<Void> ret = new XFuture<>();
        CompletableFuture<Void> orig = CompletableFuture.allOf(cfs);
        ret.alsoCancel.addAll(Arrays.asList(cfs));
        return ret.wrap(orig);
    }

    public static XFuture<Object> anyOf(CompletableFuture<?>... cfs) {
        XFuture<Object> ret = new XFuture<>();
        CompletableFuture<Object> orig = CompletableFuture.anyOf(cfs);
        ret.alsoCancel.addAll(Arrays.asList(cfs));
        return ret.wrap(orig);
    }
    
    private final ConcurrentLinkedDeque<CompletableFuture> alsoCancel = new ConcurrentLinkedDeque<>();

    public static <T> XFuture<T> supplyAsync(Callable<T> c, ExecutorService es) {
        XFuture<T> myf = new XFuture<>();
        Future<T> f = es.submit(() -> {
            T result = c.call();
            myf.complete(result);
            return result;
        });
        myf.futureFromExecSubmit = f;
        return myf;
    }

    @SuppressWarnings("unchecked")
    public static XFuture<Void> runAsync(Runnable r, ExecutorService es) {
        XFuture<Void> myf = new XFuture<>();
        Future<?> f = es.submit(() -> {
            r.run();
            myf.complete(null);
        });
        myf.futureFromExecSubmit = ((Future<Void>) f);
        return myf;
    }

    public static <T> XFuture<T> completedFuture(T object) {
        XFuture<T> ret = new XFuture<>();
        ret.complete(object);
        return ret;
    }

    public static XFuture<Void> runAsync(Runnable r) {
        return runAsync(r, getDefaultThreadPool());
    }

    public static <T> XFuture<T> supplyAsync(Callable<T> c) {
        return supplyAsync(c, getDefaultThreadPool());
    }

    @Override
    public <U> XFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn) {

        XFuture<U> myF = new XFuture<>();
        CompletableFuture<U> f = super.thenApply(fn)
                .thenCompose((CompletionStage<U> stage) -> {
                    if (stage instanceof CompletableFuture) {
                        myF.alsoCancel.add((CompletableFuture) stage);
                    } else {
                        myF.alsoCancel.add(stage.toCompletableFuture());
                    }
                    return stage;
                });
        myF.alsoCancel.add(f);
        myF.alsoCancel.add(f.thenAccept(x -> myF.complete(x)));
        myF.alsoCancel.add(this);
        return myF;
    }

    @Override
    public <U> XFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) {

        XFuture<U> myF = new XFuture<>();
        CompletableFuture<U> f = super.thenApplyAsync(fn, executor)
                .thenCompose((CompletionStage<U> stage) -> {
                    if (stage instanceof CompletableFuture) {
                        myF.alsoCancel.add((CompletableFuture) stage);
                    } else {
                        myF.alsoCancel.add(stage.toCompletableFuture());
                    }
                    return stage;
                });
        myF.alsoCancel.add(f);
        myF.alsoCancel.add(f.thenAccept(x -> myF.complete(x)));
        myF.alsoCancel.add(this);
        return myF;
    }

    @Override
    public <U> XFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) {

        XFuture<U> myF = new XFuture<>();
        CompletableFuture<U> f = super.thenApplyAsync(fn, getDefaultThreadPool())
                .thenCompose((CompletionStage<U> stage) -> {
                    if (stage instanceof CompletableFuture) {
                        myF.alsoCancel.add((CompletableFuture) stage);
                    } else {
                        myF.alsoCancel.add(stage.toCompletableFuture());
                    }
                    return stage;
                });
        myF.alsoCancel.add(f);
        myF.alsoCancel.add(f.thenAccept(x -> myF.complete(x)));
        myF.alsoCancel.add(this);
        return myF;
    }

    public void cancelAll(boolean mayInterrupt) {
        this.cancel(mayInterrupt);
        if(null != onCancelAllRunnable) {
            onCancelAllRunnable.run();
        }
        if (null != futureFromExecSubmit) {
            futureFromExecSubmit.cancel(mayInterrupt);
        }
        if (mayInterrupt && null != threadToInterrupt && Thread.currentThread() == threadToInterrupt) {
            threadToInterrupt.interrupt();
        }
        

        for (CompletableFuture f : alsoCancel) {
            if (null != f && null != this) {
                f.cancel(true);
                if (f instanceof XFuture) {
                    ((XFuture) f).cancelAll(mayInterrupt);
                }
            }
        }

    }

    public <T> XFuture<T> wrap(CompletableFuture<T> future) {
        if (future instanceof XFuture) {
            if (this != future) {
                ((XFuture<T>) future).alsoCancel.add(this);
            }
            return (XFuture<T>) future;
        }
        XFuture<T> newFuture = new XFuture<>();
        future.thenAccept(newFuture::complete);
        newFuture.alsoCancel.add(this);
        newFuture.alsoCancel.add(future);
        return newFuture;
    }

    @Override
    public XFuture<T> exceptionally(Function<Throwable, ? extends T> fn) {
        return wrap(super.exceptionally(fn));
    }

    @Override
    public <U> XFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
        return wrap(super.handleAsync(fn, executor));
    }

    @Override
    public <U> XFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(super.handleAsync(fn));
    }

    @Override
    public <U> XFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) {
        return wrap(super.handle(fn));
    }

    @Override
    public XFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor) {
        return wrap(super.whenCompleteAsync(action, executor));
    }

    @Override
    public XFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action) {
        return wrap(super.whenCompleteAsync(action));
    }

    @Override
    public XFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) {
        return wrap(super.whenComplete(action));
    }

    @Override
    public XFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor) {
        return wrap(super.runAfterEitherAsync(other, action, executor));
    }

    @Override
    public XFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action) {
        return wrap(super.runAfterEitherAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action) {
        return wrap(super.runAfterEither(other, action));
    }

    @Override
    public XFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor) {
        return wrap(super.acceptEitherAsync(other, action, executor));
    }

    @Override
    public XFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action) {
        return wrap(super.acceptEitherAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) {
        return wrap(super.acceptEither(other, action));
    }

    @Override
    public <U> XFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor) {
        return wrap(super.applyToEitherAsync(other, fn, executor));
    }

    @Override
    public <U> XFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn) {
        return wrap(super.applyToEitherAsync(other, fn, getDefaultThreadPool()));
    }

    @Override
    public <U> XFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn) {
        return wrap(super.applyToEither(other, fn));
    }

    @Override
    public XFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor) {
        return wrap(super.runAfterBothAsync(other, action, executor));
    }

    @Override
    public XFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action) {
        return wrap(super.runAfterBothAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action) {
        return wrap(super.runAfterBoth(other, action));
    }

    @Override
    public <U> XFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor) {
        return wrap(super.thenAcceptBothAsync(other, action, executor));
    }

    @Override
    public <U> XFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
        return wrap(super.thenAcceptBothAsync(other, action, getDefaultThreadPool()));
    }

    @Override
    public <U> XFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
        return wrap(super.thenAcceptBoth(other, action));
    }

    @Override
    public <U, V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor) {
        return wrap(super.thenCombineAsync(other, fn, executor));
    }

    @Override
    public <U, V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        return wrap(super.thenCombineAsync(other, fn, getDefaultThreadPool()));
    }

    @Override
    public <U, V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        return wrap(super.thenCombine(other, fn));
    }

    @Override
    public XFuture<Void> thenRunAsync(Runnable action, Executor executor) {
        return wrap(super.thenRunAsync(action, executor));
    }

    @Override
    public XFuture<Void> thenRunAsync(Runnable action) {
        return wrap(super.thenRunAsync(action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> thenRun(Runnable action) {
        return wrap(super.thenRun(action));
    }

    @Override
    public XFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor) {
        return wrap(super.thenAcceptAsync(action, executor));
    }

    @Override
    public XFuture<Void> thenAcceptAsync(Consumer<? super T> action) {
        return wrap(super.thenAcceptAsync(action, getDefaultThreadPool()));
    }

    @Override
    public XFuture<Void> thenAccept(Consumer<? super T> action) {
        return wrap(super.thenAccept(action));
    }

    @Override
    public <U> XFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor) {
        return wrap(super.thenApplyAsync(fn, executor));
    }

    @Override
    public <U> XFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn) {
        return wrap(super.thenApplyAsync(fn, getDefaultThreadPool()));
    }

    @Override
    public <U> XFuture<U> thenApply(Function<? super T, ? extends U> fn) {
        return wrap(super.thenApply(fn));
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        TestClass.main(args);
    }
}

/**
 *
 * @author will
 */
class TestClass {

    static XFuture<Void> startT1() {
        XFuture<Void> f
                = XFuture.runAsync(() -> {
                    try {
                        int count = 0;
                        System.out.println("T1 started");
                        Thread.sleep(2000);
                        System.out.println("T1 finished");
                        System.out.println(new Date());
                    } catch (InterruptedException interruptedException) {
                    }
                });
        return f;
    }

    static XFuture<Void> startT2() {
        XFuture<Void> f = new XFuture<>();
        new Thread(() -> {
            try {
                int count = 0;
                System.out.println("T2 started");

                while (!f.isCancelled() && ++count < 50) {
//                    boolean isCancelled = f.isCancelled();
//                    System.out.println("isCancelled = " + isCancelled);
                    Thread.sleep(100);
                }
                System.out.println("T2 count=" + count);
                System.out.println(new Date());
            } catch (InterruptedException interruptedException) {
            } finally {
                f.complete(null);
            }
        }).start();
        return f;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AtomicReference<Thread> at = new AtomicReference<>();
        ExecutorService es = Executors.newCachedThreadPool();
        XFuture<Void> f = startT1().thenCompose(x -> startT2()).thenCompose(x -> startT1());
//        CompletableFuture f = CompletableFuture.runAsync(() -> {
//            System.out.println("Step 1 started");
//            try {
//                Thread t = Thread.currentThread();
//                at.set(t);
//                System.out.println("t = " + t);
//                boolean isInterrupted = t.isInterrupted();
//                System.out.println("isInterrupted = " + isInterrupted);
//                Thread.sleep(5000);
//                System.out.println(new Date());
//                System.out.println("Step 1 ended");
//            } catch (InterruptedException ex) {
//                Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }, es);//.thenRunAsync(() -> {
//            System.out.println("Step 2 started");
//            try {
//                Thread.sleep(5000);
//                System.out.println("Step 2 ended");
//            } catch (InterruptedException ex) {
//                Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(XFuture.class.getName()).log(Level.SEVERE, null, ex);
        }
        f.cancelAll(true);
//        f.get();
        System.out.println("f.isCancelled() = " + f.isCancelled());
        System.out.println(new Date());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (null != at.get()) {
//            at.get().interrupt();
//            System.out.println("Interrupting = " + f.isCancelled());
//            System.out.println(new Date());
//        }

    }

}