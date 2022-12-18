package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class StorageService {

    private final Path directory = Paths.get("folder");

    public void init() throws IOException {
        Files.createDirectory(directory);
    }

    public String save(MultipartFile file) {
        try {
            String newName = new Date().getTime() + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.directory.resolve(newName));
            return newName;
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void delete(String filename) throws IOException {
        FileSystemUtils.deleteRecursively(directory.resolve(filename));
    }
}
