import biz.FilesLists;

import java.io.File;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        StringLlist str = new StringLlist();
        FilesLists filesLists = new FilesLists();
        ArrayList<File> files = filesLists.getFileList("E:\\art");
        str.terminal(files);
    }
}
