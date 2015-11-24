package ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

public class PanelDraw
{
    Screen screen;
    public PanelDraw(Screen screen)
    {
        this.screen = screen;
    }

    public void panDraw()
    {
        char[] ch = new char[(screen.getTerminalSize().getColumns() / 2) - 2];
        String horizontal = this.replaceString(String.valueOf(ch) ,'\u0000', '═');
        //String vertical = this.replaceString(String.valueOf(ch) ,'\u0000', ' ');

        this.displayLine(0, "╔" +  horizontal + "╗");

        for(int i = 1; i < screen.getTerminalSize().getRows() - 1; i++)
        {
            //screen.getCursorPosition().setColumn(0);
            this.displayLine(0, i, "║");
            this.displayLine(screen.getTerminalSize().getColumns() / 2 - 1, i, "║");
            //displayLine(i, "║" + vertical + "║");
        }

        this.displayLine(screen.getTerminalSize().getRows() - 1, "╚" + horizontal + "╝");
    }

    public void displayLine(int column, int position, String text)
    {
        screen.putString(column, position, text, Terminal.Color.DEFAULT, Terminal.Color.DEFAULT, ScreenCharacterStyle.Bold);
    }

    public void displayLine(int position, String text)
    {
        this.displayLine(0, position, text);
    }

    public String replaceString(String str, char from, char to)
    {
        return str.replace(from, to);
    }


}
