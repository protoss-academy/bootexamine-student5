package com.protosstechnology.train.bootexamine.service.impl;

import com.protosstechnology.train.bootexamine.model.Document;
import com.protosstechnology.train.bootexamine.service.DocumentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Document doc) {
        entityManager.persist(doc);
        entityManager.flush();
    }

    @Override
    public Document read(Integer id) {
        return entityManager.find(Document.class, id);
    }

    @Override
    public Document update(Document doc) {
        return entityManager.merge(doc);
    }

    @Override
    public void delete(Integer id) {
        Document doc = entityManager.find(Document.class, id);
        if(doc != null){
            entityManager.remove(doc);
        }
    }
}
