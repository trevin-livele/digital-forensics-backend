package com.trevtech.security.service.impl;
import com.trevtech.security.entity.CaseEntity;
import com.trevtech.security.entity.DeviceEntity;
import com.trevtech.security.entity.FileEntity;
import com.trevtech.security.exception.ResourceNotFoundException;
import com.trevtech.security.payload.DeviceDto;
import com.trevtech.security.payload.FileDto;
import com.trevtech.security.repository.IDeviceRepository;
import com.trevtech.security.repository.IFIleRepository;
import com.trevtech.security.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private IDeviceRepository iDeviceRepository;

    private IFIleRepository ifIleRepository;
    private ModelMapper mapper;

    public DeviceServiceImpl(IDeviceRepository iDeviceRepository, ModelMapper mapper, IFIleRepository ifIleRepository){
        this.iDeviceRepository = iDeviceRepository;
        this.ifIleRepository = ifIleRepository;
        this.mapper = mapper;
    }


    @Override
    public DeviceDto createDevice(long fileId, DeviceDto deviceDto) {
        DeviceEntity deviceEntity = mapToEntity(deviceDto);
        FileEntity fileEntity = ifIleRepository.findById(fileId).orElseThrow(
                () -> new ResourceNotFoundException("File", "id", fileId));
        deviceEntity.setFiles(Collections.singleton(fileEntity));
        DeviceEntity newDeviceEntity = iDeviceRepository.save(deviceEntity);
        return mapToDTO(newDeviceEntity);
    }


    @Override
    public DeviceDto getDeviceById(Long fileId, Long deviceId) {
        FileEntity fileEntity = ifIleRepository.findById(fileId).orElseThrow(
                () -> new ResourceNotFoundException("File", "id", fileId));

        DeviceEntity deviceEntity = iDeviceRepository.findById(deviceId).orElseThrow(
                () -> new ResourceNotFoundException("Device", "id", deviceId));
        // TODO: 8/26/2023
//        if (!fileEntity.getCaseEntity().getId().equals(caseEntity.getId())){
//            throw new ForensicsAPIException(HttpStatus.BAD_REQUEST, "File does not belong to this case");
//        }
        return mapToDTO(deviceEntity);
    }

    @Override
    public DeviceDto updateDevice(Long fileId, long deviceId, DeviceDto deviceRequest) {
        FileEntity fileEntity = ifIleRepository.findById(fileId).orElseThrow(
                () -> new ResourceNotFoundException("File", "id", fileId));

        DeviceEntity deviceEntity = iDeviceRepository.findById(deviceId).orElseThrow(() ->
                new ResourceNotFoundException("Device", "id",deviceId));
        // TODO: 8/26/2023
//        if (!fileEntity.getCaseEntity().getId().equals(caseEntity.getId())){
//            throw new ForensicsAPIException(HttpStatus.BAD_REQUEST, "File does not belong to this case");
//        }
        deviceEntity.setDeviceName(deviceRequest.getDeviceName());
        deviceEntity.setDeviceType(deviceRequest.getDeviceType());
        deviceEntity.setSerialNumber(deviceEntity.getSerialNumber());
        DeviceEntity updatedDevice = iDeviceRepository.save(deviceEntity);
        return mapToDTO(updatedDevice);
    }


    @Override
    public void deleteDeviceById(Long fileId, Long deviceId) {
        FileEntity fileEntity = ifIleRepository.findById(fileId).orElseThrow(() ->
                new ResourceNotFoundException("File", "id", fileId));

        DeviceEntity deviceEntity = iDeviceRepository.findById(fileId).orElseThrow(() ->
                new ResourceNotFoundException("Device", "id", deviceId));

        // TODO: 8/26/2023
//        if (!fileEntity.getCaseEntity().getId().equals(caseEntity.getId())){
//            throw new ForensicsAPIException(HttpStatus.BAD_REQUEST, "File does not belong to this case");
//        }
        iDeviceRepository.delete(deviceEntity);
    }

    @Override
    public List<DeviceDto> getDeviceByFileId(long fileId) {
        List<DeviceEntity> deviceEntities = iDeviceRepository.findFileById(fileId);
        return deviceEntities.stream().map(deviceEntity -> mapToDTO(deviceEntity)).collect(Collectors.toList());
    }
    // convert Entity into DTO
    private DeviceDto mapToDTO(DeviceEntity deviceEntity){
        DeviceDto deviceDto = mapper.map(deviceEntity, DeviceDto.class);
        return deviceDto;
    }

    // convert DTO to entity
    private DeviceEntity mapToEntity(DeviceDto deviceDto){
        DeviceEntity deviceEntity = mapper.map(deviceDto, DeviceEntity.class);
        return deviceEntity;
    }
}
