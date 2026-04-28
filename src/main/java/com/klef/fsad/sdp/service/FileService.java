package com.klef.fsad.sdp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.fsad.sdp.entity.FileData;
import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.repository.FileRepository;
import com.klef.fsad.sdp.repository.UserRepository;

@Service
public class FileService 
{
    private final String UPLOAD_DIR = "uploads/";

    @Autowired
    private FileRepository repo;

    @Autowired
    private UserRepository userRepo;

    public String uploadFile(MultipartFile file, String email) throws IOException
    {
        User user = userRepo.findByEmail(email);

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path path = Paths.get(UPLOAD_DIR + fileName);

        Files.createDirectories(path.getParent());

        Files.write(path, file.getBytes());

        FileData fd = new FileData();
        fd.setFileName(fileName);
        fd.setFileType(file.getContentType());
        fd.setFilePath(path.toString());
        fd.setUserId(user.getId());

        repo.save(fd);

        return "File Uploaded Successfully";
    }

    public FileData getFileByName(String fileName)
    {
        return repo.findByFileName(fileName);
    }

    public int getUserIdByEmail(String email)
    {
        return userRepo.findByEmail(email).getId();
    }
}