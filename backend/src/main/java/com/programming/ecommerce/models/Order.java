package com.programming.ecommerce.models;

import com.programming.ecommerce.models.enums.OrderStatus;
import com.programming.ecommerce.payload.request.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String orderDescription;
    private Date orderDate;
    private Long amount;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackID;
    @DBRef
    private User user;
    @DBRef
    private List<CartItems> cartItems = new ArrayList<>();
    @DBRef
    private Coupon coupon;

    public OrderDto getOrderDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setOrderDescription(orderDescription);
        orderDto.setOrderDate(orderDate);
        orderDto.setAmount(amount);
        orderDto.setAddress(address);
        orderDto.setPayment(payment);
        orderDto.setOrderStatus(orderStatus);
        orderDto.setTotalAmount(totalAmount);
        orderDto.setDiscount(discount);
        orderDto.setTrackID(trackID);
        orderDto.setUserName(user.getUsername());
        if(coupon != null){
            orderDto.setCouponName(coupon.getName());
        }
        return orderDto;
    }
}
