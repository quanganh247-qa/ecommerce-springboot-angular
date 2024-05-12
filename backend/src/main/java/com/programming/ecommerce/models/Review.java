package com.programming.ecommerce.models;

import com.programming.ecommerce.payload.request.ReviewDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")
public class Review {

    @Id
    private String id;
    private Long rating;
    private String description;
    private byte[] img;
    @DBRef
    private User user;
    @DBRef
    private Product product;

    public ReviewDto getDto(){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(this.id);
        reviewDto.setRating(this.rating);
        reviewDto.setDescription(this.description);
        reviewDto.setReturnedImg(this.img);
        reviewDto.setUserId(this.user.getId());
        reviewDto.setProductId(this.product.getId());
        reviewDto.setUsername(this.user.getUsername());
        return reviewDto;
    }
}
