import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Solution {
  /*
  This method is used to
  1. Give a total word count
  2. Identify the top 10 words used and display them in sorted order
  3. Find and display the last sentence on the file that contains the most used word
   */
  void wordCount() {
    //Change the file path
    try (BufferedReader br =
        new BufferedReader(
            new FileReader(
                "pathTofile/passage.txt"))) {
      int totalWordCount = 0;
      String curLine;
      Map<String, Integer> wordCountMap = new HashMap<>();
      List<String> linesFromFile = new ArrayList<>();

      // Loop through and form a map with word as key and count as value
      while ((curLine = br.readLine()) != null) {
        linesFromFile.add(curLine);
        String[] words = curLine.split("\\s+");
        // Count total words in the file
        totalWordCount += words.length;

        for (String word : words) {
          // Remove punctuation
          word = word.replaceAll("\\p{Punct}", "");
          wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }
      }

      System.out.println("=======================\nTotal word count: " + totalWordCount);

      // Sorted map to store top 10 words
      Map<Integer, String> treeMap = new TreeMap<>(Collections.reverseOrder());
      for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
        treeMap.put(entry.getValue(), entry.getKey());
      }

      List<String> top10Words = treeMap.values().stream().limit(10).collect(Collectors.toList());

      System.out.println("=======================\nTop 10 words:\n=======================");
      top10Words.forEach(System.out::println);

      // Identify the last sentence on the file that contains the most used word
      for (String line : linesFromFile) {
        if (line.contains(top10Words.get(0))) {
          System.out.println("===========================================================");
          System.out.println("Last sentence on the file that contains the most used word:");
          System.out.println("===========================================================");
          System.out.println(line);
          return;
        }
      }

    } catch (Exception e) {
      System.out.println("Error has occurred");
    }
  }
}
