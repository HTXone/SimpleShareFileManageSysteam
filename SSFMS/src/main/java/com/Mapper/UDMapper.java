package com.Mapper;


import com.entity.UD;
import com.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UDMapper {

    @Insert("insert into udinfo(uid,did,UDid) values (#{uid},#{did},#{UDid});")
    int insertUD(UD ud);

    @Select("select * from udsearch where UDid = #{UDid}")
    @Results(id = "UDMap",value = {
            @Result(id = true, column = "UDid",property = "UDid"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "did",property = "did"),
            @Result(column = "userPWD",property = "userPWD"),
            @Result(column = "userName",property = "userName"),
            @Result(column = "ownerId",property = "ownerId"),
            @Result(column = "DirPath",property = "DirPath"),
            @Result(column = "DirName",property = "DirName")
    })
    UD selectUD(int UDid);

    @Delete("delete from udinfo where UDid = #{UDid}")
    int deleteUD(int UDid);

    @Select("select * from udsearch where did = #{Did}")
    @ResultMap(value = "UDMap")
    List<UD> selectByDid(int Did);

    @Select("select * from udsearch where UserName = #{UserName} and userPWD = #{PWD}")
    @ResultMap("UDMap")
    UD Login(@Param("UserName")String UserName,@Param("PWD")String PWD);

    @Select("select * from udsearch where uid = #{uid} and did=#{did}")
    @ResultMap("UDMap")
    UD selectByUD(@Param("uid") long Uid,@Param("did") int Did);

    @Select("select * from udsearch where did = #{Did} and UserName like #{UName}")
    List<UD> selectByUName(@Param("Did") int Did,@Param("UName") String UName);

}
