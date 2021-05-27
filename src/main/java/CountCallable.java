import java.io.*;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CountCallable implements Supplier<Integer> {

    private String name;

    public CountCallable(String name) {
        this.name = name;
    }

    private Integer countLines() {
        String name = this.name;
        int result = 0;
        File file = new File("pdfs/" + name);
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {
             result = reader.lines().collect(Collectors.toList()).size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer get() {
        return countLines();
    }
}
