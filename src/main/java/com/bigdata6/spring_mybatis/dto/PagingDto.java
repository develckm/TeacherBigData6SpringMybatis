package com.bigdata6.spring_mybatis.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Data
public class PagingDto {
    private int page=1;

    private int rows=10;
    private int startRow;

    private String orderField;
    private String direct="DESC";
    private int totalRows; //레코드 전체 수
    private int totalPages;//페이지 전체 수
    private boolean prev;//현재페이지가 1이면 false
    private boolean next;//현재페이지가 마지막이면 false
    private int prevPage;
    private int nextPage;
    private int pageCount=5;//화면에 보일 페이지 수  5 6 [7] 8 9 , 1 [2] 3 4 5 ,  8 9 10 11 [12]
    private  int startPage;
    private int endPage;
    private String queryString;

    public PagingDto() { //dto를 컨트롤러의 파라터로 사용할때 생성에 정의된 필드가 required=true 로 정의된다.
    }

    public PagingDto(int page, int rows, String orderField, String direct) {
        this.page = page;
        this.rows = rows;
        this.orderField = orderField;
        this.direct = direct;
    }

    public void setQueryString(Map<String, String[]> queryMap) {
        ///?hobby=낚시&hobby=그림&page=1&rows=7
        //{name :[val1,val2..],boardNo=[1],hobby=["낚시","그림"]}
        //?hobby=낚시&hobby=그림
        String queryString= "";
        String and="?";
        for (String name :queryMap.keySet()){
            if(!name.equals("page")){
                for (String val : queryMap.get(name)){
                    queryString+=and+name+"="+val;
                    and="&";
                }
            }
        } //rows=10&order=user_id&
        //th:href=@{'url'(name=1,name2=3) } => url?name=1&name2
        //list.do ->  th:href=@{''(page=${i})}  => list.do?paeg=3
        //list.do ->  th:href=@{('?'+'rows=10&order=user_id&')(page=${i})}  => list.do?rows=10&order=user_id&&paeg=3

        this.queryString = queryString.toString();
    }

    public void setTotalRows(int totalRows) {
        this.startRow=(this.page-1)*this.rows;
        this.totalRows = totalRows;
        this.totalPages=totalRows/rows+((totalRows%rows>0)?1:0);
        this.startPage=this.page-(pageCount-1)/2;
        if(this.startPage<1)this.startPage=1;
        this.endPage=this.startPage+this.pageCount-1;
        if(this.endPage>this.totalPages){
            this.startPage-=(this.endPage-this.totalPages);
            if(this.startPage<1)this.startPage=1;
            this.endPage=this.totalPages;
        }
        this.next= this.page != this.totalPages;
        this.prev= this.page > 1;
        this.nextPage=this.page+1;
        this.prevPage=this.page-1;
    }
}
