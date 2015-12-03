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

            String [] commands = {
                    "cmd.exe", "/c", "start", "\"", file.getName() ,"\"", "\"" + file.getPath() + "\""
            };
            Runtime.getRuntime().exec(commands);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
