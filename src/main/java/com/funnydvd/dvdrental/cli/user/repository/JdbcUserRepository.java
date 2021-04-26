package com.funnydvd.dvdrental.cli.user.repository;

import com.funnydvd.dvdrental.cli.movie.study.anonymous.Driver;
import com.funnydvd.dvdrental.cli.user.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 회원 데이터를 데이터베이스에 CRUD 하는 DB 접근 클래스
public class JdbcUserRepository implements UserRepository{

    // 데이터베이스 연결 접속 정보
    private String id = "java_web2";
    private String pw = "202104";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driverClassName = "oracle.jdbc.driver.OracleDriver";

    @Override
    public void addUser(User user) {

        try(Connection conn = DriverManager.getConnection(url, id, pw)){
            Class.forName(driverClassName);

            String sql = "INSERT INTO dvd_user (user_number, user_name, phone_number) VALUES (seq_dvd_user.nextval, ?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getUserName());
            pstmt.setString(2,user.getPhoneNumber());

            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAllByName(String userName) {

        List<User> userList = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, id, pw)){
            Class.forName(driverClassName);

            String sql = "SELECT * FROM dvd_user WHERE user_name = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                userList.add(new User(rs)); // 한줄한줄 꺼내와서 userList add 반복 처리
            }
            return userList;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User findByUserNumber(int userNumber) {

        try(Connection conn = DriverManager.getConnection(url, id, pw)){
            Class.forName(driverClassName);

            String sql = "SELECT * FROM dvd_user WHERE user_number = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNumber);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
              return new User(rs);
              // ResultSet (조회결과로 생성자 호출)
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User deleteUser(int userNumber) {

        Connection conn = null;

        try{
            conn = DriverManager.getConnection(url, id, pw);
            Class.forName(driverClassName);
            
            // 트랜잭션 처리
            conn.setAutoCommit(false);

            String sql = "DELETE FROM dvd_user WHERE user_number = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNumber);

            User delUser = findByUserNumber(userNumber); // SELECT 쿼리

            int delResult = pstmt.executeUpdate();
            if(delResult == 1){
                conn.commit(); // 트랜잭션이 성공했을 경우만 커밋
                return delUser;
            }

        }catch (Exception e){
            try {
                conn.rollback(); // 트랜잭션 실패시 롤백
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try{
                if(conn != null) conn.close();
            }catch (Exception e) {}
        }
        return null;
    }
}
