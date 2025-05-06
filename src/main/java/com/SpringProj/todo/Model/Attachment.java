package com.SpringProj.todo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
public  class Attachment {

    @NotBlank(message = "fileName cannot be empty")
    protected String fileName;

    @NotBlank(message = "fileName cannot be empty")
    protected String filePath;

}
