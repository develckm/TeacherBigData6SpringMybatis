package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.PagingDto;

import java.util.List;

public interface CRUD<T,P> {
    List<T> findAll() throws Exception;
    List<T> findPaging(PagingDto paging) throws Exception; //where name like '%a%'
    int count(PagingDto paging) throws Exception; //검색기능 추가시 where 절에 파라미터를 받으려고..
    //~~~~ LIMIT startRow,rows ORDER BY name desc  매개변수가 많아지기 때문에 pagingDto를 정의해서 사용
    T findById(P id) throws Exception;
    int deleteById(P id) throws Exception;
    int updateById(T dto) throws Exception;
    int insert(T dto) throws Exception;
}
