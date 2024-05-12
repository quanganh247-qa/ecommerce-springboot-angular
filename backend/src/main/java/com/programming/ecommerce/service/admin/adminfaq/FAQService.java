package com.programming.ecommerce.service.admin.adminfaq;


import com.programming.ecommerce.payload.request.FAQDto;

public interface FAQService {
    FAQDto postFAQ(String productId, FAQDto faqDto);
}
