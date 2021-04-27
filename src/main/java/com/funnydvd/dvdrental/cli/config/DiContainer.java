package com.funnydvd.dvdrental.cli.config;

import com.funnydvd.dvdrental.cli.movie.controller.MovieController;
import com.funnydvd.dvdrental.cli.movie.repository.JdbcMovieRepository;
import com.funnydvd.dvdrental.cli.movie.repository.MemoryMovieRepository;
import com.funnydvd.dvdrental.cli.movie.repository.MovieRepository;
import com.funnydvd.dvdrental.cli.order.controller.OrderController;
import com.funnydvd.dvdrental.cli.order.repository.MemoryOrderRepository;
import com.funnydvd.dvdrental.cli.user.controller.UserController;
import com.funnydvd.dvdrental.cli.user.repository.JdbcUserRepository;
import com.funnydvd.dvdrental.cli.user.repository.MemoryUserRepository;

// 역할 : 객체를 생성해서 필요에 맞는 구현체를 주입해주는 책임을 가진 클래스
public class DiContainer {

    // MovieRepository Bean 등록
    public MovieRepository movieRepository(){
        return new JdbcMovieRepository();
//        return new MemoryMovieRepository(); // 원하는대로 한번만 변경하면 됨
    }

    // Movie Controller 에 의존성 주입
    public MovieController movieController(){
        return new MovieController(movieRepository());
    }


    // Order Controller 에 의존성 주입
    public OrderController orderController(){
        return new OrderController(
                new JdbcUserRepository(),
                movieRepository(),
                new MemoryOrderRepository()
        );
    }

    // User Controller 에 의존성 주입
    public UserController userController(){
        return new UserController(new JdbcUserRepository());
    }


}
