package com.klef.fsad.sdp.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

import com.klef.fsad.sdp.entity.FileData;
import com.klef.fsad.sdp.service.FileService;

@RestController
@RequestMapping("/fileapi")
@CrossOrigin("*")
public class FileController 
{
    @Autowired
    private FileService service;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request)
    {
        try 
        {
            String email = (String) request.getAttribute("userEmail");

            if(email == null)
            {
                return ResponseEntity.status(401).body("Unauthorized");
            }

            return ResponseEntity.ok(service.uploadFile(file, email));
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(500).body("Upload Failed");
        }
    }

    @GetMapping("/{filename}")
    public ResponseEntity<?> downloadFile(
            @PathVariable String filename,
            HttpServletRequest request) throws IOException
    {
        String email = (String) request.getAttribute("userEmail");

        if(email == null)
        {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        FileData file = service.getFileByName(filename);

        if(file == null)
        {
            return ResponseEntity.status(404).body("File Not Found");
        }

        int userId = service.getUserIdByEmail(email);

        if(file.getUserId() != userId)
        {
            return ResponseEntity.status(403).body("Access Denied");
        }

        Path path = Paths.get(file.getFilePath());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}