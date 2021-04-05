package com.funnydvd.dvdrental.cli.movie.study.anonymous;

public class Main {

    public static void main(String[] args) {
        
        // implements 없이 사용 - Anonymous 클래스 (익명클래스)
        Car sonata = new Car() {
            @Override
            public void run() {
                System.out.println("소나타가 주행합니다.");
            } // end method
        }; // end Anonymous class


        Driver driver = new Driver();
        driver.drive(sonata);

        driver.drive(new Car(){
            @Override
            public void run() {
                System.out.println("페라리가 주행합니다.");
            }
        });

        // 람다식(Function Interface)
        // 조건 : 인터페이스에 추상메서드가 단 하나여야 함.
//        Car morning = new Car(){ // new Car 생략
        Car morning = () ->  System.out.println("모닝이 주행합니다.");

        morning.run();
        driver.drive(() -> System.out.println("그랜저가 주행합니다."));

//        sonata.run();

        /////////////////////////////////////////////////////////////////////////
        System.out.println("===================================================");

        // 1.람다 함수
        Calculator addOper = (n1,n2) -> n1 + n2; // 한줄로 사용 가능
            // return n1+n2;
        // };
        Calculator multiOper = (a,b) -> a * b;
        System.out.println(addOper.operator(10,20));
        System.out.println(multiOper.operator(10,20));

        // 2.익명 함수
        Calculator addOper2 = new Calculator(){
            @Override
            public int operator(int n1, int n2) {
                return n1 + n2;
            }
        };




    } // end main
} // end class
