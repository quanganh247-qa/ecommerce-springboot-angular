package com.programming.ecommerce.service.admin.adminorder;

import com.programming.ecommerce.payload.request.OrderDto;
import com.programming.ecommerce.payload.response.AnalyticsResponse;

import java.util.List;

public interface AdminOrderService {

    List<OrderDto> getAllPlacedOrders();

    OrderDto changeOrderStatus(String orderId, String status);

    AnalyticsResponse calculateAnalytics();

    Long getTotalOrdersForMonth(int month, int year);

    Long getTotalEarningsForMonth(int month, int year);
}
