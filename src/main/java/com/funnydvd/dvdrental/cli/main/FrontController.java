package com.funnydvd.dvdrental.cli.main;

import com.funnydvd.dvdrental.cli.movie.controller.MovieController;

// 역할 : 시스템 분기를 결정해주는 클래스
public class FrontController {




    // 분기 결정 기능
    public static void chooseSystem(int selection){

        switch (selection){
            case 1:
                System.out.println("# 회원 관리 시스템을 시작합니다.");
                break;
            case 2:
                System.out.println("# 주문 관리 시스템을 시작합니다.");
                break;
            case 3:
                System.out.println("# 영화 관리 시스템을 시작합니다.");
                MovieController movieController = new MovieController();
                movieController.start();
                break;
            case 4:
                System.out.println("프로그램 종료");
                System.exit(0);
            default:
                System.out.println("\n### 메뉴를 잘못 선택했습니다.");
        }
    }


}
