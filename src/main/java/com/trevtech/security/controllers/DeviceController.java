package com.trevtech.security.controllers;
import com.trevtech.security.payload.DeviceDto;
import com.trevtech.security.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {

    DeviceService deviceService;


    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @PostMapping("/{fileId}/devices")
    public ResponseEntity<DeviceDto> createDevice(@PathVariable(value = "deviceId")long fileId,
                                                  @Valid @RequestBody DeviceDto deviceDto){
        return new ResponseEntity<>(deviceService.createDevice(fileId, deviceDto), HttpStatus.CREATED);
    }



//    @GetMapping("/files/{deviceId}/devices")
//    public List<DeviceDto> getDeviceByFileId(@PathVariable(value = "fileId") Long fileId){
//        return deviceService.getDeviceByFileId(fileId);
//    }


    @GetMapping("/files/{fileId}/devices/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable(value = "fileId") Long fileId,
                                                   @PathVariable(value = "id") Long deviceId){
        DeviceDto deviceDto = deviceService.getDeviceById(fileId, deviceId);
        return new ResponseEntity<>(deviceDto, HttpStatus.OK);
    }

    @PutMapping("/devices/{deviceId}/devices/{id}")
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable(value = "fileId")Long fileId,
                                                  @PathVariable(value = "id") Long deviceId,
                                                  @Valid @RequestBody DeviceDto deviceDto){
        DeviceDto updatedDevice = deviceService.updateDevice(fileId, deviceId, deviceDto);
        return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
    }

    @DeleteMapping("/files/{fileId}/devices/{id}")
    public ResponseEntity<String> deleteDevice(@PathVariable(value = "fileId") Long fileId,
                                             @PathVariable(value = "id") Long deviceId){
        deviceService.deleteDeviceById(fileId, deviceId);
        return new ResponseEntity<>("Device deleted successfully", HttpStatus.OK);
    }
}
