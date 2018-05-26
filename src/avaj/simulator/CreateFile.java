package avaj.simulator;

import java.io.*;

public class CreateFile {
    private static BufferedWriter bufferedWriter = null;

    static {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File("simulation.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeFile(String message) {
        try {
            assert false;
            bufferedWriter.write(message);
            assert bufferedWriter != null;
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void closeFile() {
        try {
            assert false;
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
