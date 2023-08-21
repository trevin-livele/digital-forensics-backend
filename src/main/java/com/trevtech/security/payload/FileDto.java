package com.trevtech.security.payload;

import com.trevtech.security.entity.CaseEntity;
import com.trevtech.security.entity.DataEntity;
import com.trevtech.security.entity.DeviceEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FileDto {
    private long id;

    @NotEmpty
    @Size(min = 2, message = "File name should have at least 2 characters")
    private String fileName;

    // post description should be not null or empty
    // post description should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    private String filePath;
    private String fileType;
    private Long fileSize;
    private LocalDateTime creationDateTime;
}
