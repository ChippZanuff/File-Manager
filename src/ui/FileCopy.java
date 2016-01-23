package ui;

import biz.Input;
import biz.MetaData;

public class FileCopy
{
    String copyNotify = "Copy";
    String result = "";
    String selectedFiles = " files?";
    int filesSize = 0;
    MetaData metaData;

    public FileCopy(MetaData metaData)
    {
        this.metaData = metaData;
    }

    public void backSpace()
    {
        if(result.length() > 0)
        {
            this.result = this.result.substring(0, this.result.length() - 1);
        }
    }

    public void inputString(Input input)
    {
        this.result = this.result + input.getChar();
    }

    public String getFileName()
    {
        if(filesSize != 0)
        {
            return filesSize + selectedFiles;
        }
        else
        {
            return this.result;
        }
    }

    public void setFileName(String name)
    {
        this.result = name;
    }

    public void setFilesSize(int size)
    {
        this.filesSize = size;
    }
}
