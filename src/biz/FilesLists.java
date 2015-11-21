package biz;

import java.io.File;
import java.util.ArrayList;

public class FilesLists
{
    public ArrayList<File> getFileList(String path)
    {
        File list = new File(path);

        ArrayList<File> dirs = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();

        ArrayList<File> result = new ArrayList<>();

        for (File item : list.listFiles())
        {
            if (item.isDirectory())
            {
                dirs.add(item);
            }
            if (item.isFile())
            {
                files.add(item);
            }
        }

        result.addAll(dirs);
        result.addAll(files);

        if (list.getParent() != null)
        {
            result.add(0, new File(".."));
        }
        return result;
    }

    public String getFileParent(File file)
    {
        return new File(file.getParent()).getParent();
    }
}
