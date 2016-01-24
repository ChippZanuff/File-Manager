package ui;


import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class Notifications
{
    private Screen screen;
    private int heightOfNotificationBar;
    private int widthOfColumn;
    private int tempRow;
    private int tempColumn;
    private int lenghtOfNotifyPanel = 0;
    private DeletionNotify deletionNotify = new DeletionNotify();
    private FolderCreationNotify folderCreationNotify;
    private FileCopy fileCopy;

    public Notifications(Screen screen, int heightOfNotificationBar, int widthOfColumn, FolderCreationNotify folderCreationNotify, FileCopy fileCopy)
    {
        this.tempColumn = widthOfColumn;
        this.tempRow = heightOfNotificationBar;
        this.screen = screen;
        this.heightOfNotificationBar = heightOfNotificationBar;
        this.widthOfColumn = widthOfColumn;
        this.folderCreationNotify = folderCreationNotify;
        this.fileCopy = fileCopy;
    }

    public void notificationPanelDrawing()
    {
        String BG = "                              ";
        char[] ch = new char[widthOfColumn - 6];
        this.lenghtOfNotifyPanel = BG.length() + 14;
        String horizontalLine = this.replaceString(String.valueOf(ch), '\u0000', '━');
        for (int i = 0; i < 8; i++)
        {
            this.screen.putString(this.widthOfColumn, this.heightOfNotificationBar++, BG, Terminal.Color.DEFAULT, Terminal.Color.CYAN);
            if(i == 0)
            {
                this.screen.putString(this.widthOfColumn, this.heightOfNotificationBar++, BG, Terminal.Color.BLUE, Terminal.Color.WHITE);
            }
        }

        this.resetColRow();

        for (int i = 0; i < 8; i++)
        {
            this.displayLine(this.widthOfColumn, this.heightOfNotificationBar, "┃");
            this.displayLine(this.widthOfColumn + ch.length + 1, this.heightOfNotificationBar++, "┃");
        }

        this.resetColRow();

        this.screen.putString(this.widthOfColumn, this.heightOfNotificationBar,"┏" + horizontalLine + "┓", Terminal.Color.BLACK, Terminal.Color.CYAN);
        this.screen.putString(this.widthOfColumn, this.heightOfNotificationBar + 8,"┗" + horizontalLine + "┛", Terminal.Color.BLACK, Terminal.Color.CYAN);
        this.heightOfNotificationBar = tempRow;
        this.widthOfColumn = tempColumn;
    }

    public void displayLine(int column, int row, String text)
    {
        screen.putString(column, row, text, Terminal.Color.BLACK, Terminal.Color.CYAN);
    }

    private String replaceString(String str, char from, char to)
    {
        return str.replace(from, to);
    }

    private void resetColRow()
    {
        this.heightOfNotificationBar = tempRow;
        this.widthOfColumn = tempColumn;
    }

    public void deletion(boolean check)
    {
        this.notificationPanelDrawing();
        screen.putString((this.widthOfColumn + 48) / 2, this.heightOfNotificationBar, this.deletionNotify.notify, Terminal.Color.BLUE, Terminal.Color.CYAN);
        if(check)
        {
            screen.putString((this.widthOfColumn + 7), (this.heightOfNotificationBar + 4), this.deletionNotify.yes, Terminal.Color.BLUE, Terminal.Color.YELLOW);
            screen.putString((this.widthOfColumn + 22), (this.heightOfNotificationBar + 4), this.deletionNotify.no, Terminal.Color.BLUE, Terminal.Color.CYAN);
        }
        if(!check)
        {
            screen.putString((this.widthOfColumn + 22), (this.heightOfNotificationBar + 4), this.deletionNotify.no, Terminal.Color.BLUE, Terminal.Color.YELLOW);
            screen.putString((this.widthOfColumn + 7), (this.heightOfNotificationBar + 4), this.deletionNotify.yes, Terminal.Color.BLUE, Terminal.Color.CYAN);
        }


    }

    public void makeFolder(boolean check)
    {
        this.notificationPanelDrawing();
        screen.putString((this.widthOfColumn + this.lenghtOfNotifyPanel + this.folderCreationNotify.notify.length()) / 2, this.heightOfNotificationBar, this.folderCreationNotify.notify, Terminal.Color.BLUE, Terminal.Color.CYAN);
        screen.putString(this.widthOfColumn + 1, this.heightOfNotificationBar + 1, this.folderCreationNotify.getWithMeta(), Terminal.Color.BLUE, Terminal.Color.WHITE);
        if(check)
        {
            screen.putString((this.widthOfColumn + 7), (this.heightOfNotificationBar + 4), this.folderCreationNotify.yes, Terminal.Color.BLUE, Terminal.Color.YELLOW);
            screen.putString((this.widthOfColumn + 22), (this.heightOfNotificationBar + 4), this.folderCreationNotify.no, Terminal.Color.BLUE, Terminal.Color.CYAN);
        }
        if(!check)
        {
            screen.putString((this.widthOfColumn + 22), (this.heightOfNotificationBar + 4), this.folderCreationNotify.no, Terminal.Color.BLUE, Terminal.Color.YELLOW);
            screen.putString((this.widthOfColumn + 7), (this.heightOfNotificationBar + 4), this.folderCreationNotify.yes, Terminal.Color.BLUE, Terminal.Color.CYAN);
        }
    }

    public void copyFiles(boolean check)
    {
        this.notificationPanelDrawing();
        screen.putString((this.widthOfColumn + lenghtOfNotifyPanel + 16) / 2, this.heightOfNotificationBar, this.fileCopy.copyNotify, Terminal.Color.BLUE, Terminal.Color.CYAN);
        screen.putString(this.widthOfColumn + 1, this.heightOfNotificationBar + 1, fileCopy.getFileName(), Terminal.Color.BLUE, Terminal.Color.WHITE);
        if(check)
        {
            screen.putString((this.widthOfColumn + 7), (this.heightOfNotificationBar + 4), this.folderCreationNotify.yes, Terminal.Color.BLUE, Terminal.Color.YELLOW);
            screen.putString((this.widthOfColumn + 22), (this.heightOfNotificationBar + 4), this.folderCreationNotify.no, Terminal.Color.BLUE, Terminal.Color.CYAN);
        }
        if(!check)
        {
            screen.putString((this.widthOfColumn + 22), (this.heightOfNotificationBar + 4), this.folderCreationNotify.no, Terminal.Color.BLUE, Terminal.Color.YELLOW);
            screen.putString((this.widthOfColumn + 7), (this.heightOfNotificationBar + 4), this.folderCreationNotify.yes, Terminal.Color.BLUE, Terminal.Color.CYAN);
        }
    }
}
