package com.funnydvd.dvdrental.cli.order.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 연체 정책
 * 1. 반납예정일로부터 1잃 연체당 300원씩 연체료 징수
 */
public class OverduePolicy {

    private static final int BASE_OVERDUE_CHARGE = 300;

    // 연체 일수 계산
    public static int calcOverdueDay(LocalDate returnDay){

        // 현재 날짜
        LocalDate now = LocalDate.now();

        if(returnDay.isBefore(now)){
            return (int) ChronoUnit.DAYS.between(returnDay, now);
        }
            return 0;
    }

    // 연체료 계산
    public static int calcOverdueCharge(LocalDate returnDay){
//        return  연체 일수 * BASE_OVERDUE_CHARGE;
        return calcOverdueDay(returnDay) * BASE_OVERDUE_CHARGE;
    }

}
