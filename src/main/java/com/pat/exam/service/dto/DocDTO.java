package com.pat.exam.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocDTO {
    private Long docId;
    private String doc;
    private Long userId;
}
