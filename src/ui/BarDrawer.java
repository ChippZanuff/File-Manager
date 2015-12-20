package ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

public class BarDrawer
{
    private Screen screen;
    private DefaultBar defaultBar;

    public BarDrawer(Screen screen, DefaultBar defaultBar)
    {
        this.screen = screen;
        this.defaultBar = defaultBar;
    }

    public void barDrawing(Bar bar)
    {
        int barLength = 0;
        int lastRow = this.screen.getTerminalSize().getRows() - 1;
        for (BottomBar bottomBar : bar.getList())
        {
            this.screen.putString(barLength, lastRow, bottomBar.getKeyNum(), Terminal.Color.DEFAULT, Terminal.Color.DEFAULT, ScreenCharacterStyle.Bold);
            barLength += bottomBar.getKeyNum().length();

            this.screen.putString(barLength, lastRow, bottomBar.getBarName(), Terminal.Color.BLACK, Terminal.Color.GREEN, ScreenCharacterStyle.Bold);
            barLength += bottomBar.getLength();
        }
    }
}
