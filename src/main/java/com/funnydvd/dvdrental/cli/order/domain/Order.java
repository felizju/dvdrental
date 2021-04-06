package com.funnydvd.dvdrental.cli.order.domain;

import com.funnydvd.dvdrental.cli.movie.domain.Movie;
import com.funnydvd.dvdrental.cli.user.domain.User;

import java.time.LocalDate;

import static com.funnydvd.dvdrental.cli.order.domain.OrderStatus.*;

public class Order {

    private static int sequence;

    private int orderNumber; // 주문번호
    private User user; // 주문자 정보

    // 주문 1번당 영화 1개 - 주문 1번당 여러개 영화라면 List<Movie>로 해야함.
    private Movie movie; // 대여 영화 정보
    private LocalDate orderDate; // 대여날짜
    private LocalDate returnDate; // 반납 예정 날짜
    private int overdueDay; // 연체일수
    private int overdueCharge; // 연체요금
    private OrderStatus orderStatus; // 대여상태 (대여중, 반납완료)

    // 생성자
    public Order(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        this.orderNumber = ++sequence;
        this.orderDate = LocalDate.now();
        this.returnDate = orderDate.plusDays(3); // 연체일
        this.orderStatus = ORDERED;
    }

    // getter & setter
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(int overdueDay) {
        this.overdueDay = overdueDay;
    }

    public int getOverdueCharge() {
        return overdueCharge;
    }

    public void setOverdueCharge(int overdueCharge) {
        this.overdueCharge = overdueCharge;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    @Override
    public String toString() {

        return
                "## 대여영화번호: " + movie.getSerialNumber() +
                        ", 대여영화명: " + movie.getMovieName() +
                        ", 회원명: " + user.getUserName() + "(" + user.getPhoneNumber() + ")" +
                        ", 대여일자: " + orderDate +
                        ", 반납일자: " + returnDate +
                        ", 연체금액(" + overdueDay + "일): "  + overdueCharge + "원"
                ;
    }

    // 반납 시점에 연체료 계산
    public void checkOverdue() {
        this.overdueDay = OverduePolicy.calcOverdueDay(this.returnDate);
        this.overdueCharge = OverduePolicy.calcOverdueCharge(this.returnDate);
    }
}
