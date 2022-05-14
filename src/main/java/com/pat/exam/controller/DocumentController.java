package com.pat.exam.controller;

import com.pat.exam.service.DocumentService;
import com.pat.exam.service.dto.DocDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<DocDTO> deleteDocById(@PathVariable Long docId){
        documentService.deleteDoc(docId);
        return ResponseEntity.noContent().build();
    }
}
