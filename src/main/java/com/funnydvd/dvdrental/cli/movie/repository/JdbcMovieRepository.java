package com.funnydvd.dvdrental.cli.movie.repository;

import com.funnydvd.dvdrental.cli.movie.domain.Movie;
import com.funnydvd.dvdrental.cli.movie.domain.SearchCondition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieRepository implements MovieRepository {

    // 데이터베이스 연결 접속 정보
    private String id = "java_web2";
    private String pw = "202104";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driverClassName = "oracle.jdbc.driver.OracleDriver";


    // 영화추가
    @Override
    public void addMovie(Movie movie) {
        try(Connection conn = DriverManager.getConnection(url, id, pw)){
            Class.forName(driverClassName);

            String sql = "INSERT INTO dvd_movie (serial_number, movie_name, nation, pub_year, charge) " +
                    "VALUES (seq_dvd_movie.nextval, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, movie.getMovieName());
            pstmt.setString(2, movie.getNation());
            pstmt.setString(3, ""+movie.getPubYear()); // 빈문자열 더하면 String 변환
            pstmt.setInt(4, movie.getCharge());

            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 영화검색 - 조건별
    @Override
    public List<Movie> searchMovieList(String keyword, SearchCondition condition) { // 타이만 검색해도 타이타닉 검색되도록!

        List<Movie> movieList = new ArrayList<>();
        keyword = keyword.trim(); // 앞뒤 공백 제거

        try(Connection conn = DriverManager.getConnection(url, id, pw)){
            Class.forName(driverClassName);

            String sql = "SELECT * FROM dvd_movie ";
            // 해당 sql에 아래 조건에 따라 where절 +=으로 덧붙이기

            switch (condition) {
                case TITLE:
                    sql += "WHERE movie_name LIKE ?";
                    keyword = "%" + keyword + "%";
                    break;
                case NATION:
                    sql += "WHERE nation LIKE ?";
                    keyword = "%" + keyword + "%";
                    break;
                case PUB_YEAR:
                    sql += "WHERE pub_year LIKE ?";
                    keyword = "%" + keyword + "%";
                    break;
                case ALL:
                    break;
                case POSSIBLE:
                    sql += "WHERE rental = ?";
                    keyword = "F";
                    break;
                default:
                    return null;
            }

            PreparedStatement pstmt = conn.prepareStatement(sql);

            if(condition != SearchCondition.ALL){
                pstmt.setString(1, keyword);
            }

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                movieList.add(new Movie(rs));
            }
            return movieList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    // 영화검색 - 단일행
    @Override
    public Movie searchMovieOne(int serialNumber) {
        try(Connection conn = DriverManager.getConnection(url, id, pw)){
            Class.forName(driverClassName);

            String sql = "SELECT * FROM dvd_movie WHERE serial_number = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, serialNumber);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return new Movie(rs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    // 영화삭제
    @Override
    public void removeMovie(int serialNumber) {
        try(Connection conn = DriverManager.getConnection(url, id, pw)){
            Class.forName(driverClassName);

            String sql = "DELETE FROM dvd_movie WHERE serial_number = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, serialNumber);

            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
