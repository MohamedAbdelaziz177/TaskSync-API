package com.SpringProj.todo.DTOs.AttachmentDTOs;

import com.SpringProj.todo.Model.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentReadDto {

    public AttachmentReadDto(Attachment attachment) {

        this.fileName = attachment.getFileName();
        this.filePath = attachment.getFilePath();
    }

    private String fileName;

    private String filePath;
}
