package com.funnydvd.dvdrental.cli.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OverduePolicyTest {

    @Test
    @DisplayName("연체일수와 연체료가 정확하게 계산되어야 한다.")
    void overdueTest(){
        // 반납 예정일
        LocalDate returnDate = LocalDate.of(2021,2,7);

        int overdueDay = OverduePolicy.calcOverdueDay(returnDate);
        int overdueCharge = OverduePolicy.calcOverdueCharge(returnDate);
        System.out.println("overdueDay = " + overdueDay);
        System.out.println("overdueCharge = " + overdueCharge);
    }

}