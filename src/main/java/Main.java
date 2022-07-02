import divider.Divider;
import divider.FileDivider;
import generator.FileGenerator;
import generator.Generator;
import merger.FileMerger;
import merger.Merger;
import sorter.FileSorter;
import sorter.Sorter;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser(args);
        Integer lineNumber = parser.get("n");
        Integer lineLength = parser.get("l");
        Generator fileGenerator = new FileGenerator();
        File unsortedFile = fileGenerator.generateFile(lineNumber, lineLength);
        List<File> dividedFiles;
        if (lineNumber < FileDivider.CHILD_SIZE) {
            dividedFiles = Collections.singletonList(unsortedFile);
        } else {
            Divider fileDivider = new FileDivider();
            dividedFiles = fileDivider.divideFile(unsortedFile);
        }
        Sorter fileSorter = new FileSorter();
        fileSorter.sort(dividedFiles);
        Merger fileMerger = new FileMerger();
        fileMerger.merge(dividedFiles);
    }
}
