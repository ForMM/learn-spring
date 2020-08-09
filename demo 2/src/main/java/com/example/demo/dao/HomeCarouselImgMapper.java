package com.example.demo.dao;

import com.example.demo.dao.entity.HomeCarouselImg;
import java.util.List;
import java.util.Map;

public interface HomeCarouselImgMapper {
    int deleteByPrimaryKey(String id);

    int insert(HomeCarouselImg record);

    HomeCarouselImg selectByPrimaryKey(String id);

    List<HomeCarouselImg> selectAll();

    int updateByPrimaryKey(HomeCarouselImg record);

    HomeCarouselImg selectByType(String type);
}