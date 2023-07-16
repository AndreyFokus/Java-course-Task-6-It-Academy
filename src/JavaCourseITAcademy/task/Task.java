package JavaCourseITAcademy.task;

import JavaCourseITAcademy.generalPartsList.GeneralPartsList;
import JavaCourseITAcademy.partOfRobot.PartOfRobot;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task {
    private static final Random RANDOM = new Random();
    private static final AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) {
        GeneralPartsList generalList = new GeneralPartsList();

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(new Producer(generalList));
        service.execute(new Consumer(generalList));
        service.execute(new Consumer(generalList));
        service.shutdown();

    }

    static class Producer implements Runnable {
        GeneralPartsList generalList;

        public Producer(GeneralPartsList partsList) {
            this.generalList = partsList;
        }

        @Override
        public void run() {
            while (flag.get()) {
                generalList.addPart(randomParts(RANDOM.nextInt(6)));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        GeneralPartsList generalList;
        LinkedList<PartOfRobot> localList;
        int counter = 0;

        public Consumer(GeneralPartsList partsList) {
            this.generalList = partsList;
            localList = new LinkedList<>();
        }

        @Override
        public void run() {
            while (flag.get()) {

                if (counter == 20) {
                    if (Thread.currentThread().getName().equals("pool-1-thread-3")) {
                        System.out.println("Country 2 is WINNER!!!!");
                    } else {
                        System.out.println("Country 1 is WINNER!!!!");
                    }
                    flag.lazySet(false);
                }

                for (PartOfRobot p : PartOfRobot.values()) {
                    if (generalList.getPart(p) != null && !localList.contains(p)) {
                        localList.add(p);
                        System.out.println(Thread.currentThread().getName() + " забрал " + p);
                    }
                }
                if (localList.size() == 6) {
                    counter++;
                    System.out.println("-----------------------------" + Thread.currentThread().getName() + " создал робота " + counter);
                    localList.clear();
                }
            }
        }
    }




    public static PartOfRobot randomParts(int number) {
        for (PartOfRobot i : PartOfRobot.values()) {
            if (i.getValue() == number) {
                return i;
            }
        }
        return null;
    }
}
