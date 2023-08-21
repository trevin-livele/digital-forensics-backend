package com.trevtech.security.service.impl;
import com.trevtech.security.entity.CaseEntity;
import com.trevtech.security.exception.ResourceNotFoundException;
import com.trevtech.security.payload.CaseDto;
import com.trevtech.security.payload.CaseResponse;
import com.trevtech.security.repository.IcaseRepository;
import com.trevtech.security.service.ICaseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CaseServiceImpl implements ICaseService {
private IcaseRepository icaseRepository;
    private ModelMapper mapper;

    public CaseServiceImpl(IcaseRepository icaseRepository, ModelMapper mapper){
        this.icaseRepository = icaseRepository;
        this.mapper = mapper;
    }

    @Override
    public CaseDto createCase(CaseDto caseDto) {

        // convert DTO to entity
        CaseEntity caseEntity = mapToEntity(caseDto);
        CaseEntity newCase = icaseRepository.save(caseEntity);
        // convert entity to DTO
        CaseDto caseResponse = mapToDTO(newCase);
        return caseResponse;
    }

    @Override
    public CaseDto getCaseById(long id) {
        CaseEntity caseEntity = icaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Case", "id", id));
        return mapToDTO(caseEntity);
    }

    @Override
    public CaseResponse getAllCases(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<CaseEntity> caseEntities = icaseRepository.findAll(pageable);
        // get content for page object
        List<CaseEntity> listOfCases = caseEntities.getContent();
        List<CaseDto> content = listOfCases.stream().map(caseEntity -> mapToDTO(caseEntity)).collect(Collectors.toList());
        CaseResponse caseResponse = new CaseResponse();
        caseResponse.setContent(content);
        caseResponse.setPageNo(caseEntities.getNumber());
        caseResponse.setPageSize(caseEntities.getSize());
        caseResponse.setTotalElements(caseEntities.getTotalElements());
        caseResponse.setTotalPages(caseEntities.getTotalPages());
        caseResponse.setLast(caseEntities.isLast());
        return caseResponse;
    }

    @Override
    public CaseDto updateCase(CaseDto caseDto, long id) {
        // get post by id from the database
        CaseEntity caseEntity = icaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Case", "id", id));
        caseEntity.setCaseName(caseDto.getCaseName());
        caseEntity.setDescription(caseDto.getDescription());
        caseEntity.setCreationDateTime(caseDto.getCreationDateTime());
        CaseEntity updatedCase = icaseRepository.save(caseEntity);
        return  mapToDTO(updatedCase);
    }

    @Override
    public void deleteCaseById(long id) {
        CaseEntity caseEntity = icaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Case", "id", id));
        icaseRepository.delete(caseEntity);
    }

    // convert Entity into DTO
    private CaseDto mapToDTO(CaseEntity caseEntity){
        CaseDto caseDto = mapper.map(caseEntity, CaseDto.class);
        return caseDto;
    }

    // convert DTO to entity
    private CaseEntity mapToEntity(CaseDto caseDto){
        CaseEntity caseEntity = mapper.map(caseDto, CaseEntity.class);
        return caseEntity;
    }
}
