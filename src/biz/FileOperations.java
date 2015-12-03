package biz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileOperations
{
    public ArrayList<File> getRootDirectories(MetaData metaData)
    {
        return new ArrayList<>(Arrays.asList(metaData.getRootDirectory()));
    }

    public void execute(File file)
    {
        try
        {
            String cmd = file.getPath();
            if (! file.canExecute())
            {
                cmd = "start " + cmd;
            }
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
