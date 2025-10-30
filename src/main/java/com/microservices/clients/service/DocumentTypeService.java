package com.microservices.clients.service;

import com.microservices.clients.model.DocumentType;
import java.util.List;
import java.util.Optional;

public interface DocumentTypeService {
    List<DocumentType> findAllDocumentTypes();
    Optional<DocumentType> findDocumentTypeById(Long id);
    DocumentType saveDocumentType(DocumentType documentType);
    DocumentType updateDocumentType(Long id, DocumentType documentTypeDetails);
    void deleteDocumentType(Long id);
}
