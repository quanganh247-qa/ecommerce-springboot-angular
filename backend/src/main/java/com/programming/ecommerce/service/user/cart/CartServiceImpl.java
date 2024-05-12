package com.programming.ecommerce.service.user.cart;

import com.programming.ecommerce.exceptions.ValidationException;
import com.programming.ecommerce.models.*;
import com.programming.ecommerce.models.enums.OrderStatus;
import com.programming.ecommerce.payload.request.AddProductToCartDto;
import com.programming.ecommerce.payload.request.CartItemsDto;
import com.programming.ecommerce.payload.request.OrderDto;
import com.programming.ecommerce.payload.request.PlaceOrderDto;
import com.programming.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartItemsRepository cartItemsRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;

    @Override
    public ResponseEntity<?> addCart(AddProductToCartDto addProductToCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductToCartDto.getUserId(), OrderStatus.Pending);
        Optional<CartItems> cartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductToCartDto.getProductId(), activeOrder.getId(), addProductToCartDto.getUserId());
        if(cartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        else {
            Optional<Product> productOptional = productRepository.findById(addProductToCartDto.getProductId());
            Optional<User> userOptional = userRepository.findById(addProductToCartDto.getUserId());
            if(productOptional.isPresent() && userOptional.isPresent()){
                CartItems cartItems1 = new CartItems();
                cartItems1.setProduct(productOptional.get());
                cartItems1.setPrice(productOptional.get().getPrice());
                cartItems1.setQuantity(1L);
                cartItems1.setUser(userOptional.get());
                cartItems1.setOrder(activeOrder);

                CartItems updateCart = cartItemsRepository.save(cartItems1);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount()+cartItems1.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cartItems1.getPrice());
                activeOrder.getCartItems().add(updateCart);

                orderRepository.save(activeOrder);
                return ResponseEntity.status(HttpStatus.CREATED).body(cartItems1);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }

    @Override
    public OrderDto getCardByUserId(String userId){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems ::getCartDto).collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);
        if(activeOrder.getCoupon() != null){
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }
        return orderDto;

    }

    @Override
    public OrderDto applyCoupon(String userId, String code){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        Coupon coupon =  couponRepository.findByCode(code).orElseThrow(() -> new ValidationException("Coupon not found"));
        if(couponExpired(coupon)){
            throw new ValidationException("Coupon expired");
        }
        double discountAmount = ((coupon.getDiscount()/ 100.0)* activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount() - discountAmount;
        activeOrder.setAmount((long)netAmount);
        activeOrder.setDiscount((long)discountAmount);
        activeOrder.setCoupon(coupon);
        orderRepository.save(activeOrder);
        return activeOrder.getOrderDto();

    }

    public boolean couponExpired(Coupon coupon){
        Date expiryDate = coupon.getExpirationDate();
        Date current =  new Date();

        return expiryDate != null && expiryDate.before(current);
    }

    @Override
    public OrderDto increaseProductQuantity(AddProductToCartDto addProductToCartDto){

        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductToCartDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(addProductToCartDto.getProductId());

        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                addProductToCartDto.getProductId(), activeOrder.getId(), addProductToCartDto.getUserId()
        );

        if(optionalProduct.isPresent()&& optionalCartItems.isPresent()){
            CartItems cartItems = optionalCartItems.get();
            Product product = optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount()+ product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount()+ product.getPrice());
            cartItems.setQuantity(cartItems.getQuantity()+1);
            if (activeOrder.getCoupon()!=null){
                double discountAmount = ((activeOrder.getCoupon().getDiscount()/100.0)* activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;
                activeOrder.setAmount((long)netAmount);
                activeOrder.setDiscount((long)discountAmount);
            }
            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }

        return null;
    }

    @Override
    public OrderDto decreaseProductQuantity(AddProductToCartDto addProductToCartDto){

        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductToCartDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(addProductToCartDto.getProductId());

        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                addProductToCartDto.getProductId(), activeOrder.getId(), addProductToCartDto.getUserId()
        );

        if(optionalProduct.isPresent()&& optionalCartItems.isPresent()){
            CartItems cartItems = optionalCartItems.get();
            Product product = optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());
            cartItems.setQuantity(cartItems.getQuantity()-  1);
            if (activeOrder.getCoupon()!=null){
                double discountAmount = ((activeOrder.getCoupon().getDiscount()/100.0)* activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;
                activeOrder.setAmount((long)netAmount);
                activeOrder.setDiscount((long)discountAmount);
            }
            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }

        return null;
    }

    @Override
    public OrderDto placeOrder(PlaceOrderDto placeOrderDto){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
        Optional<User> userOptional = userRepository.findById(placeOrderDto.getUserId());
        if(userOptional.isPresent()){
            activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
            activeOrder.setAddress(placeOrderDto.getAddress());
            activeOrder.setOrderDate(new Date());
            activeOrder.setOrderStatus(OrderStatus.Placed);
            activeOrder.setTrackID(java.util.UUID.randomUUID());
            orderRepository.save(activeOrder);

            Order newOrder = new Order();
            newOrder.setAmount(0L);
            newOrder.setTotalAmount(0L);
            newOrder.setDiscount(0L);
            newOrder.setUser(userOptional.get());
            newOrder.setOrderStatus(OrderStatus.Pending);
            orderRepository.save(newOrder);
            return activeOrder.getOrderDto();

        }
        return null;
    }

    @Override
    public List<OrderDto> getMyPLacedOrders(String userId){
        return orderRepository.findByUserIdAndOrderStatusIn(userId,List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered))
                .stream().map(Order::getOrderDto).toList();
    }

    @Override
    public OrderDto searchOrder(UUID trackId){
        Optional<Order> order = orderRepository.findByTrackID(trackId);
        if(order.isPresent()){
            return order.get().getOrderDto();
        }
        return null;
    }
}
