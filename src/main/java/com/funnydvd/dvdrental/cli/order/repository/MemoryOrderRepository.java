package com.funnydvd.dvdrental.cli.order.repository;

import com.funnydvd.dvdrental.cli.movie.domain.Movie;
import com.funnydvd.dvdrental.cli.order.domain.Order;
import com.funnydvd.dvdrental.cli.order.domain.OrderStatus;
import com.funnydvd.dvdrental.cli.user.domain.User;

import java.util.HashMap;
import java.util.Map;

public class MemoryOrderRepository implements OrderRepository {

    private static final Map<Integer, Order> orderDatabase = new HashMap<>();

    @Override
    public void saveOrder(Order order) {

        Movie rentalMovie = order.getMovie();
        User rentalUser = order.getUser();

        // 영화 연계 정보
        rentalMovie.setRental(true);
        rentalMovie.setRentalUser(rentalUser);

        // 회원 연계 정보
        rentalUser.setTotalPaying(rentalMovie.getCharge()); // 객체 간 소통
        rentalUser.addOrder(order);

        orderDatabase.put(order.getOrderNumber(), order); // 객체간 대화
    }

    @Override
    public void returnOrder(Order order) {
        Movie retrunMovie = order.getMovie();
        User returnUser = order.getUser();

        // 반납 영화 연계정보 처리
        retrunMovie.setRental(false);
        retrunMovie.setRentalUser(null);

        // 반납 회원 연계 정보 처리
        returnUser.removeOrder(retrunMovie.getSerialNumber());

        orderDatabase.get(order.getOrderNumber()).setOrderStatus(OrderStatus.RETURNED);

    }
}
