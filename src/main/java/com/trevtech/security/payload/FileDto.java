package com.trevtech.security.payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
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
