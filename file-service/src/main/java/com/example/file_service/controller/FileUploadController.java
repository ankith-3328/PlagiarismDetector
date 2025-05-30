package com.example.file_service.controller;

import com.example.file_service.dto.PlagiarismResponseDTO;
import com.example.file_service.service.FileService;
import com.example.file_service.service.PlagiarismServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @Autowired
    private PlagiarismServiceClient plagiarismClient;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) {

        if (file1.isEmpty() || file2.isEmpty()) {
            return ResponseEntity.badRequest().body("Both files must be provided.");
        }

        try {
            String content1 = fileService.readFileContent(file1);
            String content2 = fileService.readFileContent(file2);

            PlagiarismResponseDTO result = plagiarismClient.getSimilarity(content1, content2);

            return ResponseEntity.ok(String.format("%.2f%%", result.similarity));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error reading files: " + e.getMessage());
        }
    }
}