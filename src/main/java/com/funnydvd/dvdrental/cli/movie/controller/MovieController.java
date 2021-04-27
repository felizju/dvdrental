package com.funnydvd.dvdrental.cli.movie.controller;

import com.funnydvd.dvdrental.cli.main.AppController;
import com.funnydvd.dvdrental.cli.movie.domain.Movie;
import com.funnydvd.dvdrental.cli.movie.domain.SearchCondition;
import com.funnydvd.dvdrental.cli.movie.repository.JdbcMovieRepository;
import com.funnydvd.dvdrental.cli.movie.repository.MemoryMovieRepository;
import com.funnydvd.dvdrental.cli.movie.repository.MovieRepository;

import java.util.List;

import static com.funnydvd.dvdrental.cli.movie.domain.SearchCondition.*;
import static com.funnydvd.dvdrental.cli.ui.AppUI.*;

// 영화관리 시스템의 분기를 제어
public class MovieController implements AppController {

    // 영화 저장소와 의존 관계 설정 (포함관계)
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
//        movieRepository = new MemoryMovieRepository();
//        movieRepository = new JdbcMovieRepository();
        this.movieRepository =  movieRepository; // 의존성 주입
    }


    // 제어 시작 기능
    public void start(){
        while (true) {

            movieManagementScreen();
            int selection = inputInteger(">>> ");

            switch (selection){
                case 1:
                    insertMovieData();
                    break;
                case 2:
                    searchMovieData();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\n# 메뉴를 다시 입력하세요!");
            }
        }
    }

    // 영화 정보 검색 기능
    private void searchMovieData() {
        showSearchConditionScreen();
        int selection = inputInteger(">>> ");

        SearchCondition condition = ALL;

        switch (selection){
            case 1:
                System.out.println("\n ## 제목으로 검색합니다.");
                condition = TITLE;
                break;
            case 2:
                System.out.println("\n ## 국가별로 검색합니다.");
                condition = NATION;
                break;
            case 3:
                System.out.println("\n ## 발매연도로 검색합니다.");
                condition = PUB_YEAR;
                break;
            case 4:
                System.out.println("\n ## 전체 검색합니다.");
                break;
            default:
                System.out.println("\n ## 잘못입력 했습니다.");

        }

        String keyword = "";
        if(condition != ALL){
            keyword = inputString("# 검색어 : ");
        }

        // 핵심로직
        List<Movie> movieList = movieRepository.searchMovieList(keyword, condition);


        int count = movieList.size();
        if (count > 0) {
            System.out.printf("\n================================검색 결과(총 %d건)===================================\n", count);
            for (Movie movie : movieList) {
                System.out.println(movie);
            }
        } else {
            System.out.println("\n### 검색 결과가 없습니다.");
        }
    }




    // 영화 정보 추가 기능
    private void insertMovieData() {

        System.out.println("\n====== 영화 DVD 정보를 추가합니다. ======");
        String movieName = inputString("# 영화명: ");
        String nation = inputString("# 국가명: ");
        int pubYear = inputInteger("# 발매연도: ");

        // 저장할 영화 객체 필요
        Movie newMovie = new Movie(movieName, nation, pubYear);

        // 저장명령
        movieRepository.addMovie(newMovie);
        System.out.printf("\n### [%s]의 정보가 정상 추가되었습니다.", newMovie.getMovieName());

//        movieRepository.searchMovieList("", SearchCondition.ALL).forEach(m -> System.out.println(m));

    }





}
