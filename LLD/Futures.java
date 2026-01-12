import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;

public class Examples {
    public static void main(String[] args) throws Exception {
        //FutureWithNoResult();
        //FutureWithResult();
        FutureWithCallback();
    }

    private static void FutureWithNoResult() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println(MessageFormat.format("I am running in thread {0}", Thread.currentThread().getName()));
        });
        System.out.println(MessageFormat.format("I am running in thread {0}", Thread.currentThread().getName()));
        future.get();
    }

    private static void FutureWithResult() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello World!";
        });

        String result = future.get();
        System.out.println(result);
    }

    private static void FutureWithCallback() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Hello World!";
        });

        CompletableFuture<String> greetingFuture =  future.thenApply(greeting -> {
            return greeting + "Mr Nikhil";
        });

        System.out.println(greetingFuture.get());
    }
}