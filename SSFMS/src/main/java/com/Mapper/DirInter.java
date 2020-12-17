package com.Mapper;

import com.entity.SDir;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface DirInter {

    @Update("insert into dirinfo(DirId,userID,DirPath,DirIp,Size) values(#{DirId},#{UserId},#{DirPath},#{Ip},#{Size});")
    int insert(SDir dir);

    @Update("delete from dirinfo where DirId = #{id}")
    int deleteById(int id);

    @Select("select * from dirInfo where id = #{id}")
    @Results(id = "SDir",value = {
            @Result(id = true,column = "DirId",property = "DirId"),
            @Result(column = "userId",property = "UserId"),
            @Result(column = "DirPath",property = "DirPath"),
            @Result(column = "DirIp",property = "Ip"),
            @Result(column = "Size",property = "Size")
    })
    SDir SelectByDid(int id);

    @Update("update dirinfo set DirPath = #{DirPath},DirIp = #{Ip},Size = #{Size} where DirId = #{DirId}")
    int UpdateDirInfo(SDir dir);

    @Update("update dirinfo set DirIp = #{Ip} where DirId = #{id}")
    int UpdateIp(@Param("id") int Did, @Param("Ip")String Ip);

    @Update("update dirinfo set Size = #{Size} where Dirid = #{id}")
    int updateSize(@Param("id") int Did,@Param("Size")int Size);

}
