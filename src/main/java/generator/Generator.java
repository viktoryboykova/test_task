package generator;

import java.io.File;

public interface Generator {

    File generateFile(int linesNumber, int lineLength);
}
