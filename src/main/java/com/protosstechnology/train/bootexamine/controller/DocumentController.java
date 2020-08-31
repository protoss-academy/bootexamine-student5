package com.protosstechnology.train.bootexamine.controller;

import com.protosstechnology.train.bootexamine.model.Document;
import com.protosstechnology.train.bootexamine.model.DocumentRequest;
import com.protosstechnology.train.bootexamine.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Operation(summary = "Get Document by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the Document",
                    content = {  @Content(mediaType = "application/json",schema = @Schema(implementation = Document.class)) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id Document",
                    content = {  @Content }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Document not found",
                    content = {  @Content }
            ),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable("id") String id){
        log.info("getDocument id={}", id);
        Document doc = documentService.read(Integer.parseInt(id));
        log.info("getDocument doc={}", doc);
        if(doc == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(documentService.read(Integer.parseInt(id)));
        }
    }

    @PostMapping
    public ResponseEntity<Document> addDocument(@RequestBody DocumentRequest docReq){
        log.info("addDocument");
        Document doc = docReq.toDocument();
        documentService.create(doc);
        log.info("document id={}", doc.getId());
        return ResponseEntity.ok().body(doc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable("id") String id, @RequestBody DocumentRequest docReq){
        log.info("updateDocument");
        Document doc = docReq.toDocument();
        doc.setId(Integer.parseInt(id));
        documentService.update(doc);
        return ResponseEntity.ok().body(doc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable("id") String id){
        String responseStr = "";
        try {
            documentService.delete(Integer.parseInt(id));
            responseStr = "Delete document{"+id+"} successful";
        }catch (Exception e){
            responseStr = "Delete document{"+id+"} fail";
        }
        return ResponseEntity.ok().body(responseStr);
    }

}
