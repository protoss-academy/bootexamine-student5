package com.protosstechnology.train.bootexamine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequest {
    @NotNull
    private String documentNumber;

    @NotNull
    private String description;

    public Document toDocument(){
        Document doc = new Document();
        doc.setDocumentNumber(this.documentNumber);
        doc.setDescription(this.description);
        return doc;
    }
}
