package com.assignmentno6;
import java.io.*;
public class que3 {

	public static void main(String[] args) {
		int errorCount = 0;

        // Read and Write using try-with-resources
        try (
            BufferedReader br = new BufferedReader(new FileReader("src/com/assignmentno6/system.log/"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/assignmentno6/error.log/"))
        ) {

            String line;

            while ((line = br.readLine()) != null) {

                // Check if line contains "ERROR"
                if (line.contains("ERROR")) {

                    bw.write(line);
                    bw.newLine(); // move to next line in file

                    errorCount++;
                }
            }

            System.out.println("Total ERROR lines: " + errorCount);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}