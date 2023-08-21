package com.trevtech.security.service;

import com.trevtech.security.payload.CaseDto;
import com.trevtech.security.payload.FileDto;

public interface FilesService {

    FileDto createFile(FileDto fileDto);
    FileDto getFileById(long id);
    FileDto updateFile(FileDto fileDto, long id);
    void deleteFileById(long id);
}
