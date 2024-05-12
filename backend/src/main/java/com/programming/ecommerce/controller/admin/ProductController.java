package com.programming.ecommerce.controller.admin;
import com.programming.ecommerce.payload.request.FAQDto;
import com.programming.ecommerce.payload.request.ProductRequest;
import com.programming.ecommerce.repository.FAQRepository;
import com.programming.ecommerce.service.admin.adminfaq.FAQService;
import com.programming.ecommerce.service.admin.adminproduct.AdminProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final AdminProductService  adminProductService;
    private final FAQService faqService;
    @PostMapping("/product")
    public ResponseEntity<ProductRequest> addProduct(@ModelAttribute ProductRequest productRequest,@RequestParam("image") MultipartFile image) throws IOException{
        ProductRequest productRequest1 = adminProductService.addProduct(productRequest,image);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRequest1);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductRequest> getAllProducts(){
        return adminProductService.getAllProducts();
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductRequest>> getAllProductByNameContaining(@PathVariable("name") String name){
        List<ProductRequest> productRequests = adminProductService.getAllProductByNameContaining(name);
        return ResponseEntity.ok(productRequests);
    }

    @DeleteMapping("/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("productId") String productId){
        adminProductService.deleteProduct(productId);
    }

//    @PostMapping("/faq/{productId}")
//    public ResponseEntity<?> postFAQ(@PathVariable("productId") String productId, @RequestBody FAQDto faqDto){
//        return ResponseEntity.ok(faqService.postFAQ(productId,faqDto));
//    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductRequest> getProductById(@PathVariable("productId") String productId){
        ProductRequest productRequest = adminProductService.getProductById(productId);
        if(productRequest != null){
            return ResponseEntity.ok(productRequest);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{productId}")
    public  ResponseEntity<ProductRequest> updateProduct(@PathVariable("productId") String productId, @ModelAttribute ProductRequest productRequest){
        ProductRequest productRequest1 = adminProductService.updateProduct(productId,productRequest);
        if(productRequest1 != null){
            return ResponseEntity.ok(productRequest1);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
