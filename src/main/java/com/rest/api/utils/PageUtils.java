package com.rest.api.utils;

public class PageUtils {

    public static PageDTO calculatePage(int size, int page, long totalElements){
        PageDTO pageDTO = new PageDTO();
        boolean isFirst = false;
        boolean isLast = false;
        long totalPage = 0;

        if(totalElements % size == 0){
            totalPage = totalElements / size;
        }else{
            totalPage = totalElements / size + 1;
        }

        if(page == totalPage){
            isLast = true;
        }

        if(page == 1){
            isFirst = true;
        }

        pageDTO.setFirst(isFirst);
        pageDTO.setLast(isLast);
        pageDTO.setSize(size);
        pageDTO.setPage(page);
        pageDTO.setTotalPage(totalPage);
        pageDTO.setTotalElement(totalElements);

        return pageDTO;
    }

}
