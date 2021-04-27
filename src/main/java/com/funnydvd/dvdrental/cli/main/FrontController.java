package com.funnydvd.dvdrental.cli.main;

import com.funnydvd.dvdrental.cli.config.DiContainer;
import com.funnydvd.dvdrental.cli.movie.controller.MovieController;
import com.funnydvd.dvdrental.cli.order.controller.OrderController;
import com.funnydvd.dvdrental.cli.user.controller.UserController;

// 역할 : 시스템 분기를 결정해주는 클래스
public class FrontController {

    private static AppController appController; // 의존성 추가

    // 분기 결정 기능
    public static void chooseSystem(int selection){


        DiContainer dc = new DiContainer();

        switch (selection){
            case 1:
                appController = dc.userController();
                break;
            case 2:
                appController = dc.orderController();
                break;
            case 3:
                appController = dc.movieController();
                break;
            case 4:
                System.out.println("프로그램 종료");
                System.exit(0);
            default:
                System.out.println("\n### 메뉴를 잘못 선택했습니다.");
                return;
        }// end switch

        appController.start();

    }


}
