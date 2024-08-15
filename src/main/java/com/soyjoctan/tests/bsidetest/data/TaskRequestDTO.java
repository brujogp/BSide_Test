package com.soyjoctan.tests.bsidetest.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TaskRequestDTO {
    @JsonProperty("task_name")
    private String taskName;

    @JsonProperty("finish_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Date finishDate;
}
