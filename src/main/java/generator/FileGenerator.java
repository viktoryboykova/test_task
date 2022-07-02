package generator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileGenerator implements Generator {

    private static final Random random = new Random();
    private static final Logger LOG = LogManager.getLogger(FileGenerator.class.getName());

    @Override
    public File generateFile(int linesNumber, int lineLength) {
        File file = new File("C:\\Users\\gurov\\Desktop\\task\\unsorted.txt");
        String BASE = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb;
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < linesNumber; i++) {
                sb = new StringBuilder();
                while (sb.length() < lineLength) {
                    sb.append(BASE.charAt(random.nextInt(BASE.length())));
                }
                writer.append(sb.toString());
                writer.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("Сгенерирован файл " + file.getName());
        return file;
    }
}
