package com.asl.domain.document;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;

@Document(collection = "studies")
public record StudyDocument(@Id 
							String id, 
							@Field("user_id")
							String userId,
							StudyDeck studyDeck,
							List<Question> question,
							@CreatedDate
							@Field("created_at")
							OffsetDateTime createdAt,
							@LastModifiedDate
							@Field("update_at")
							OffsetDateTime updateAt) {
	
	@Builder(toBuilder = true)
	public StudyDocument {}

}
