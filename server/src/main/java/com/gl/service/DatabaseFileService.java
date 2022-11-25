package com.gl.service;

import com.gl.exceptions.FileNotFoundException;
import com.gl.exceptions.FileStorageException;
import com.gl.model.DatabaseFile;
import com.gl.repo.DatabaseFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DatabaseFileService {

    @Autowired
    private DatabaseFileRepo databaseFileRepo;

    public DatabaseFile storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence '..' " + fileName);
            }

            DatabaseFile databaseFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());

            return databaseFileRepo.save(databaseFile);
        } catch (IOException e) {
            try {
                throw new FileStorageException(fileName + " could not be saved");
            } catch (FileStorageException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public DatabaseFile getFileByName(String fileName) {
        try {
            return databaseFileRepo.findByFileName(fileName).orElseThrow(() -> new FileNotFoundException(fileName + " could not be found"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public DatabaseFile getFile(String fileId) {
        try {
            return databaseFileRepo.findById(fileId).orElseThrow(() -> new FileNotFoundException(fileId + " could not be found"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}