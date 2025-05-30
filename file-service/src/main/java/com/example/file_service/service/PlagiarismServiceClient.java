package com.example.file_service.service;

import com.example.file_service.dto.PlagiarismRequestDTO;
import com.example.file_service.dto.PlagiarismResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PlagiarismServiceClient {

    @Autowired
    private WebClient webClient;

    public PlagiarismResponseDTO getSimilarity(String content1, String content2) {
        PlagiarismRequestDTO request = new PlagiarismRequestDTO(content1, content2);

        return webClient.post()
                .uri("/plagiarism/check")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PlagiarismResponseDTO.class)
                .block();
    }

}

