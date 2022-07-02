package merger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileMerger implements Merger {

    private static final Logger LOG = LogManager.getLogger(FileMerger.class.getName());

    @Override
    public File merge(List<File> list) {
        if (list.size() == 1) {
            LOG.info("Final sorted file was created: " + list.get(0).getName());
            return list.get(0);
        }
        List<File> mergedFiles = new ArrayList<>();
        if (list.size() % 2 != 0) {
            mergedFiles.add(list.get(list.size() - 1));
        }
        for (int i = 1; i < list.size(); i += 2) {
            mergedFiles.add(submerge(list.get(i - 1), list.get(i), list.size() == 2));
        }
        return merge(mergedFiles);
    }

    private File submerge(File file1, File file2, boolean isTerminalIteration) {
        File sortedFile = new File(isTerminalIteration ? "sorted.txt" : "sorted" + file1.getName().split("\\.")[0] + ".txt");
        try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1))) {
            try (BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2))) {
                try (FileWriter fileWriter = new FileWriter(sortedFile)) {
                    String line1 = bufferedReader1.readLine();
                    String line2 = bufferedReader2.readLine();
                    while (line1 != null || line2 != null) {
                        if (line1 == null) {
                            fileWriter.append(line2).append(System.lineSeparator());
                            line2 = bufferedReader2.readLine();
                        } else if (line2 == null) {
                            fileWriter.append(line1).append(System.lineSeparator());
                            line1 = bufferedReader1.readLine();
                        } else if (line1.compareTo(line2) < 0) {
                            fileWriter.append(line1).append(System.lineSeparator());
                            line1 = bufferedReader1.readLine();
                        } else {
                            fileWriter.append(line2).append(System.lineSeparator());
                            line2 = bufferedReader2.readLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("File " + sortedFile.getName() + " was generated from " + file1.getName() + " and " + file2.getName());
        file1.delete();
        LOG.info("File was deleted: " + file1.getName());
        file2.delete();
        LOG.info("File was deleted: " + file2.getName());
        return sortedFile;
    }
}
