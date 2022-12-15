package com.bigdata6.spring_mybatis.dto;

import lombok.Data;

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

    public PagingDto(int page, int rows, String orderField, String direct) {
        this.page = page;
        this.rows = rows;
        this.orderField = orderField;
        this.direct = direct;
        this.startRow=(page-1)*rows;
    }

    public void setQueryString(Map<String, String[]> queryMap) {
        //{name :[val1,val2..],boardNo=[1],hobby=["낚시","그림"]}
        //?hobby=낚시&hobby=그림
        StringBuilder queryString= new StringBuilder();
        for (String name :queryMap.keySet()){
            if(!name.equals("page")){
                for (String val : queryMap.get(name)){
                    queryString.append(name).append("=").append(val).append("&");
                }
            }
        }
        this.queryString = queryString.toString();
    }

    public void setTotalRows(int totalRows) {
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
