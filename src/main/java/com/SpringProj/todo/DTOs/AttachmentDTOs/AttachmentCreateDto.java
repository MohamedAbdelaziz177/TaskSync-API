package com.SpringProj.todo.DTOs.AttachmentDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentCreateDto {

    @NotBlank(message = "Enter file name please")
    private String fileName;

    @NotNull(message = "Select file please")
    private MultipartFile file;
}
