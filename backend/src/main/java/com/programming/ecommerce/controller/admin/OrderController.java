package com.programming.ecommerce.controller.admin;

import com.programming.ecommerce.models.Order;
import com.programming.ecommerce.payload.request.OrderDto;
import com.programming.ecommerce.payload.response.AnalyticsResponse;
import com.programming.ecommerce.service.admin.adminorder.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final AdminOrderService adminOrderService;

    @GetMapping("/placedOrders")
    public ResponseEntity<List<OrderDto>> getAllPlacedOrders(){
        return ResponseEntity.ok(adminOrderService.getAllPlacedOrders());
    }

    @GetMapping("/order/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable("orderId") String orderId, @PathVariable("status") String status){
        OrderDto orderDto = adminOrderService.changeOrderStatus(orderId,status);
        if(orderDto == null){
            return ResponseEntity.badRequest().body("Order not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/order/analytics")
    public ResponseEntity<AnalyticsResponse> getAnalytics(){
        return ResponseEntity.ok(adminOrderService.calculateAnalytics());
    }

}
