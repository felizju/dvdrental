package com.funnydvd.dvdrental.cli.movie.repository;

import com.funnydvd.dvdrental.cli.movie.domain.Movie;
import com.funnydvd.dvdrental.cli.movie.domain.SearchCondition;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryMovieRepository implements MovieRepository {

    // 영화 정보들을 저장할 자료구조
    // Map<key,value> Map<getSerialNumber(), Movie>
    private static final Map<Integer, Movie> movieMemoryDB = new HashMap<>();

    // static 초기화 (자료 저장하기)
    static {
        insertTestData();
    }

    // 기초 자료 저장
    private static void insertTestData() {
        // 각 객체 생성
        Movie movie1 = new Movie("인터스텔라", "미국", 2014);
        Movie movie2 = new Movie("포레스트 검프", "미국", 1994);
        Movie movie3 = new Movie("너의 이름은", "일본", 2017);
        Movie movie4 = new Movie("라라랜드", "미국", 2016);
        Movie movie5 = new Movie("레옹", "프랑스", 1994);
        Movie movie6 = new Movie("어바웃 타임", "영국", 2013);
        Movie movie7 = new Movie("타이타닉", "미국", 1998);
        Movie movie8 = new Movie("인생은 아름다워", "이탈리아", 1999);
        Movie movie9 = new Movie("쇼생크 탈출", "미국", 1995);
        Movie movie10 = new Movie("기생충", "대한민국", 2019);
        
        // movieMemoryDB.put(key, value)
        movieMemoryDB.put(movie1.getSerialNumber(), movie1);
        movieMemoryDB.put(movie2.getSerialNumber(), movie2);
        movieMemoryDB.put(movie3.getSerialNumber(), movie3);
        movieMemoryDB.put(movie4.getSerialNumber(), movie4);
        movieMemoryDB.put(movie5.getSerialNumber(), movie5);
        movieMemoryDB.put(movie6.getSerialNumber(), movie6);
        movieMemoryDB.put(movie7.getSerialNumber(), movie7);
        movieMemoryDB.put(movie8.getSerialNumber(), movie8);
        movieMemoryDB.put(movie9.getSerialNumber(), movie9);
        movieMemoryDB.put(movie10.getSerialNumber(), movie10);
    }

    @Override
    public void addMovie(Movie movie) { // dvd생성
        // movieMemoryDB.put(key, value)
        movieMemoryDB.put(movie.getSerialNumber(), movie);
    }

    @Override
    public List<Movie> searchMovieList(String keyword, SearchCondition condition) { // dvd검색 - 조건별

        // 호출 부에 전달할 검색데이터 리스트
        List<Movie> results = null;

        switch (condition){
            case TITLE:
//                results = searchByTitle(keyword);
                results = search(keyword, (k, m) -> k.equals(m.getMovieName()));
                break;
            case NATION:
//                results = searchByNation(keyword);
                results = search(keyword, (k, m) -> k.equals(m.getNation()));
                break;
            case PUB_YEAR:
//                results = searchByPubYear(keyword);
                results = search(keyword, (k, m) -> Integer.parseInt(k) == m.getPubYear());
                break;
            case ALL:
//                results = searchAll();
                results = search(keyword, (k, m) -> true); // true주면 전부 다 리턴 - 전체 리스트
                break;
            case POSSIBLE:
                results = search(keyword, (k, m) -> !m.isRental());
                break;
            default:
                return null;
        }
        return results;
    }

    
    // 공통코드 - 콜백 함수로 정규화 시키기
    private List<Movie> search(String keyword, MoviePredicate mp){
        List<Movie> movieList = new ArrayList<>();
        for (Integer key : movieMemoryDB.keySet()) {
            Movie movie = movieMemoryDB.get(key);

            // 검색 키워드와 발매연도 일치하는 movie만 리스트에 추가
            if(mp.test(keyword, movie)){
                movieList.add(movie);
            }
        }
        return movieList;
    }

/*

    // 발매연도로 검색
    private List<Movie> searchByPubYear(String keyword) {
        List<Movie> movieList = new ArrayList<>();
        for (Integer key : movieMemoryDB.keySet()) {
            Movie movie = movieMemoryDB.get(key);

            // 검색 키워드와 발매연도 일치하는 movie만 리스트에 추가 (중복)
            if(Integer.parseInt(keyword) == movie.getPubYear()){
                movieList.add(movie);
            }
        }
        return movieList;
    }

    // 국가별로 검색
    private List<Movie> searchByNation(String keyword) {
        List<Movie> movieList = new ArrayList<>();
        for (Integer key : movieMemoryDB.keySet()) {
            Movie movie = movieMemoryDB.get(key);

            // 검색 키워드와 국가가 일치하는 movie만 리스트에 추가 (중복)
            if(keyword.equals(movie.getNation())){
                movieList.add(movie);
            }
        }
        return movieList;
    }


    // 제목으로 검색
    private List<Movie> searchByTitle(String keyword) {
        List<Movie> movieList = new ArrayList<>();
        for (Integer key : movieMemoryDB.keySet()) {
            Movie movie = movieMemoryDB.get(key);

            // 검색 키워드와 제목이 일치하는 movie만 리스트에 추가 (중복)
            if(keyword.equals(movie.getMovieName())){
                movieList.add(movie);
            }
        }
        return movieList;
    }

    // 전체 검색
    private List<Movie> searchAll() {
        List<Movie> movieList = new ArrayList<>();

        for (int key : movieMemoryDB.keySet()) {
            Movie movie = movieMemoryDB.get(key); // get(key)로 value 반환
            movieList.add(movie);
        }
        return movieList;
    }
*/


    @Override
    public Movie searchMovieOne(int serialNumber) { // dvd 검색 - 1개
        return movieMemoryDB.get(serialNumber); // get(key)로 value 반환
    }


    @Override
    public void removeMovie(int serialNumber) { // dvd 삭제
        // movieMemoryDB.remove(key)
        movieMemoryDB.remove(serialNumber);
    }


    // 영화 검색 조건을 위한 인터페이스 (내부)
    @FunctionalInterface // 람다식 가능한지 오류 확인해줌
    interface MoviePredicate{
        boolean test(String keyword, Movie movie);
    }
}
