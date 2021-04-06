package com.funnydvd.dvdrental.cli.user.domain;

import static com.funnydvd.dvdrental.cli.user.domain.Grade.*;

/**
 * 회원 등급 정책
 * 5000원 이상 결제 시 SILVER 등급
 * 7000원 이상 결제 시 GOLD 등급
 * 10000원 이상 결제 시 VIP 등급
 */
public class GradePolicy {

    // 상수
    private static final int VIP_PRICE = 10000;
    private static final int GOLD_PRICE = 7000;
    private static final int SILVER_PRICE = 5000;

    // 등급 조정 기능
    public static void changeGrade(User user){

        // 총 결제액에 따른 회원 등급 조정
        int totalPaying = user.getTotalPaying();

        if(totalPaying >= VIP_PRICE){
            user.setGrade(VIP);
        }else if(totalPaying >= GOLD_PRICE){
            user.setGrade(GOLD);
        }else if(totalPaying >= SILVER_PRICE){
            user.setGrade(SILVER);
        }else{
            return;
        }
    }

}
