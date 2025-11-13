import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

final class ReverseString {

    /** Private constructor to prevent instantiation. */
    private ReverseString() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Returns a sorted version of an array.
     * @return array
     * @param str Array input
     */
    public static String reverse(final String str) {
        if (str.length() == 0) {
            // Return empty
            return "";
        } else {
            // Return reversed string with first char moved to the end
            return reverse(str.substring(1)) + str.charAt(0);
        }
    }

    /**
     * Returns a sorted version of an array.
     * @return array
     * @param lines Array input
     */
    public static String[] reverseAll(final String[] lines) {
        String[] reversed = new String[lines.length];
        // Add reversed string to each element COUNTER of array
        int counter = 0;
        for (String line : lines) {
            // Add reversed line to array, and increment counter
            reversed[counter] = reverse(line);
            counter += 1;
        }
        return reversed;
    }

    /**
     * Writes a sorted version of an array to a file.
     * @param lines Array input
     * @param outputFile File name to write to
     */
    public static void writeToFile(final String[] lines,
            final String outputFile) {
        // Write to file, or raise error
        try {
            // Create writer, write each line
            FileWriter writer = new FileWriter(outputFile);
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
            // Close writer and inform user
            writer.close();
            System.out.println("Reversed lines written to " + outputFile);
        } catch (IOException e) {
            // File write error
            System.out.println("Error writing to file: " + outputFile);
        }
    }

    /**
     * Reads a file and converts its contents into an array.
     * @param inputFile Input file
     * @return array
     */
    public static String[] getLines(final String inputFile) {
        try {
            // Create file, scanner and empty array list
            File file = new File(inputFile);
            Scanner fileScanner = new Scanner(file);

            ArrayList<String> lines = new ArrayList<>();

            // Iterate through every line
            while (fileScanner.hasNextLine()) {
                // Read each line
                String line = fileScanner.nextLine();
                lines.add(line);
            }

            // Setup numbers array
            String[] linesArray = new String[lines.size()];
            for (int i = 0; i < linesArray.length; i++) {
                linesArray[i] = lines.get(i);
            }
            fileScanner.close();
            return linesArray;
        } catch (FileNotFoundException error) {
            // Error msg for file not found
            System.out.println("\nError: The file " + inputFile
                    + " was not found."
                    + " Please ensure it exists in the same directory.");
            return new String[0];
        }
    }

    public static void main(final String[] args) {
        // File names
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Get array, sort and write to file
        String[] array = getLines(inputFile);
        String[] sorted = reverseAll(array);
        writeToFile(sorted, outputFile);
    }
}
