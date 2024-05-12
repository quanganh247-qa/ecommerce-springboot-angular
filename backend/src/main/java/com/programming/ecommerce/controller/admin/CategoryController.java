package com.programming.ecommerce.controller.admin;

import com.programming.ecommerce.models.Category;
import com.programming.ecommerce.payload.request.CategoryRequest;
import com.programming.ecommerce.service.admin.admincategory.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.addCategory(categoryRequest);
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
