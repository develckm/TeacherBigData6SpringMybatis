package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.PagingDto;

import java.util.List;

public interface CRUD<T,P> {
    List<T> findAll();
    List<T> findPaging(PagingDto paging); //where name like '%a%'
    int count(PagingDto paging); //검색기능 추가시 where 절에 파라미터를 받으려고..
    //~~~~ LIMIT startRow,rows ORDER BY name desc  매개변수가 많아지기 때문에 pagingDto를 정의해서 사용
    T findById(P id);
    int deleteById(P id);
    int updateById(T dto);
    int insert(T dto);
}
