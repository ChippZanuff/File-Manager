package ui;


import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class Notifications
{
    private Screen screen;
    private int heightOfNotificationBar;
    private int widthOfColumn;

    public Notifications(Screen screen, int heightOfNotificationBar, int widthOfColumn)
    {
        this.screen = screen;
        this.heightOfNotificationBar = heightOfNotificationBar;
        this.widthOfColumn = widthOfColumn;
    }

    public void notificationPanelDrawing()
    {
        String BG = "                              ";
        int row = (this.screen.getTerminalSize().getRows() - heightOfNotificationBar) / 2;
        int column = (this.screen.getTerminalSize().getColumns() - widthOfColumn) / 2;
        for (int i = 0; i < 8; i++)
        {
            this.screen.putString(column, row++, BG, Terminal.Color.CYAN, Terminal.Color.CYAN);
        }
        System.out.println(BG.length());
    }
}
