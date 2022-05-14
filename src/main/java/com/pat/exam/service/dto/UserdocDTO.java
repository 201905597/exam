package com.pat.exam.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase para hacer la JOIN entre USERS y DOCS (parte #1 del examen)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserdocDTO {
    private String user;
    private String doc;
}
