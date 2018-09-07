package guava_vs_caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;


public class TestMain {

    public void testCaffeine() {
        Caffeine x = null;

        LoadingCache<String, String> graphs = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(key -> createExpensiveGraph(key));

        System.out.println(graphs.get("abc"));
    }

    private String createExpensiveGraph(String key) {
        return key + "__" + String.valueOf(Math.random());
    }

    public static void main(String[] args) {

        TestMain tm = new TestMain();
        tm.testCaffeine();

    }
}
