package com.minecraft_wiki.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "homeSlides")

public class HomeSlide {
    @Id
    private ObjectId slideId;

    private String title;
    private String description;
    private String imageUrl;

    private int displayOrder;
}
