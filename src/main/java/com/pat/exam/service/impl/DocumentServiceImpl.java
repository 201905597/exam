package com.pat.exam.service.impl;

import com.pat.exam.repository.DocumentRepository;
import com.pat.exam.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void deleteDoc(Long docId) {
        documentRepository.deleteById(docId);
    }
}
