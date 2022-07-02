import divider.FileDivider;
import generator.FileGenerator;
import merger.FileMerger;
import sorter.FileSorter;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser(args);
        FileGenerator fileGenerator = new FileGenerator();
        File unsortedFile = fileGenerator.generateFile(parser.get("n"), parser.get("l"));
        FileDivider fileDivider = new FileDivider();
        List<File> dividedFiles = fileDivider.divideFile(unsortedFile, parser.get("d"));
        FileSorter fileSorter = new FileSorter();
        fileSorter.sort(dividedFiles);
        FileMerger fileMerger = new FileMerger();
        fileMerger.merge(dividedFiles);

    }
}
