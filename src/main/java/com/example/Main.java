import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ActorInfoReader {

    public static void main(String[] args) {
        String fileName = "actors.txt";  // Path to your text file
        readAndPrintActors(fileName);
    }

    public static void readAndPrintActors(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            System.out.println("=== Actor Details ===\n");

            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                // Split by commas
                String[] parts = line.split(",");

                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    String age = parts[1].trim();
                    String country = parts[2].trim();
                    String famousMovie = parts[3].trim();

                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                    System.out.println("Country: " + country);
                    System.out.println("Famous Movie: " + famousMovie);
                    System.out.println("-------------------------");
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
