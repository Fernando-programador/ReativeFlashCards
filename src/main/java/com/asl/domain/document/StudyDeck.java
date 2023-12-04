package com.asl.domain.document;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;

public record StudyDeck(@Field("deck_id")
						String deckId, 
						Set<StudyCard> cards) {
	
	@Builder(toBuilder = true)
	public StudyDeck {}

}
