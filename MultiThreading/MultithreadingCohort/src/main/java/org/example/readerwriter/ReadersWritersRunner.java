package org.example.readerwriter;

public class ReadersWritersRunner {
    public static void main(String[] args) {
        ReadersAndWritersService service = new ReadersAndWritersService();

        new Thread(() -> service.access(new Worker("1", WorkerType.WRITE, 10000L))).start();
        new Thread(() -> service.access(new Worker("2", WorkerType.READ, 2000L))).start();
        new Thread(() -> service.access(new Worker("3", WorkerType.READ, 2000L))).start();
        new Thread(() -> service.access(new Worker("4", WorkerType.WRITE, 10000L))).start();
        new Thread(() -> service.access(new Worker("5", WorkerType.READ, 4000L))).start();
        new Thread(() -> service.access(new Worker("6", WorkerType.READ, 200L))).start();
        new Thread(() -> service.access(new Worker("7", WorkerType.WRITE, 300L))).start();
        new Thread(() -> service.access(new Worker("8", WorkerType.READ, 7000L))).start();
    }
}
