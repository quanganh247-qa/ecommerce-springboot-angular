package com.programming.ecommerce.models;

import com.programming.ecommerce.payload.request.FAQDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "faq")
public class FAQ {
    @Id
    private String id;

    private String question;
    private String answer;

    @DBRef
    private Product product;

    public FAQDto getFAQDto(){
        FAQDto faqDto = new FAQDto();
        faqDto.setId(id);
        faqDto.setQuestion(question);
        faqDto.setAnswer(answer);
        faqDto.setProductId(product.getId());
        return faqDto;
    }
}
