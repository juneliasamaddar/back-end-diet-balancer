package com.klef.fsad.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.fsad.sdp.entity.FileData;

public interface FileRepository extends JpaRepository<FileData, Integer>
{
    FileData findByFileName(String fileName);
}