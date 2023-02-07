package com.teachmeskills.security.controller;

import com.teachmeskills.security.dto.DocumentTypeDto;
import com.teachmeskills.security.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentsController {

    private final DocumentService documentService;

    @RequestMapping
    protected String doGet(@PathVariable("id") long id,  final Model model) {
        model.addAttribute("type", new DocumentTypeDto());
        DocumentTypeDto documentType = documentService.getDocumentTypeById(id);
        model.addAttribute("type", documentType);
        return "main";
    }
}
