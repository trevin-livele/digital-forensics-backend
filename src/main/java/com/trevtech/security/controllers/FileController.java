package com.trevtech.security.controllers;
import com.trevtech.security.payload.FileDto;
import com.trevtech.security.service.FilesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {
FilesService filesService;

    public FileController(FilesService filesService){
        this.filesService = filesService;
    }

    @PostMapping("/{caseId}/files")
    public ResponseEntity<FileDto> createFile(@PathVariable(value = "caseId") long caseid,
                                              @Valid @RequestBody FileDto fileDto){
        return new ResponseEntity<>(filesService.createFile(caseid, fileDto), HttpStatus.CREATED);
    }

    @GetMapping("/cases/{caseId}/files")
    public List<FileDto> getFilesByCaseId(@PathVariable(value = "caseId") Long caseId){
        return filesService.getFilesByCaseId(caseId);
    }


    @GetMapping("/cases/{caseId}/files/{id}")
    public ResponseEntity<FileDto> getFileById(@PathVariable(value = "caseId") Long caseId,
                                               @PathVariable(value = "id") Long fileId){
        FileDto fileDto = filesService.getFileById(caseId, fileId);
        return new ResponseEntity<>(fileDto, HttpStatus.OK);
    }

    @PutMapping("/cases/{casesId}/files/{id}")
    public ResponseEntity<FileDto> updateComment(@PathVariable(value = "caseId") Long caseId,
                                                 @PathVariable(value = "id") Long fileId,
                                                 @Valid @RequestBody FileDto fileDto){
        FileDto updatedFile = filesService.updateFile(caseId, fileId, fileDto);
        return new ResponseEntity<>(updatedFile, HttpStatus.OK);
    }

    @DeleteMapping("/cases/{caseId}/files/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable(value = "caseId") Long caseId,
                                             @PathVariable(value = "id") Long fileId){
        filesService.deleteFileById(caseId, fileId);
        return new ResponseEntity<>("File deleted successfully", HttpStatus.OK);
    }
}
