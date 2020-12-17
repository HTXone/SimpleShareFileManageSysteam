package com.Mapper;

import com.entity.User;
import java.util.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInter {

    @Insert("insert into userinfo (userId,userPWD,userName,NowIp,Number) values(#{UserId},#{PWD},#{Name},#{NowIP},#{Number}) ;")
    int insertUser(User user);

    @Update("delete from userinfo where id = #{id}")
    int deleteByPrimartKey(int id);

    @Select("select * from userInfo where id = #{id};")
    @Results(id = "User",value = {
            @Result(id = true,column = "UserId",property = "UserId"),
            @Result(column = "userPWD",property = "PWD"),
            @Result(column = "userName",property = "Name"),
            @Result(column = "NowIp",property = "NowIP"),
            @Result(column = "Number",property = "Number")
    })
    User selectByPrimaryKey(String id);

    @Update("update userinfo set userPWD = #{PWD} where userId = #{id}")
    int ChangePWD(@Param("id")int id,@Param("PWD")String PWD);

    @Update("update userinfo set userPWD = #{PWD},userName = #{Name},NowIp = #{NowIP},Number = #{Number} where userid = #{UserId};")
    int UpdateInfo(User user);

    @Update("update userinfo set NowIp = #{Ip} wherer userId = #{id}")
    int SetIp(@Param("id")int id,@Param("Ip")String Ip);

    @Update("update userinfo set Number = #{Number} where userid = #{id}")
    int NumberUpdate(@Param("id")int id,@Param("Number")int Number);

}
