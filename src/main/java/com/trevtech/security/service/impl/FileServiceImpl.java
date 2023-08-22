package com.trevtech.security.service.impl;
import com.trevtech.security.entity.CaseEntity;
import com.trevtech.security.entity.FileEntity;
import com.trevtech.security.exception.ForensicsAPIException;
import com.trevtech.security.exception.ResourceNotFoundException;
import com.trevtech.security.payload.FileDto;
import com.trevtech.security.repository.IFIleRepository;
import com.trevtech.security.repository.IcaseRepository;
import com.trevtech.security.service.FilesService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FileServiceImpl implements FilesService {

    private IFIleRepository ifIleRepository;

    private IcaseRepository icaseRepository;

    private ModelMapper mapper;

    public FileServiceImpl(IFIleRepository ifIleRepository, ModelMapper mapper, IcaseRepository icaseRepository){
        this.ifIleRepository = ifIleRepository;
        this.mapper = mapper;
        this.icaseRepository = icaseRepository;
    }

    @Override
    public FileDto createFile(long caseId, FileDto fileDto) {

        FileEntity fileEntity = mapToEntity(fileDto);
        // retrieve case by id
        CaseEntity caseEntity = icaseRepository.findById(caseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", caseId));
        // set case to file entity
        fileEntity.setCaseEntity(caseEntity);
        FileEntity newFileEntity = ifIleRepository.save(fileEntity);
        return mapToDTO(newFileEntity);
    }

    @Override
    public List<FileDto> getFilesByCaseId(long caseId) {
        // retrieve files by caseId
        List<FileEntity> fileEntities = ifIleRepository.findCaseById(caseId);

        // convert list of file entities to list file dto's
        return fileEntities.stream().map(fileEntity -> mapToDTO(fileEntity)).collect(Collectors.toList());
    }

    @Override
    public FileDto getFileById(Long caseId, Long fileId) {
        // retrieve case entity by id
        CaseEntity caseEntity = icaseRepository.findById(caseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", caseId));

        // retrieve case by id
        FileEntity fileEntity = ifIleRepository.findById(fileId).orElseThrow(() ->
                new ResourceNotFoundException("File", "id", fileId));

        if (!fileEntity.getCaseEntity().getId().equals(caseEntity.getId())){
            throw new ForensicsAPIException(HttpStatus.BAD_REQUEST, "File does not belong to this case");
        }
        return mapToDTO(fileEntity);
    }

    @Override
    public FileDto updateFile(Long caseId, long fileId, FileDto fileRequest) {
        // retrieve case entity by id
        CaseEntity caseEntity = icaseRepository.findById(caseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", caseId));
        // retrieve file by id
        FileEntity fileEntity = ifIleRepository.findById(fileId).orElseThrow(() ->
                new ResourceNotFoundException("File", "id", fileId));
        if (!fileEntity.getCaseEntity().getId().equals(caseEntity.getId())){
            throw new ForensicsAPIException(HttpStatus.BAD_REQUEST, "File does not belong to case");
        }
        fileEntity.setFileName(fileRequest.getFileName());
        fileEntity.setFilePath(fileRequest.getFilePath());
        fileEntity.setFileSize(fileRequest.getFileSize());
        fileEntity.setFileType(fileRequest.getFileType());

        FileEntity updatedFile = ifIleRepository.save(fileEntity);
        return mapToDTO(updatedFile);
    }

    @Override
    public void deleteFileById(Long caseId, Long fileId) {
        // retrieve case entity by id
        CaseEntity caseEntity = icaseRepository.findById(caseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", caseId));

        // retrieve file by id
        FileEntity fileEntity = ifIleRepository.findById(fileId).orElseThrow(() ->
                new ResourceNotFoundException("File", "id", fileId));

        if (!fileEntity.getCaseEntity().getId().equals(caseEntity.getId())){
            throw new ForensicsAPIException(HttpStatus.BAD_REQUEST, "File does not belong to this case");
        }
        ifIleRepository.delete(fileEntity);
    }

    private FileDto mapToDTO(FileEntity fileEntity){
        FileDto fileDto = mapper.map(fileEntity, FileDto.class);
        return fileDto;
    }

    private FileEntity mapToEntity(FileDto fileDto){
        FileEntity fileEntity = mapper.map(fileDto, FileEntity.class);
        return fileEntity;
    }

}
