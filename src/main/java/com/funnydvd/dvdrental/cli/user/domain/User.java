package com.funnydvd.dvdrental.cli.user.domain;

import com.funnydvd.dvdrental.cli.order.domain.Order;

import java.util.HashMap;
import java.util.Map;

import static com.funnydvd.dvdrental.cli.user.domain.Grade.*;

public class User {

    // 필드
    private static int sequence; // 회원 순차번호

    private int userNumber; // identifier
    private String userName; // 회원명
    private String phoneNumber; // 전화번호
    private int totalPaying; // 누적결제액
    private Grade grade; // 회원등급

    //현재 대여중인 목록 (영화시리얼넘버: K, 주문: V)
    // 주문을 받았을 때 연계로 변경될 값들을 생각해야함.
    private final Map<Integer, Order> orderMap = new HashMap<>();


    // 생성자 (외부에서 받아서 넣어야 하는 값만)
    public User(String userName, String phoneNumber) {
        this.userNumber = ++sequence;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.grade = BRONZE;
    }

    // orderMap 관리 메서드
    // 대여 주문 추가 기능
    public void addOrder(Order order){
        orderMap.put(order.getMovie().getSerialNumber(), order);
    }
    // dvd 반납 시 대여 주문 제거
    public Order removeOrder(int serialNumber){
        return orderMap.remove(serialNumber);
    }
    // 영화 시리얼 넘버로 특정 대여 주문 정보 얻기
    public Order getOrder(int serialNumber){
        return orderMap.get(serialNumber);
    }

    //회원의 전체 대여중인 주문 정보 얻기
    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }


    // setter & getter
    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTotalPaying() {
        return totalPaying;
    }

    public void setTotalPaying(int charge) {
        this.totalPaying += charge;
        GradePolicy.changeGrade(this); // 나의 등급 조정 this
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }


    
    // toString 오버라이드
    @Override
    public String toString() {
        return  "## 회원번호: " + userNumber +
                ", 회원명: " + userName +
                ", 전화번호: " + phoneNumber  +
                ", 총 결제금액: " + totalPaying + "원" +
                ", 등급: " + grade;
    }


}
