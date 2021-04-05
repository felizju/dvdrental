package com.funnydvd.dvdrental.cli.movie.repository;

import com.funnydvd.dvdrental.cli.movie.domain.Movie;
import com.funnydvd.dvdrental.cli.movie.domain.SearchCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMovieRepositoryTest {

    MovieRepository repository = new MemoryMovieRepository();

    @Test
    @DisplayName("영화를 저장요청하면 메모리 디비에 잘 저장되어야 한다.")
    void insertTest(){
        Movie newMovie = new Movie("nojam movie", "korea", 2016);

        repository.addMovie(newMovie);

        Movie findMovie = repository.searchMovieOne(newMovie.getSerialNumber());
        System.out.println("findMovie = " + findMovie);
    }



    @Test
    @DisplayName("전체 조회 시 모든 영화 정보가 출력되어야 한다.")
    void selectAll(){
        List<Movie> movieList = repository.searchMovieList("", SearchCondition.ALL);
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }

    @Test
    @DisplayName("영화 정보 삭제 시 해당 영화가 조회되면 안된다.")
    void removeTest(){
        repository.removeMovie(3);
        Movie movie = repository.searchMovieOne(3);
        System.out.println("movie = " + movie);
    }

    @Test
    @DisplayName("조건에 따라 검색결과가 리턴되어야 한다.")
    void searchTest(){

        repository.searchMovieList("", SearchCondition.ALL).forEach(movie -> System.out.println(movie));
        System.out.println("=======================================================");

        repository.searchMovieList("타이타닉", SearchCondition.TITLE).forEach(movie -> System.out.println(movie));
        /*for (Movie movie : repository.searchMovieList("타이타닉", SearchCondition.TITLE)) {
            System.out.println(movie);
        }*/
        System.out.println("=======================================================");


        repository.searchMovieList("대한민국", SearchCondition.NATION).forEach(movie -> System.out.println(movie));
        System.out.println("=======================================================");


        repository.searchMovieList("1994", SearchCondition.PUB_YEAR).forEach(movie -> System.out.println(movie));
        System.out.println("=======================================================");


        List<Movie> movieList = repository.searchMovieList("",SearchCondition.ALL);
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            if(i % 2 == 0){ // idx부터 시작 1,3,5,7,9 = rental
                movie.setRental(true);
            }
        }

        repository.searchMovieList("", SearchCondition.POSSIBLE).forEach(movie -> System.out.println(movie));
        System.out.println("=======================================================");


    }



}