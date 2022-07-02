package divider;

import java.io.File;
import java.util.List;

public interface Divider {

    List<File> divideFile(File file, int size);

}