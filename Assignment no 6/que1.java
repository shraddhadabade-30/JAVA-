package com.assignmentno6;

import java.io.*;
import java.util.*;
class StudentRecord {
    String name;
    String subject;
    int marks;

    StudentRecord(String name, String subject, int marks) {
        this.name = name;
        this.subject = subject;
        this.marks = marks;
    }
}

public class que1 {

	public static void main(String[] args) {
		List<StudentRecord> list = new ArrayList<>();

        // Maps for processing
        Map<String, Integer> totalMarks = new HashMap<>();
        Map<String, Integer> subjectCount = new HashMap<>();
        Map<String, StudentRecord> subjectTopper = new HashMap<>();

        // Read file
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/assignmentno6/students.csv"))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String name = data[1];
                String subject = data[2];
                int marks;

                try {
                    marks = Integer.parseInt(data[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid marks: " + data[3]);
                    continue;
                }

                // Create object
                StudentRecord record = new StudentRecord(name, subject, marks);
                list.add(record);

                // Total marks per student
                totalMarks.put(name, totalMarks.getOrDefault(name, 0) + marks);

                // Count subjects
                subjectCount.put(name, subjectCount.getOrDefault(name, 0) + 1);

                // Subject topper
                if (!subjectTopper.containsKey(subject) ||
                        subjectTopper.get(subject).marks < marks) {
                    subjectTopper.put(subject, record);
                }
            }

            // Find overall topper
            String overallTopper = "";
            int maxMarks = 0;

            for (String student : totalMarks.keySet()) {
                int total = totalMarks.get(student);
                if (total > maxMarks) {
                    maxMarks = total;
                    overallTopper = student;
                }
            }

            // Write output
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("report.txt"))) {

                bw.write("===== STUDENT REPORT =====\n");
                bw.write("Total & Average Marks:\n");

                for (String student : totalMarks.keySet()) {
                    int total = totalMarks.get(student);
                    int count = subjectCount.get(student);
                    double avg = (double) total / count;

                    bw.write(student + ": Total = " + total +
                            ", Average = " + String.format("%.2f", avg) + "\n");
                }

                bw.write("\nSubject Toppers:\n");

                for (String subject : subjectTopper.keySet()) {
                    StudentRecord r = subjectTopper.get(subject);
                    bw.write(subject + ": " + r.name + " (" + r.marks + ")\n");
                }

                bw.write("\nOverall Topper: " + overallTopper);

                System.out.println("Report generated successfully!");

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
