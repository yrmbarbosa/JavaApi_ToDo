package com.yrmb.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private long id;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime resolvedDate;

}
