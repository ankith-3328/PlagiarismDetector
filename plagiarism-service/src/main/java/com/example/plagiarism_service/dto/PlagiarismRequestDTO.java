package com.example.plagiarism_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlagiarismRequestDTO {
    public String content1;
    public String content2;
}
