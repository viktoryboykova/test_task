package sorter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class FileSorter implements Sorter {

    private static final Logger LOG = LogManager.getLogger(FileSorter.class.getName());

    @Override
    public void sort(List<File> files) {
        for (File unsortedFile : files) {
            List<String> list = null;
            try {
                list = Files.lines(unsortedFile.toPath()).sorted().collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (FileWriter fileWriter = new FileWriter(unsortedFile)) {
                list.forEach(l -> {
                    try {
                        fileWriter.append(l).append(System.lineSeparator());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            LOG.info("File was sorted: " + unsortedFile.getName());
        }
    }
}
