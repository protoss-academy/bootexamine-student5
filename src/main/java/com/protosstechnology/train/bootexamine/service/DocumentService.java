package com.protosstechnology.train.bootexamine.service;

import com.protosstechnology.train.bootexamine.model.Document;

public interface DocumentService {
    public void create(Document doc);
    public Document read(Integer id);
    public Document update(Document doc);
    public void delete(Integer id);
}