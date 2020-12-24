import com.FileSystem.FileSystem;
import com.Mapper.DirInter;
import com.Mapper.FileInter;
import com.Mapper.HistoryInter;
import com.Mapper.UserInter;
import com.Service.DirService;
import com.entity.History;
import com.entity.SDir;
import com.entity.SFile;
import com.entity.User;
import com.unit.TimeGet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MapperTest {

    InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserInter userInter;
    private DirInter dirInter;
    private FileInter fileInter;
    private HistoryInter historyInter;

    @Before
    public void init() throws IOException{
        in = Resources.getResourceAsStream("mybatis.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        //userInter = session.getMapper(UserInter.class);
        dirInter = session.getMapper(DirInter.class);
        fileInter = session.getMapper(FileInter.class);
        historyInter = session.getMapper(HistoryInter.class);
    }

    @After
    public void detory() throws IOException{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void NewUser(){
        String Uid = TimeGet.GetNowTimeID();
        long uid = Long.parseLong(Uid);
        uid= uid % 1000000000;
        User user = new User();
        user.setUserId(uid);
        user.setName("AAA");
        user.setPWD("123456");
        System.out.println(userInter.insertUser(user));
    }

    @Test
    public void Login(){
        System.out.println(userInter.Login("AAA","123456").toString());
    }

    @Test
    public void NewDir(){
        String Uid = TimeGet.GetNowTimeID();
        long uid = Long.parseLong(Uid);
        uid= uid % 1000000;
        SDir sDir = new SDir();
        sDir.setDirId((int)uid);
        sDir.setDirPath("AAA");
        sDir.setSize(0);
        sDir.setUserId(402268685);
        System.out.println(dirInter.insert(sDir));
    }

    @Test
    public void selectDir(){
        System.out.println(dirInter.SelectByDid(349565).toString());
    }

    @Test
    public void updateDir(){
        System.out.println(dirInter.updateSize(27583,100));
    }

    @Test
    public void NewFile(){
        SFile sf = new SFile();
        sf.setFileName("AFFF");
        sf.setFid((int)(Long.parseLong(TimeGet.GetNowTimeID())%1000000));
        sf.setDirId(27583);
        sf.setPath("AAA");
        sf.setTime(TimeGet.GetNowTime());
        System.out.println(fileInter.insert(sf));
    }

    @Test
    public void SelectFile(){
//        for(SFile sFile : fileInter.selectByDid(27583)){
//            System.out.println(sFile.toString());
//        }
        for(SFile sFile : fileInter.NDFind("%p%",349565)){
            System.out.println(sFile.toString());
        }
    }

    @Test
    public void UpdateFile(){
        for(SFile sFile : fileInter.selectByDid(27583)){
            System.out.println(sFile.toString());
            //sFile.setEnc(true);
            sFile.setFileName(sFile.getFileName().split(".gz")[0]);
            sFile.setTime(TimeGet.GetNowTime());
            sFile.setSize(sFile.getSize());
            System.out.println(fileInter.UpdateFileTS(sFile));
        }
    }

    @Test
    public void DeleteFile(){
        SFile sf = new SFile();
        sf.setFileName("AFFT");
        sf.setFid((int)(Long.parseLong(TimeGet.GetNowTimeID())%1000000));
        sf.setDirId(27583);
        sf.setPath("AAA");
        sf.setTime(TimeGet.GetNowTime());
        System.out.println(fileInter.insert(sf));
        System.out.println(fileInter.deleteByKey(sf.getFid()));
    }


    @Test
    public void NewHistory(){
        History history = new History();
        history.setFid(8685);
        history.setMood(1);
        history.setUid(402268685);
        history.setTime(TimeGet.GetNowTime());
        long time = Long.parseLong(TimeGet.GetNowTimeID());
        time = time%1000000;
        history.setHid((int)time);
        System.out.println(historyInter.Insert(history));
    }

    @Test
    public void SelectHistory(){
        //System.out.println(historyInter.SelectByHid(21686).toString());
        for(History historys : historyInter.SelectByFU("%C%","%p%",349565)){
            System.out.println(historys.toString());
        }
    }


    @Test
    public void EncodingTest(){
        FileSystem Fs = new FileSystem();
        //Fs.Encoding("222.py","D:\\Data\\SSFMS\\ROOT\\TTTTTTTT\\","12345678");
        //Fs.Decoding("222.py__d","D:\\Data\\SSFMS\\ROOT\\TTTTTTTT\\","12345678");
        //Fs.GZipFile("D:\\Data\\SSFMS\\ROOT\\TTTTTTTT\\","222.py");
        //Fs.GZtoFile("D:\\Data\\SSFMS\\ROOT\\TTTTTTTT\\","222.py.gz");

    }





}
