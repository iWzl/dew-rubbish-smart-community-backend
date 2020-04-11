package com.upuphub.dew.community.operation.api.bean.vo.resp;


import lombok.Data;

@Data
public class PageInfoResp {
    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
}
