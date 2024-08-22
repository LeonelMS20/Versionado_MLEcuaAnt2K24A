package MLDataAccess;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MLDAO {

    private static final String FILE_PATH = "MLData/MLHormiguero.csv";

    public void mlSave(MLDTO hormiga) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(hormiga.toCSV());
            writer.newLine();
        }
    }
}
