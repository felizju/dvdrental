package com.funnydvd.dvdrental.cli.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

// 역할 : 화면 출력 텍스트 모음 클래스
public abstract class AppUI {

    private static Scanner sc = new Scanner(System.in);
    //입력 유틸메서드

    //정수 입력 메서드
    public static int inputInteger(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("정수로 입력해주세요!");
            } finally {
                sc.nextLine(); // nextInt \n 제거
            }
        }
    }

    //문자열 입력 메서드
    public static String inputString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }


    //시작화면 출력
    public static void startScreen() {
        System.out.println("\n========= DVD 대여 관리 시스템 =========");
        System.out.println("### 1. 회원 관리 시스템");
        System.out.println("### 2. 대여 주문 관리 시스템");
        System.out.println("### 3. 영화 DVD 관리 시스템");
        System.out.println("### 4. 프로그램 종료");
        System.out.println("----------------------------------------");
    }

    //회원관리 시스템 화면 출력
    public static void userManagementScreen() {
        System.out.println("\n========= 회원 관리 시스템 =========");
        System.out.println("### 1. 신규 회원 추가");
        System.out.println("### 2. 회원 검색");
        System.out.println("### 3. 회원 탈퇴");
        System.out.println("### 4. 회원 전체 검색");
        System.out.println("### 5. 첫 화면으로 가기");
        System.out.println("----------------------------------------");
    }

    //대여 주문 관리 시스템 화면 출력
    public static void orderManagementScreen() {
        System.out.println("\n========= 대여 주문 관리 시스템 =========");
        System.out.println("### 1. 영화 DVD 대여하기");
        System.out.println("### 2. 영화 DVD 반납하기");
        System.out.println("### 3. 첫 화면으로 가기");
        System.out.println("----------------------------------------");
    }

    //영화관리 시스템 화면 출력
    public static void movieManagementScreen() {
        System.out.println("\n========= 영화 DVD 관리 시스템 =========");
        System.out.println("### 1. 신규 영화 DVD 추가");
        System.out.println("### 2. 영화 DVD 정보 검색");
        System.out.println("### 3. 첫 화면으로 가기");
        System.out.println("----------------------------------------");
    }

    //영화 검색 조건 화면
    public static void showSearchConditionScreen() {
        System.out.println("\n============== 영화 DVD 검색 조건을 선택하세요. ===============");
        System.out.println("[ 1. 제목검색 | 2. 국가검색 | 3. 발매연도검색 | 4. 전체검색 ]");
    }
    
}
