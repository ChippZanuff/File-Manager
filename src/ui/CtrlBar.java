package ui;


import java.util.ArrayList;

public class CtrlBar implements Bar
{
    private ArrayList<BottomBar> CtrlList = new ArrayList<>();

    public CtrlBar()
    {
        this.CtrlList.add(new BottomBar("Left", "1", 8));
        this.CtrlList.add(new BottomBar("Right", "2", 8));
        this.CtrlList.add(new BottomBar("Name", "3", 8));
        this.CtrlList.add(new BottomBar("Extens", "4", 8));
        this.CtrlList.add(new BottomBar("Write", "5", 8));
        this.CtrlList.add(new BottomBar("Size", "6", 8));
        this.CtrlList.add(new BottomBar("Unsort", "7", 8));
        this.CtrlList.add(new BottomBar("Createn", "8", 8));
        this.CtrlList.add(new BottomBar("Access", "9", 8));
        this.CtrlList.add(new BottomBar("Description", "10", 8));
        this.CtrlList.add(new BottomBar("Owner", "11", 8));
        this.CtrlList.add(new BottomBar("Sort", "12", 8));
    }

    @Override
    public ArrayList<BottomBar> getList()
    {
        return CtrlList;
    }
}
