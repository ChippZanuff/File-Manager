package ui;

import java.util.ArrayList;

public class DefaultBar implements Bar
{
    private ArrayList<BottomBar> DefaultList = new ArrayList<>();

    public DefaultBar()
    {
        this.DefaultList.add(new BottomBar("Help", "1", 8));
        this.DefaultList.add(new BottomBar("UserMin", "2", 8));
        this.DefaultList.add(new BottomBar("View", "3", 8));
        this.DefaultList.add(new BottomBar("Edit", "4", 8));
        this.DefaultList.add(new BottomBar("Copy", "5", 8));
        this.DefaultList.add(new BottomBar("RenMove", "6", 8));
        this.DefaultList.add(new BottomBar("MkFold", "7", 8));
        this.DefaultList.add(new BottomBar("Delete", "8", 8));
        this.DefaultList.add(new BottomBar("ConfMin", "9", 8));
        this.DefaultList.add(new BottomBar("Quit", "10", 8));
        this.DefaultList.add(new BottomBar("Plugin", "11", 8));
        this.DefaultList.add(new BottomBar("Screen", "12", 8));
    }

    @Override
    public ArrayList<BottomBar> getList()
    {
        return this.DefaultList;
    }
}
