package com.example.file_service.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public abstract boolean isTextFile(MultipartFile file);
    public abstract String readFileContent(MultipartFile file) throws Exception;
}
