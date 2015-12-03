import biz.FilesLists;
import biz.MetaData;

import java.io.File;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        StringLlist str = new StringLlist();
        FilesLists filesLists = new FilesLists();
        MetaData metaData = new MetaData("E:\\");
        ArrayList<File> files = filesLists.getFileList("E:\\");

        //Directory dir = new Directory(new FilesRepository());
        //dir.forward("E:\\");
        //new FileManager().terminal(dir);

        str.terminal(files, metaData);
    }
}
