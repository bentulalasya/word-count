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
    // Please change path to file before running.
    try (BufferedReader br =
        new BufferedReader(
            new FileReader(
                "PathToFile/passage.txt"))) {
      int totalWordCount = 0;
      String curLine;
      Map<String, Integer> wordCountMap = new HashMap<>();
      List<String> sentenceFromFile = new ArrayList<>();

      // Loop through and form a map with word as key and count as value
      while ((curLine = br.readLine()) != null) {
        for (String sentence : curLine.split("[.]\\s")) {
          sentenceFromFile.add(sentence);
          for (String word : sentence.split("\\s+")) {
            totalWordCount++;
            // Remove punctuation
            word = word.replaceAll("\\p{Punct}", "");
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
          }
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
      for (int i = sentenceFromFile.size() - 1; i >= 0; i--) {
        if (sentenceFromFile.get(i).contains(top10Words.get(0))) {
          System.out.println("===========================================================");
          System.out.println("Last sentence on the file that contains the most used word:");
          System.out.println("===========================================================");
          System.out.println(sentenceFromFile.get(i) + ".");
          return;
        }
      }

    } catch (Exception e) {
      System.out.println("Error has occurred");
    }
  }
}
