package com.funnydvd.dvdrental.cli.order.repository;

import com.funnydvd.dvdrental.cli.order.domain.Order;

public interface OrderRepository {

    // 대여 주문 저장 기능
    void saveOrder(Order order);

    // 반납 처리 기능
    void returnOrder(Order order);

}
