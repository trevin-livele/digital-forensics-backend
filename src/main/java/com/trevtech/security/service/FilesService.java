package com.trevtech.security.service;
import com.trevtech.security.payload.FileDto;
import java.util.List;

public interface FilesService {
    FileDto createFile(long caseId, FileDto fileDto);
    List<FileDto> getFilesByCaseId(long postId);
    FileDto getFileById(Long caseId, Long fileId);
    FileDto updateFile(Long caseId, long fileId, FileDto fileRequest);
    void deleteFileById(Long caseId, Long fileId);
}