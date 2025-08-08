package ru.jb.hometask3.threadpool;

import java.util.LinkedList;
import java.util.List;

public class CustomThreadPool {
    private final List<Worker> workers = new LinkedList<>();
    private final LinkedList<Runnable> taskQueue = new LinkedList<>();
    private volatile boolean isRunning = true;

    public CustomThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker("Worker-" + i);
            workers.add(worker);
            worker.start();
        }
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void shutdown() {
        isRunning = false;
        synchronized (taskQueue) {
            taskQueue.notifyAll();
        }
    }

    public void awaitTermination() {
        for (Worker worker: workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (isRunning || !taskQueue.isEmpty()) {
                Runnable task;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        if (!isRunning) { return; }
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    task = taskQueue.removeFirst();
                }

                try {
                    task.run();
                } catch (Exception e) {
                    System.err.println(getName() + " error: " + e.getMessage());
                }
            }
        }
    }
}
