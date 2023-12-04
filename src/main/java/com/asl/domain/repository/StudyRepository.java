package com.asl.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.asl.domain.document.StudyDocument;

@Repository
public interface StudyRepository extends ReactiveMongoRepository<StudyDocument, String> {

}
