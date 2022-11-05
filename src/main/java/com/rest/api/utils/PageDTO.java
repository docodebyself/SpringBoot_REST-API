package com.rest.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PageDTO<T> {
    private int page;
    private int size;
    private long totalElement;
    private boolean isLast;
    private boolean isFirst;
    private long totalPage;
    private List<T> data;
}
