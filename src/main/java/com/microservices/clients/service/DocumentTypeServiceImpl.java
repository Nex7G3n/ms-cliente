package com.microservices.clients.service;

import com.microservices.clients.model.DocumentType;
import com.microservices.clients.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentType> findAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }

    @Override
    public Optional<DocumentType> findDocumentTypeById(Long id) {
        return documentTypeRepository.findById(id);
    }

    @Override
    public DocumentType saveDocumentType(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    @Override
    public DocumentType updateDocumentType(Long id, DocumentType documentTypeDetails) {
        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentType not found with id " + id));
        documentType.setName(documentTypeDetails.getName());
        documentType.setDescription(documentTypeDetails.getDescription());
        return documentTypeRepository.save(documentType);
    }

    @Override
    public void deleteDocumentType(Long id) {
        documentTypeRepository.deleteById(id);
    }
}
