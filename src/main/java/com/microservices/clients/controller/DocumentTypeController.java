package com.microservices.clients.controller;

import com.microservices.clients.dto.DocumentTypeDTO;
import com.microservices.clients.mapper.ClientMapper;
import com.microservices.clients.model.DocumentType;
import com.microservices.clients.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/document-types")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;
    @Autowired
    private ClientMapper clientMapper; // Reutilizamos ClientMapper para mapear DocumentType

    @GetMapping
    public ResponseEntity<List<DocumentTypeDTO>> getAllDocumentTypes() {
        List<DocumentType> documentTypes = documentTypeService.findAllDocumentTypes();
        List<DocumentTypeDTO> documentTypeDTOs = documentTypes.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(documentTypeDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeDTO> getDocumentTypeById(@PathVariable Long id) {
        return documentTypeService.findDocumentTypeById(id)
                .map(clientMapper::toDto)
                .map(documentTypeDTO -> new ResponseEntity<>(documentTypeDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DocumentTypeDTO> createDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        DocumentType documentType = clientMapper.toEntity(documentTypeDTO);
        DocumentType savedDocumentType = documentTypeService.saveDocumentType(documentType);
        return new ResponseEntity<>(clientMapper.toDto(savedDocumentType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentTypeDTO> updateDocumentType(@PathVariable Long id, @RequestBody DocumentTypeDTO documentTypeDTO) {
        try {
            DocumentType documentTypeDetails = clientMapper.toEntity(documentTypeDTO);
            DocumentType updatedDocumentType = documentTypeService.updateDocumentType(id, documentTypeDetails);
            return new ResponseEntity<>(clientMapper.toDto(updatedDocumentType), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDocumentType(@PathVariable Long id) {
        try {
            documentTypeService.deleteDocumentType(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
