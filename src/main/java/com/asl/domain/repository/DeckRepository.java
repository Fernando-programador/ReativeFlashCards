package com.asl.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.asl.domain.document.DeckDocument;

@Repository
public interface DeckRepository extends ReactiveMongoRepository<DeckDocument, String>{

}
