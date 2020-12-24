package com.Mapper;

import com.entity.SFile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInter {

    @Update("insert into fileinfo(FileName,DirId,FilePath,FileTime,FileSize,IsEncrypt,IsLock,PWD,FileId) values (#{FileName},#{DirId},#{Path},#{Time},#{Size},#{IsEnc},#{IsLock},#{PWD},#{Fid})")
    int insert(SFile file);

    @Update("delete from fileInfo where Fileid = #{Fid};")
    int deleteByKey(int Fid);

    @Select("select * from fileInfo where FileName = #{FileName} and DirId = #{Did}")
    @Results(id = "FileMap",value = {
            @Result(column = "Fileid",property = "Fid"),
            @Result(column = "FileName",property = "FileName"),
            @Result(column = "DirId",property = "DirId"),
            @Result(column = "FilePath",property = "Path"),
            @Result(column = "FileTime",property = "Time"),
            @Result(column = "FileSize",property = "Size"),
            @Result(column = "IsEncrypt",property = "IsEnc"),
            @Result(column = "IsLock",property = "IsLock"),
            @Result(column = "PWD",property = "PWD")
    })
    SFile selectByNDid(@Param("FileName") String FileName,@Param("Did") int Did);

    @Update("update fileInfo set FileTime = #{Time},FileSize = #{Size},IsEncrypt = #{IsEnc},IsLock = #{IsLock},PWD = #{PWD},FileName = #{FileName} where FileId = #{Fid}")
    int UpdateFile(SFile file);

    @Update("update fileInfo set IsLock = #{IsLock} where FileName = #{FileName} and DirId = #{DirId}")
    int LockFile(SFile file);

    @Update("update fileInfo set IsEncrypt = #{IsEnc},PWD = #{PWD} where FileName = #{FileName} and DirId = #{DirId}")
    int EncFile(SFile file);

    @Update("update fileInfo set FileTime = #{Time},FileSize = #{Size},FileName = #{FileName} where Fileid = #{Fid} and DirId = #{DirId}")
    int UpdateFileTS(SFile file);

    @Select("select * from fileInfo where DirId = #{Did}")
    @ResultMap("FileMap")
    List<SFile> selectByDid(int Did);

    @Select("Select * from fileInfo where Fileid = #{Fid}")
    @ResultMap("FileMap")
    SFile selectByFid(int Fid);

    @Select("select * from fileInfo where FileName like #{FileName} and DirId = #{Did}")
    @ResultMap("FileMap")
    List<SFile> NDFind(@Param("FileName") String FileName,@Param("Did") int Did);
}
