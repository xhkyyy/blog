package best_practice.String;

import org.junit.Test;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Len on 02/02/2017.
 */
public class StringBuilderHolderTest {

	@Test
	public void test() throws InterruptedException {

		final CountDownLatch countDownLatch = new CountDownLatch(10);
		final CyclicBarrier barrier = new CyclicBarrier(10);

		Runnable runnable = new Runnable() {

			StringBuilderHolder holder = new StringBuilderHolder(512);

			@Override
			public void run() {

				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}

				StringBuilder builder = StringBuilderHolder.getGlobal();
				builder.append(Thread.currentThread().getName()).append("-1");
				System.out.println(builder.toString());


				builder = StringBuilderHolder.getGlobal();
				builder.append(Thread.currentThread().getName()).append("-2");
				System.out.println(builder.toString());

				StringBuilder builder2 = holder.get();
				builder2.append(Thread.currentThread().getName()).append("-11");
				System.out.println(builder2.toString());

				builder2 = holder.get();
				builder2.append(Thread.currentThread().getName()).append("-22");
				System.out.println(builder2.toString());

				countDownLatch.countDown();

			}
		};

		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(runnable);
			thread.start();
		}

		countDownLatch.await();
		System.out.println("Over!");

	}

}
