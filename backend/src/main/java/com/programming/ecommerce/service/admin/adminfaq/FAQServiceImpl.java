package com.programming.ecommerce.service.admin.adminfaq;

import com.programming.ecommerce.models.FAQ;
import com.programming.ecommerce.models.Product;
import com.programming.ecommerce.payload.request.FAQDto;
import com.programming.ecommerce.repository.FAQRepository;
import com.programming.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FAQServiceImpl implements FAQService{
    private final FAQRepository faqRepository;
    private final ProductRepository productRepository;

    @Override
    public FAQDto postFAQ(String productId, FAQDto faqDto){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            FAQ faq = new FAQ();
            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());
            return faqRepository.save(faq).getFAQDto();
        }
        return null;
    }

}
