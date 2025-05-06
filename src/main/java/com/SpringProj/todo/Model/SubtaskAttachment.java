package com.SpringProj.todo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subtask_attachment")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubtaskAttachment extends Attachment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "subtask_id")  // Need to be experienced
    // private Long subtaskId;

    @ManyToOne()
    @JoinColumn(name = "subtask_id")
    private SubTask subtask;
}
