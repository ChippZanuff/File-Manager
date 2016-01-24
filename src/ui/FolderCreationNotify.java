package ui;

import biz.Input;
import biz.MetaData;

public class FolderCreationNotify
{
    String notify = "Make folder";
    String yes = "Yes";
    String no = "No";
    String result = "";
    private MetaData metaData;

    public FolderCreationNotify(MetaData metaData)
    {
        this.metaData = metaData;
    }

    public String getWithMeta()
    {
        if(this.result.length() >= 28)
        {
            return this.result.substring(this.result.length() - 26);
        }
        return this.result;
    }

    public void backSpace()
    {
        if(result.length() > 0)
        {
            this.result = this.result.substring(0, this.result.length() - 1);
        }
    }

    public String getModifiedMeta()
    {
        return this.result = metaData.getPath() + this.result;
    }

    public void inputString(Input input)
    {
        this.result = this.result + input.getChar();
    }

}