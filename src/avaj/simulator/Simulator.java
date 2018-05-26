package avaj.simulator;

import avaj.simulator.vehicles.*;

import java.io.*;
import java.util.regex.*;

public class Simulator {
    private static WeatherTower weatherTower;
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] argv) throws InterruptedException {
        int cntSimulation = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader(argv[0]));
            Pattern nbrSimulation = Pattern.compile("^\\d+\\s*$");
            Pattern air = Pattern.compile("^(Baloon [B]{1}\\d+ \\d+ \\d+ \\d+$|" +
                    "^JetPlane [J]{1}\\d+ \\d+ \\d+ \\d+$|" +
                    "^Helicopter [H]{1}\\d+ \\d+ \\d+ \\d+$)");
            String line = file.readLine();
            if (line == null) {
                System.out.println("Error: file is empty");
                System.exit(1);
            }
            Matcher tmp = nbrSimulation.matcher(line);
            if (!tmp.matches()) {
                System.out.println("Invalid simulations count " + RED + "\"" + line + "\"" + RESET);
                System.exit(1);
            } else {
                weatherTower = new WeatherTower();
                cntSimulation = Integer.parseInt(line.split(" ")[0]);
            }
            while ((line = file.readLine()) != null) {
                tmp = air.matcher(line);
                if (!tmp.matches()) {
                    System.out.println("Invalid data in " + RED + "\"" + line + "\"" + RESET);
                    System.exit(1);
                } else {
                    String[] param = line.split(" ");
                    Flyable flyable = AircraftFactory.newAircraft(param[0], param[1], Integer.parseInt(param[2]), Integer.parseInt(param[3]), Integer.parseInt(param[4]));
                    assert flyable != null;
                    flyable.registerTower(weatherTower);
                }
            }
            file.close();
            for (int cnt = 0; cnt <= cntSimulation; cnt++) {
                weatherTower.changeWeather();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Unable to find " + RED + "\"" + argv[0] + "\"" + RESET + " file");
        } catch (IOException e) {
            System.out.println("Error reading " + RED + "\"" + argv[0] + "\"" + RESET + " file");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Specify simulation file like " + RED + "scenario.txt" + RESET);
        } finally {
            CreateFile.closeFile();
        }
    }
}
