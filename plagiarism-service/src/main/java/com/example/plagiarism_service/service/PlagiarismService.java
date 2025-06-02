package com.example.plagiarism_service.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlagiarismService {
    public double calculate(String text1, String text2) {
        text1 = preprocess(text1);
        text2 = preprocess(text2);

        Map<String, Integer> freq1 = buildFrequencyMap(text1);
        Map<String, Integer> freq2 = buildFrequencyMap(text2);

        Set<String> allWords = new HashSet<>();
        allWords.addAll(freq1.keySet());
        allWords.addAll(freq2.keySet());

        List<Integer> vector1 = new ArrayList<>();
        List<Integer> vector2 = new ArrayList<>();

        for (String word : allWords) {
            vector1.add(freq1.getOrDefault(word, 0));
            vector2.add(freq2.getOrDefault(word, 0));
        }

        return cosineSimilarity(vector1, vector2) * 100; // return percentage
    }

    private static String preprocess(String text) {
        return text.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private static Map<String, Integer> buildFrequencyMap(String text) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : text.split(" ")) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        return freqMap;
    }

    private static double cosineSimilarity(List<Integer> vec1, List<Integer> vec2) {
        int dotProduct = 0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vec1.size(); i++) {
            dotProduct += vec1.get(i) * vec2.get(i);
            normA += Math.pow(vec1.get(i), 2);
            normB += Math.pow(vec2.get(i), 2);
        }

        if (normA == 0 || normB == 0) return 0.0;

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
