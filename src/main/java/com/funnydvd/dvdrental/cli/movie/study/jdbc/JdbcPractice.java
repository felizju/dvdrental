package com.funnydvd.dvdrental.cli.movie.study.jdbc;

// 도메인 엔터티 객체
// DB의 jdbc_practice 엔터티를 연동하는 클래스
public class JdbcPractice {

    // 컬럼과 1:1로 매칭되는 필드를 선언
    private int id;
    private String name;
    private String addr;


    public JdbcPractice(int id, String name, String addr) {
        this.id = id;
        this.name = name;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "JdbcPractice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
