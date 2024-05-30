package com.example.demo.mapper;

import com.example.demo.entity.Photo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Photomapper {
    @Select("select * from photo")
    List<Photo> find();

    @Insert("INSERT INTO photo (upload_photo, result_photo) VALUES (#{uploadphoto}, #{resultphoto})")
    void save(Photo photo);

}
