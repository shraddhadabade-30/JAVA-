package com.assignmentno6;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class WordCounterThread implements Runnable {

    private List<String> lines;
    private AtomicInteger totalCount;
    private int threadId;

    WordCounterThread(List<String> lines, AtomicInteger totalCount, int threadId) {
        this.lines = lines;
        this.totalCount = totalCount;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        int localCount = 0;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] words = line.trim().split("\\s+");
            localCount += words.length;
        }

        System.out.println("Thread " + threadId + " count: " + localCount);

        // Add to shared total safely
        totalCount.addAndGet(localCount);
    }
}

public class que4 {

	 List<String> allLines = new ArrayList<>();
	 AtomicInteger totalCount = new AtomicInteger(0);

     // Step 1: Read file
     try (BufferedReader br = new BufferedReader(new FileReader("src/com/assignmentno6/input.txt"))) {

         String line;
         while ((line = br.readLine()) != null) {
             allLines.add(line);
         }

     } catch (IOException e) {
         System.out.println("File error: " + e.getMessage());
         return;
     }

     // Step 2: Divide work
     int numThreads = 3; // you can change
     int chunkSize = (int) Math.ceil(allLines.size() / (double) numThreads);

     List<Thread> threads = new ArrayList<>();

     // Step 3: Create threads
     for (int i = 0; i < numThreads; i++) {

         int start = i * chunkSize;
         int end = Math.min(start + chunkSize, allLines.size());

         if (start >= end) break;

         List<String> subList = allLines.subList(start, end);

         Thread t = new Thread(new WordCounterThread(subList, totalCount, i + 1));
         threads.add(t);
         t.start();
     }

     // Step 4: Wait for all threads
     for (Thread t : threads) {
         try {
             t.join();
         } catch (InterruptedException e) {
             System.out.println("Thread interrupted");
         }
     }

     // Final result
     System.out.println("Total word count: " + totalCount.get());
 }
}
