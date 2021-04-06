package com.funnydvd.dvdrental.cli.user.repository;

import com.funnydvd.dvdrental.cli.user.domain.User;

import java.util.List;

public interface UserRepository {

    // 회원가입
    void addUser(User user);

    // 회원 목록 조회
    List<User> findAllByName(String userName);

    // 회원 개별 조회
    User findByUserNumber(int userNumber);

    // 회원탈퇴
    User deleteUser(int userNumber);


}
