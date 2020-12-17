package com.Service;

import com.entity.SFile;
import java.io.File;

public interface FileService {

    SFile GetFile();

    boolean FileEncoding();

    File DownLoadFile();

    boolean UploadFile();

    boolean FileDecoding();

    boolean FileZip();

    boolean FileRzip();

}
