import java.io.File;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        StringLlist str = new StringLlist();

        ArrayList<File> files = str.getFileList("E:\\art");
        str.terminal(files);
    }
}
