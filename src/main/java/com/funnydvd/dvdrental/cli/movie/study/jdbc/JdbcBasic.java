package com.funnydvd.dvdrental.cli.movie.study.jdbc;

// db관련 패키지 : java.sql

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcBasic {

    // 데이터베이스 연결 접속 정보
    private String id = "sqld";
    private String pw = "1234";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // db서버 위치:1521
    private String driverClassName = "oracle.jdbc.driver.OracleDriver"; // 오라클 연동클래스 이름

    // INSERT 문을 실행하는 메서드
    public boolean save(JdbcPractice jp){

        Connection connection = null;
        try {
            // 드라이버 클래스 로딩
            Class.forName(driverClassName);

            // 연결 정보 객체 생성
            connection = DriverManager.getConnection(url,this.id,pw);

            // SQL 실행
            String query = "INSERT INTO jdbc_practice VALUES (?, ?, ?)";

            // SQL실행을 위한 PreparedStatement 객체 필요
            PreparedStatement statement = connection.prepareStatement(query);

            // 쿼리의 ? 값 세팅
            statement.setInt(1, jp.getId());
            statement.setString(2, jp.getName());
            statement.setString(3, jp.getAddr());

            // SQL 실행 명령
            // SELECT : executeQuery()
            // INSERT, UPDATE, DELETE : executeUpdate()
            // resultNum : 성공하면 1, 실패하면 0
            int resultNum = statement.executeUpdate();
            return resultNum == 1;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            // DB 접속 해제 : 빈번한 데이터베이스 트랜잭선이 생길 때마다 메모리 과부하가 걸릴 가능성이 있음
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // UPDATE 문을 실행하는 메서드
    public boolean modify(JdbcPractice jp){

        // try with resource : 자원 해제를 자동처리 (Auto Closable)
        // 자원 해제를 자동으로 클로즈 해줌
        try(Connection connection = DriverManager.getConnection(url,this.id,pw)) {

            // 드라이버 클래스 로딩
            Class.forName(driverClassName);

            // SQL 실행
            String query = "UPDATE jdbc_practice SET name = ?, addr = ?  WHERE id = ?";

            // SQL실행을 위한 PreparedStatement 객체 필요
            PreparedStatement statement = connection.prepareStatement(query);

            // 쿼리의 ? 값 세팅
            statement.setString(1, jp.getName());
            statement.setString(2, jp.getAddr());
            statement.setInt(3, jp.getId());

            // SQL 실행 명령
            // SELECT : executeQuery()
            // INSERT, UPDATE, DELETE : executeUpdate()
            // resultNum : 성공하면 1, 실패하면 0
            int resultNum = statement.executeUpdate();
            return resultNum == 1;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



    // DELETE 문을 실행하는 메서드
    public boolean remove(int id){

        // try with resource : 자원 해제를 자동처리 (Auto Closable)
        // 자원 해제를 자동으로 클로즈 해줌
        try(Connection connection = DriverManager.getConnection(url,this.id,pw)) {

            // 드라이버 클래스 로딩
            Class.forName(driverClassName);

            // SQL 실행
            String query = "DELETE FROM jdbc_practice WHERE id = ?";

            // SQL실행을 위한 PreparedStatement 객체 필요
            PreparedStatement statement = connection.prepareStatement(query);

            // 쿼리의 ? 값 세팅
            statement.setInt(1, id);

            // SQL 실행 명령
            // SELECT : executeQuery()
            // INSERT, UPDATE, DELETE : executeUpdate()
            // resultNum : 성공하면 1, 실패하면 0
            int resultNum = statement.executeUpdate();
            return resultNum == 1;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    // 다중행 SELECT
    public List<JdbcPractice> findAll(){

        List<JdbcPractice> resultList = new ArrayList<>();

        // try with resource : 자원 해제를 자동처리 (Auto Closable)
        try(Connection connection = DriverManager.getConnection(url,this.id,pw)) {

            // 드라이버 클래스 로딩
            Class.forName(driverClassName);

            // SQL 실행
            String query = "SELECT * FROM jdbc_practice ORDER BY 1";

            // SQL실행을 위한 PreparedStatement 객체 필요
            PreparedStatement statement = connection.prepareStatement(query);

            // SQL 실행 명령
            // SELECT : executeQuery()
            // INSERT, UPDATE, DELETE : executeUpdate()
            // ResultSet : SELECT 결과 2차원 테이블 (표)
            ResultSet rs = statement.executeQuery();
             
            // 다음 행 커서 이동
            while(rs.next()){ // 데이터가 조회되지 않을 때까지 반복
                JdbcPractice jb = new JdbcPractice(rs.getInt("id"), rs.getString("name"), rs.getString("addr"));

                resultList.add(jb);
            }
           return resultList;

        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList(); // 빈 리스트 리턴
        }
    }

    // 단일행 SELECT
    public JdbcPractice fineOne(int id){

        // try with resource : 자원 해제를 자동처리 (Auto Closable)
        try(Connection connection = DriverManager.getConnection(url,this.id,pw)) {

            // 드라이버 클래스 로딩
            Class.forName(driverClassName);

            // SQL 실행
            String query = "SELECT * FROM jdbc_practice WHERE id = ?";

            // SQL실행을 위한 PreparedStatement 객체 필요
            PreparedStatement statement = connection.prepareStatement(query);

            // ? 객체
            statement.setInt(1, id);

            // SQL 실행 명령
            // SELECT : executeQuery()
            // INSERT, UPDATE, DELETE : executeUpdate()
            // ResultSet : SELECT 결과 2차원 테이블 (표)
            ResultSet rs = statement.executeQuery();

            // 다음 행 커서 이동
            if(rs.next()){
               return new JdbcPractice(rs.getInt("id"), rs.getString("name"), rs.getString("addr"));

            }else{
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 테이블 행수 count 조회 : SELECT COUNT(*) FROM jdbc_practice;
    public int getCount(){

        // try with resource : 자원 해제를 자동처리 (Auto Closable)
        try(Connection connection = DriverManager.getConnection(url,this.id,pw)) {

            // 드라이버 클래스 로딩
            Class.forName(driverClassName);

            // SQL 실행
            String query = "SELECT COUNT(*) AS cnt FROM jdbc_practice"; // 별칭으로 지정

            // SQL실행을 위한 PreparedStatement 객체 필요
            PreparedStatement statement = connection.prepareStatement(query);


            // SQL 실행 명령
            // SELECT : executeQuery()
            // INSERT, UPDATE, DELETE : executeUpdate()
            // ResultSet : SELECT 결과 2차원 테이블 (표)
            ResultSet rs = statement.executeQuery();

            // 다음 행 커서 이동
            if(rs.next()){
                return  rs.getInt("cnt");
            }else{
                return -1;
            }

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    
    
}
