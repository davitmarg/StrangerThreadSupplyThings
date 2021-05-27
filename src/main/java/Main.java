import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {

        String name1 = "rnd1/file1.csv";
        String name2 = "file2.csv";
        String name3 = "result.csv";

        FileHandler result = new FileHandler(name3);

        List<Person> list = new ArrayList<>();


        ExecutorService service = Executors.newSingleThreadExecutor();

        CompletableFuture.supplyAsync(() -> {
            System.out.println("Plor");
            FileHandler fileHandler = new FileHandler(name1);
            System.out.println("Shlor");
            System.out.println(Thread.currentThread().getName());
            List<Person> personList = fileHandler.readAll();
            System.out.println("Bmbul");
            return null;
        });




        result.writeAll(list);

    }
}
