package ui;

import java.util.ArrayList;

public class AltBar implements Bar
{
    private ArrayList<BottomBar> AltList = new ArrayList<>();

    public AltBar()
    {
        this.AltList.add(new BottomBar("Left", "1", 8));
        this.AltList.add(new BottomBar("Right", "2", 8));
        this.AltList.add(new BottomBar("View", "3", 8));
        this.AltList.add(new BottomBar("Edit", "4", 8));
        this.AltList.add(new BottomBar("Print", "5", 8));
        this.AltList.add(new BottomBar("MkLink", "6", 8));
        this.AltList.add(new BottomBar("Find", "7", 8));
        this.AltList.add(new BottomBar("History", "8", 8));
        this.AltList.add(new BottomBar("Video", "9", 8));
        this.AltList.add(new BottomBar("", "10", 8));
        this.AltList.add(new BottomBar("ViewHs", "11", 8));
        this.AltList.add(new BottomBar("FoldHs", "12", 8));
    }

    @Override
    public ArrayList<BottomBar> getList()
    {
       return this.AltList;
    }
}
