package com.example.plagiarism_service.controller;

import com.example.plagiarism_service.dto.PlagiarismRequestDTO;
import com.example.plagiarism_service.service.PlagiarismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plagiarism")
public class PlagiarismController {

    @Autowired
    PlagiarismService plagiarismService;

    @PostMapping("/check")
    public ResponseEntity<Double> checkPlagiarism(@RequestBody PlagiarismRequestDTO plagiarismRequestDTO){
        double similarity = plagiarismService.calculate(plagiarismRequestDTO.getContent1(),
                            plagiarismRequestDTO.getContent2());

        return ResponseEntity.ok(similarity);
    }
}
