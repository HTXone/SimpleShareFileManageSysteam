import com.FileSystem.FileSystem;


public class FileSystemTest {

    public static void main(String[] args) {
        FileSystem FS = new FileSystem("D:\\Data\\TEST\\ROOT");
        boolean c = FS.CreateNewDir("AAA");
        System.out.println(c);
    }

}
