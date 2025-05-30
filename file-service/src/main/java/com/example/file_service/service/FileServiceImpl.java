package com.example.file_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public boolean isTextFile(MultipartFile file) {
        return file.getOriginalFilename() != null && file.getOriginalFilename().endsWith(".txt");
    }

    @Override
    public String readFileContent(MultipartFile file) throws Exception {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
