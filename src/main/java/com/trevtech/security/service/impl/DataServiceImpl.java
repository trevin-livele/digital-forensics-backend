package com.trevtech.security.service.impl;

import com.trevtech.security.entity.CaseEntity;
import com.trevtech.security.entity.DataEntity;
import com.trevtech.security.entity.FileEntity;
import com.trevtech.security.exception.ForensicsAPIException;
import com.trevtech.security.exception.ResourceNotFoundException;
import com.trevtech.security.payload.DataDto;
import com.trevtech.security.payload.FileDto;
import com.trevtech.security.repository.IFIleRepository;
import com.trevtech.security.repository.IcaseRepository;
import com.trevtech.security.repository.IdataRepository;
import com.trevtech.security.service.IDataService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements IDataService {

    private IcaseRepository icaseRepository;

    private ModelMapper mapper;

    private IdataRepository idataRepository;

    public DataServiceImpl(IdataRepository idataRepository,ModelMapper mapper, IcaseRepository icaseRepository){
        this.idataRepository = idataRepository;
        this.mapper = mapper;
        this.icaseRepository = icaseRepository;
    }

    @Override
    public DataDto createData(long caseId, DataDto dataDto) {
        DataEntity dataEntity = mapToEntity(dataDto);
        // retrieve case by id
        CaseEntity caseEntity = icaseRepository.findById(caseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", caseId));
        // set case to file entity
        dataEntity.setCaseEntity(caseEntity);
        DataEntity newDataEntity = idataRepository.save(dataEntity);
        return mapToDTO(newDataEntity);
    }

    @Override
    public List<DataDto> getDataByCaseId(long caseId) {
        // retrieve files by caseId
        List<DataEntity> dataEntities = idataRepository.findCaseById(caseId);

        // convert list of file entities to list file dto's
        return dataEntities.stream().map(dataEntity -> mapToDTO(dataEntity)).collect(Collectors.toList());
    }

    @Override
    public DataDto getDataById(Long caseId, Long dataId) {
        // retrieve case entity by id
        CaseEntity caseEntity = icaseRepository.findById(caseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", caseId));

        // retrieve case by id
        DataEntity dataEntity = idataRepository.findById(dataId).orElseThrow(() ->
                new ResourceNotFoundException("File", "id", dataId));

        if (!dataEntity.getCaseEntity().getId().equals(caseEntity.getId())){
            throw new ForensicsAPIException(HttpStatus.BAD_REQUEST, "File does not belong to this case");
        }
        return mapToDTO(dataEntity);
    }



    @Override
    public DataDto updateData(Long caseId, long dataId, DataDto dataRequest) {
        // retrieve case entity by id
        CaseEntity caseEntity = icaseRepository.findById(caseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", caseId));
        // retrieve file by id
        DataEntity dataEntity  = idataRepository.findById(dataId).orElseThrow(() ->
                new ResourceNotFoundException("File", "id", dataId));
        if (!dataEntity.getCaseEntity().getId().equals(caseEntity.getId())){
            throw new ForensicsAPIException(HttpStatus.BAD_REQUEST, "Data does not belong to case");
        }
        dataEntity.setDataName(dataRequest.getDataName());
        dataEntity.setDataSize(dataRequest.getDataSize());
        dataEntity.setDataType(dataRequest.getDataType());
        DataEntity updatedData = idataRepository.save(dataEntity);
        return mapToDTO(updatedData);
    }

    @Override
    public void deleteDataById(Long caseId, Long dataId) {
        // retrieve case entity by id
        CaseEntity caseEntity = icaseRepository.findById(caseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", caseId));
        // retrieve file by id
        DataEntity dataEntity = idataRepository.findById(dataId).orElseThrow(() ->
                new ResourceNotFoundException("Data", "id", dataId));
        if (!dataEntity.getCaseEntity().getId().equals(caseEntity.getId())){
            throw new ForensicsAPIException(HttpStatus.BAD_REQUEST, "Data does not belong to this case");
        }
        idataRepository.delete(dataEntity);
    }


    private DataDto mapToDTO(DataEntity dataEntity){
        DataDto dataDto = mapper.map(dataEntity, DataDto.class);
        return dataDto;
    }

    private DataEntity mapToEntity(DataDto dataDto){
        DataEntity dataEntity = mapper.map(dataDto, DataEntity.class);
        return dataEntity;
    }
}
