package divider;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDivider implements Divider {
    public final static int CHILD_SIZE = 1000;
    private static final Logger LOG = LogManager.getLogger(FileDivider.class.getName());

    @Override
    public List<File> divideFile(File file) {
        List<File> files = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder;
            String nextLine = "";
            int number = 1;
            while (nextLine != null) {
                File newFile = new File(number + ".txt");
                number += 1;
                FileWriter fileWriter = new FileWriter(newFile);
                stringBuilder = new StringBuilder();
                for (int i = 0; i < CHILD_SIZE; i++) {
                    nextLine = bufferedReader.readLine();
                    if (nextLine != null) {
                        stringBuilder.append(nextLine);
                        if (i < CHILD_SIZE - 1) {
                            stringBuilder.append(System.lineSeparator());
                        }
                    }
                }
                fileWriter.append(stringBuilder.toString());
                fileWriter.close();
                files.add(newFile);
                LOG.info("File was created: " + newFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}
