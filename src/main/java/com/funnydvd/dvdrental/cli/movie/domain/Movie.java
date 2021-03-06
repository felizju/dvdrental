package com.funnydvd.dvdrental.cli.movie.domain;

import com.funnydvd.dvdrental.cli.user.domain.Grade;
import com.funnydvd.dvdrental.cli.user.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

// 역할 : 이 클래스는 하나의 DVD 정보를 저장할 수 있어야 한다.
public class Movie {

    private int serialNumber; // dvd 식별 번호
    private String movieName; // dvd 영화명
    private String nation; // 영화 제작국가
    private int pubYear; // 영화 발매년도
    private int charge; // dvd 대여 금액
    private boolean rental; // dvd 대여 상태
    private User rentalUser; // 현재 대여자 정보

    // 일련번호 (static 으로 공유)
    private static int sequence;

    // 생성자
    // 받아와야 할 데이터 생각하기
    // serialNumber 자동부여, charge 대여금액 자동부여, rental 대여상태 자동변경
    public Movie(String movieName, String nation, int pubYear) {
        this.serialNumber = ++sequence;
        this.movieName = movieName;
        this.nation = nation;
        this.pubYear = pubYear;
        this.charge = ChargePolicy.calculateDvdCharge(this.pubYear);
    }


    // DB조회 데이터 처리 생성자
    public Movie(ResultSet rs) throws SQLException { // 예외처리는 호출한 곳에서 처리
        this.serialNumber = rs.getInt("serial_number");
        this.movieName = rs.getString("movie_name");
        this.nation = rs.getString("nation");
        this.pubYear = Integer.parseInt(rs.getString("pub_year"));
        this.charge = rs.getInt("charge");
        this.rental = rs.getString("rental").equals("T");
    }


    // getter & setter
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public boolean isRental() {
        return rental;
    }

    public void setRental(boolean rental) {
        this.rental = rental;
    }

    public static int getSequence() {
        return sequence;
    }

    public static void setSequence(int sequence) {
        Movie.sequence = sequence;
    }

    public User getRentalUser() {
        return rentalUser;
    }

    public void setRentalUser(User rentalUser) {
        this.rentalUser = rentalUser;
    }

    // toString
    @Override
    public String toString() {
        String rental = this.rental ? "대여중" : "대여가능";

        return String.format("### DVD번호: %s, 영화명: %s, " +
                        "국가명: %s, 발매연도: %d년, 대여료: %d원, 대여상태: %s"
                , serialNumber, movieName, nation, pubYear, charge, rental);
    }
}
