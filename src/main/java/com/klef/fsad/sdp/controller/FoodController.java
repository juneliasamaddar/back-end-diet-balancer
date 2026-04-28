package com.klef.fsad.sdp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/fileapi")
@CrossOrigin("*")
public class FileController 
{
    private final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) 
    {
        try 
        {
            File folder = new File(UPLOAD_DIR);
            if (!folder.exists()) 
            {
                folder.mkdir();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File dest = new File(UPLOAD_DIR + fileName);

            file.transferTo(dest);

            return ResponseEntity.ok("File uploaded: " + fileName);
        } 
        catch (IOException e) 
        {
            return ResponseEntity.status(500).body("Upload Failed");
        }
    }
}