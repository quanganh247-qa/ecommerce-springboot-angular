package com.programming.ecommerce.repository;

import com.programming.ecommerce.models.Order;
import com.programming.ecommerce.models.enums.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByUserIdAndOrderStatus(String userId, OrderStatus orderStatus);
    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatuses);

    List<Order> findByUserIdAndOrderStatusIn(String userId, List<OrderStatus> orderStatuses);

    Optional<Order> findByTrackID(UUID trackID);

    List<Order> findByOrderDateBetweenAndOrderStatus(Date startDate, Date endDate, OrderStatus status);

    Long countByOrderStatus(OrderStatus orderStatus);


}
