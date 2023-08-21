package com.trevtech.security.service.impl;

import com.trevtech.security.payload.FileDto;
import com.trevtech.security.repository.IFIleRepository;
import com.trevtech.security.service.FilesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class FileServiceImpl implements FilesService {

    private IFIleRepository ifIleRepository;

    private ModelMapper mapper;

    public FileServiceImpl(IFIleRepository ifIleRepository, ModelMapper mapper){
        this.ifIleRepository = ifIleRepository;
        this.mapper = mapper;
    }


    @Override
    public FileDto createFile(FileDto fileDto) {
        return null;
    }

    @Override
    public FileDto getFileById(long id) {
        return null;
    }

    @Override
    public FileDto updateFile(FileDto fileDto, long id) {
        return null;
    }

    @Override
    public void deleteFileById(long id) {

    }
}
