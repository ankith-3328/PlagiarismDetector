package com.example.plagiarism_service.service;

import org.springframework.stereotype.Service;

@Service
public class PlagiarismService {
    public double compareTexts(String text1, String text2) {
        String[] words1 = text1.toLowerCase().split("\\s+");
        String[] words2 = text2.toLowerCase().split("\\s+");

        int commonCount = 0;

        for (String word1 : words1) {
            for (String word2 : words2) {
                if (word1.equals(word2)) {
                    commonCount++;
                    break;
                }
            }
        }

        int totalWords = Math.max(words1.length, words2.length);
        return totalWords == 0 ? 0.0 : ((double) commonCount / totalWords)*100;
    }
}
