package divider;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDivider implements Divider {

    private static final Logger LOG = LogManager.getLogger(FileDivider.class.getName());

    @Override
    public List<File> divideFile(File file, int size) {
        List<File> files = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = null;
            String nextLine = "";
            int number = 1;
            while (nextLine != null) {
                File newFile = new File("C:\\Users\\gurov\\Desktop\\task\\" + number + ".txt");
                number += 1;
                FileWriter fileWriter = new FileWriter(newFile);
                stringBuilder = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    nextLine = bufferedReader.readLine();
                    if (nextLine != null) {
                        stringBuilder.append(nextLine).append(System.lineSeparator());
                    }
                }
                fileWriter.append(stringBuilder.toString());
                fileWriter.close();
                files.add(newFile);
                LOG.info("Создан файл " + newFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}
