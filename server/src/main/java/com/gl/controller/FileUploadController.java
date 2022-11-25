package com.gl.controller;

import com.gl.model.DatabaseFile;
import com.gl.payload.Response;
import com.gl.service.DatabaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/upload-file")
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping
    public Response uploadFile(@RequestParam("file")MultipartFile file) {
    DatabaseFile fileName = fileStorageService.storeFile(file);
    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download-file/").path(fileName.getFileName()).toUriString();
    return new Response(fileName.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());

    }

    @PostMapping("/many")
    public List<Response> uploadManyFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
    }
}