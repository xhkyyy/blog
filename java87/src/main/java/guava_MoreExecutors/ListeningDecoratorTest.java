package guava_MoreExecutors;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.*;

public class ListeningDecoratorTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService delegate = Executors.newWorkStealingPool();

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(delegate);

        ListenableFuture<Boolean> listenableFuture = listeningExecutorService.submit(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("exec");
                return true;
            }

        });

        ExecutorService callbackDelegate = Executors.newWorkStealingPool();

        Futures.addCallback(listenableFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean aBoolean) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failure");
            }
        }, callbackDelegate);

        System.out.println(listenableFuture.get());


    }
}
