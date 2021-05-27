import org.apache.commons.lang3.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;

public class MyCallablePDF implements Callable<Boolean> {


    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    void createPDF(String name) {

        name += ".pdf";

        try(PDDocument document = new PDDocument()) {
            PDPage my_page = new PDPage();
            document.addPage(my_page);
            PDPageContentStream contentStream = new PDPageContentStream(document, my_page);

            contentStream.beginText();

            contentStream.newLineAtOffset(50, 700);
            contentStream.setLeading(14.5f);

            contentStream.setFont(PDType1Font.COURIER, 30 );
            contentStream.showText("          Certificate");
            contentStream.newLine();

            contentStream.setFont(PDType1Font.COURIER, 12 );
            String text = "This certificate is being given to " + RandomStringUtils.randomAlphabetic(5,10);

            int cnt = getRandomNumber(10,20);
            for (int i = 0; i < 20; i++) {
                contentStream.showText(text);
                contentStream.newLine();
            }
            contentStream.endText();

            contentStream.close();

            document.save("pdfs/"+name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Boolean call() {
        createPDF(UUID.randomUUID().toString());
        return true;
    }
}
