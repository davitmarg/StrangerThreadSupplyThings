import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public void writeAll(List<Person> list) {
        try (FileWriter out = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.
                     withHeader("ID","First Name", "Last Name", "Email"))) {
            list.forEach(each-> {
                try {
                    printer.printRecord(each.toStringList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Person csvToPerson(CSVRecord record)
    {
        String id = record.get(0);
        String firstName = record.get(1);
        String lastName = record.get(2);
        String email = record.get(3);
        return new Person(id,firstName,lastName,email);
    }

    public List<Person> readAll(){
        List<Person> list = new ArrayList<Person>();
        try(FileReader fileReader = new FileReader(fileName);
            CSVParser parser = new CSVParser(fileReader,CSVFormat.DEFAULT)) {
            List<CSVRecord> records = parser.getRecords();
            for (int i = 1; i < records.size(); i++) {
                Person person = csvToPerson(records.get(i));
                list.add(person);
            }
            return list;
        } catch (FileNotFoundException e) {
            return list;
        } catch (IOException e) {
            return list;
        }
    }


}
