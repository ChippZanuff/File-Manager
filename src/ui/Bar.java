package ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;

public class Bar
{
    Screen screen;

    private ArrayList<BottomBar> bbList = new ArrayList<>();

    public Bar(Screen screen)
    {
        this.screen = screen;
        this.bbList.add(new BottomBar("Help", "1", 8));
        this.bbList.add(new BottomBar("UserMin", "2", 8));
        this.bbList.add(new BottomBar("View", "3", 8));
        this.bbList.add(new BottomBar("Edit", "4", 8));
        this.bbList.add(new BottomBar("Copy", "5", 8));
        this.bbList.add(new BottomBar("RenMove", "6", 8));
        this.bbList.add(new BottomBar("MkFold", "7", 8));
        this.bbList.add(new BottomBar("Delete", "8", 8));
        this.bbList.add(new BottomBar("ConfMin", "9", 8));
        this.bbList.add(new BottomBar("Quit", "10", 8));
        this.bbList.add(new BottomBar("Plugin", "11", 8));
        this.bbList.add(new BottomBar("Screen", "12", 8));
    }
    public void barDrawing()
    {
        int barLength = 0;
        int lastRow = this.screen.getTerminalSize().getRows() - 1;
        for(BottomBar bottomBar : bbList)
        {
            this.screen.putString(barLength, lastRow, bottomBar.getKeyNum(), Terminal.Color.DEFAULT, Terminal.Color.DEFAULT, ScreenCharacterStyle.Bold);
            barLength += bottomBar.getKeyNum().length();

            this.screen.putString(barLength, lastRow, bottomBar.getBarName(), Terminal.Color.BLACK, Terminal.Color.GREEN, ScreenCharacterStyle.Bold);
            barLength += bottomBar.getLength();
        }
    }
}
