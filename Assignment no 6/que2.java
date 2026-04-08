package com.assignmentno6;
import java.io.*;
import java.util.*;

public class que2 {

	public static void main(String[] args) {
		 Map<String, Integer> activityCount = new HashMap<>();

	        // Read file
	        try (BufferedReader br = new BufferedReader(new FileReader("src/com/assignmentno6/activity.txt/"))) {

	            String line;

	            while ((line = br.readLine()) != null) {

	                // Split by space
	                String[] data = line.split(" ");

	                String user = data[0];     // User
	                String activity = data[1]; // Activity (not used further here)

	                // Count activity
	                activityCount.put(user, activityCount.getOrDefault(user, 0) + 1);
	            }

	            // Find user with maximum activity
	            String maxUser = "";
	            int maxCount = 0;

	            for (String user : activityCount.keySet()) {
	                int count = activityCount.get(user);
	                if (count > maxCount) {
	                    maxCount = count;
	                    maxUser = user;
	                }
	            }

	            // Write result to file
	            try (BufferedWriter bw = new BufferedWriter(new FileWriter("activity_summary.txt"))) {

	                bw.write("===== USER ACTIVITY SUMMARY =====\n");

	                for (String user : activityCount.keySet()) {
	                    bw.write(user + " : " + activityCount.get(user) + "\n");
	                }

	                bw.write("\nMost Active User: " + maxUser + 
	                         " (Activities = " + maxCount + ")");

	                System.out.println("File written successfully!");

	            } catch (IOException e) {
	                System.out.println("Error writing file: " + e.getMessage());
	            }

	        } catch (FileNotFoundException e) {
	            System.out.println("File not found!");
	        } catch (IOException e) {
	            System.out.println("Error reading file: " + e.getMessage());
	        }
	    }
	
	}


