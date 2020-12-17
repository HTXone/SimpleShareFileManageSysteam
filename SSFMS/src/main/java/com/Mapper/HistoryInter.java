package com.Mapper;

import com.entity.History;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryInter {

    @Update("insert into historyInfo(HistoryId,UserId,FileName,Mood,Time) values (#{Hid},#{Uid},#{FileName},#{Mood},#{Time})")
    int Insert(History history);

    @Update("delete from historyInfo where HistoryId = #{Hid}")
    int deleteByKey(int Hid);

    @Select("select * from historyInfo where HistoryId = #{Hid}")
    @Results(id = "historyMap",value = {
            @Result(id = true,column = "HistoryId",property = "Hid"),
            @Result(column = "UserId",property = "Uid"),
            @Result(column = "FileName",property = "FileName"),
            @Result(column = "Mood",property = "Mood"),
            @Result(column = "Time",property = "Time")
    })
    History SelectByHid(int Hid);

}
