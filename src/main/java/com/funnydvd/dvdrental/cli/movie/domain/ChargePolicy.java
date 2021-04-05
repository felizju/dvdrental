package com.funnydvd.dvdrental.cli.movie.domain;

import java.time.LocalDateTime;

/**
 * 요금 정책
 * 1. 올해 출시된 영화는 기본적으로 5000원
 * 2. 출시 후 1년이 지날 때마다 500원씩 할인한다.
 * 3. 최소 요금은 2000원이며 그 밑으로는 할인되지 않는다.
 */
public class ChargePolicy {

    // 요금 관련 상수
    private static final int BASE_CHARGE = 5000;
    private static final int DISCOUNT = 500;
    private static final int LIMIT_CHARGE = 2000;


    // 영화 요금을 계산해서 리턴하는 기능
    public static int calculateDvdCharge(int pubYear){ // 인스턴트 필드 사용 안 했으므로 static
        // 올해 연도 정보
        int thisYear = LocalDateTime.now().getYear();
        // 출시 후 경과 연도
        int movieAge = thisYear - pubYear;

        // 요금 계산
        int charge = BASE_CHARGE - (movieAge * DISCOUNT);

        if(charge < LIMIT_CHARGE){
            return LIMIT_CHARGE;
        }
        return charge;
    }

}
