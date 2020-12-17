package com.Mapper;

import com.entity.SFile;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInter {

    @Update("insert into fileinfo(FileName,DirId,FilePath,FileTime,FileSize,IsEncrypt,IsLock,PWD) values (#{FileName},#{DirId},#{Path},#{Time},#{Size},#{IsEnc},#{IsLock},#{PWD})")
    int insert(SFile file);

    @Update("delete from fileInfo where FileName = #{FileName};")
    int deleteByKey(String FileName);

    @Select("select * from fileInfo where FileName = #{FileName} and DirId = #{Did}")
    @Results(id = "FileMap",value = {
            @Result(id = true,column = "FileName",property = "FileName"),
            @Result(column = "DirId",property = "DirId"),
            @Result(column = "FilePath",property = "Path"),
            @Result(column = "FileTime",property = "Time"),
            @Result(column = "FileSize",property = "Size"),
            @Result(column = "IsEncrypt",property = "IsEnc"),
            @Result(column = "IsLock",property = "IsLock"),
            @Result(column = "PWD",property = "PWD")
    })
    SFile selectByNDid(String FileName,int Did);

    @Update("update fileInfo set FileTime = #{Time},FileSize = #{Size},IsEncrypt = #{IsEnc},IsLock = #{IsLock},PWD = #{PWD} where FileName = #{FileName} and DirId = #{DirId}")
    int UpdateFile(SFile file);

    @Update("update fileInfo set IsLock = #{IsLock} where FileName = #{FileName} and DirId = #{DirId}")
    int LockFile(SFile file);

    @Update("update fileInfo set IsEncrypt = #{IsEnc},PWD = #{PWD} where FileName = #{FileName} and DirId = #{DirId}")
    int EncFile(SFile file);

    @Update("update fileInfo set FileTime = #{Time},FileSize = #{Size} where FileName = #{FileName} and DirId = #{DirId}")
    int UpdateFileTS(SFile file);
}
