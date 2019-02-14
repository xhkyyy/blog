package multi_threads;

import multi_threads.atomic_start_once.RWThread;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        List<RWThread> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(new RWThread());
        }

        for (RWThread rwThread : list) {
            rwThread.start();
        }


        for (RWThread rwThread : list) {
            try {
                rwThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        System.out.println("count: " + RWThread.count);


    }
}
