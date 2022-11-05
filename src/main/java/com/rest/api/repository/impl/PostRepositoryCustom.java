package com.rest.api.repository.impl;

import com.rest.api.utils.PageDTO;

public interface PostRepositoryCustom {

    PageDTO findAllWithCustomPage(int size, int page, String direction, String properties, String content, String title);
}
