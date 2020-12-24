package com.Mapper;

import com.entity.History;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryInter {

    @Update("insert into historyInfo(HistoryId,UserId,Fid,Mood,Time,FName,Did) values (#{Hid},#{Uid},#{Fid},#{Mood},#{Time},#{FileName},#{Did})")
    int Insert(History history);

    @Update("delete from historyInfo where HistoryId = #{Hid}")
    int deleteByKey(int Hid);

    @Select("select * from UHselect where HistoryId = #{Hid}")
    @Results(id = "historyMap",value = {
            @Result(id = true,column = "HistoryId",property = "Hid"),
            @Result(column = "Fid",property = "Fid"),
            @Result(column = "UserId",property = "Uid"),
            @Result(column = "FName",property = "FileName"),
            @Result(column = "Mood",property = "Mood"),
            @Result(column = "Time",property = "Time"),
            @Result(column = "Did",property = "Did"),
            @Result(column = "UserName",property = "userName")
    })
    History SelectByHid(int Hid);

    @Select("select * from historyInfo where Userid = #{Uid};")
    @ResultMap("historyMap")
    List<History> SelectByUid(long Uid);

    @Select("select * from UHselect where Did = #{Did};")
    @ResultMap("historyMap")
    List<History> SelectByDid(long Did);

    @Select("select * from UHselect where FName like #{FileName} and userName like #{UserName} and Did = #{Did};")
    @ResultMap("historyMap")
    List<History> SelectByFU(@Param("UserName")String UName,@Param("FileName")String FileName,@Param("Did")int Did);

    @Select("select * from UHselect where FName like #{FileName} and Did = #{Did};")
    @ResultMap("historyMap")
    List<History> SelectByFD(@Param("Did")int Did,@Param("FileName")String FileName);

    @Select("select * from UHselect where userName like #{UserName} and Did = #{Did};")
    @ResultMap("historyMap")
    List<History> SelectByUD(@Param("Did")int Did,@Param("UserName")String UName);


}
