package biz;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import ui.Cursor;
import ui.PanelDraw;

import java.io.File;
import java.util.ArrayList;

public class FileManager
{
    public void terminal(OldDirectory oldDirectory)
    {
        int topRowPosition = 1;
        int bottomRowPosition = 2;
        int defrowpos = 2;

        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out);
        Screen screen = new Screen(terminal);
        screen.startScreen();
        Key key = null;
        Cursor cursor = new Cursor(screen,topRowPosition, bottomRowPosition, defrowpos);
        PanelDraw panelDraw = new PanelDraw(screen);

        ArrayList<File> files = oldDirectory.getFiles();

        int rows = screen.getTerminalSize().getRows() - 2;
        int startitr = 0;
        int enditr = (rows <= files.size()) ? rows : files.size();
        int filePosition = this.getFilePosition(cursor, startitr);

        while (key == null || key.getCharacter() != 'Y')
        {
            int x = 1;
            int y = topRowPosition;

            if (key != null)
            {
                System.out.println("File pos:" + filePosition);
                System.out.println("File row pos:" + cursor.getRowPosition());
                System.out.println(key.getKind().name());

                boolean isChild = (files.get(0) != null) && files.get(0).getName().equals("..");

                switch (key.getKind())
                {
                    case ArrowDown:
                        int fileSize = (isChild)?  files.size(): files.size();

                        if(cursor.getRowPosition() < fileSize && !cursor.isBottom())
                        {
                            cursor.moveDown();
                        }
                        else if (enditr < files.size())
                        {
                            startitr += 1;
                            enditr += 1;
                        }
                        break;
                    case ArrowUp:
                        if (!cursor.isTop())
                        {
                            cursor.moveUp();
                        } else if (startitr > 0)
                        {
                            startitr -= 1;
                            enditr -= 1;
                        }
                        break;
                    case Enter:
                        System.out.println(filePosition);
                        if (files.get(filePosition) != null)
                        {
                            System.out.println(files.get(filePosition).getName());
                        }

                        if (cursor.getRowPosition() == topRowPosition && isChild)
                        {
                            oldDirectory.back();
                            files = oldDirectory.getFiles();
                        }
                        else if (files.get(filePosition).isDirectory())
                        {
                            oldDirectory.forward(files.get(filePosition).getPath());
                            files = oldDirectory.getFiles();
                        }
                        else if (files.get(filePosition).isFile())
                        {
                            oldDirectory.execute(files.get(filePosition));
                        }
                        panelDraw.setSizeOfString(oldDirectory.getPathLength());

                        enditr = (rows <= files.size()) ? rows : files.size();
                        startitr = 0;
                        cursor.resetRowPosition();

                        break;
                }

                screen.clear();
                filePosition = this.getFilePosition(cursor, startitr);

                for (File item : files.subList(startitr, enditr))
                {
                    Terminal.Color itemColor =  Terminal.Color.CYAN;
                    Terminal.Color selectedItemColor =  Terminal.Color.BLUE;
                    Terminal.Color backgroundColor =  Terminal.Color.DEFAULT;
                    if(item.isFile())
                    {
                        itemColor = Terminal.Color.WHITE;
                    }
                    if (files.get(filePosition).getName().equals(item.getName())) {
                        backgroundColor = selectedItemColor;
                    }
                    screen.putString(x, y++, item.getName(), itemColor, backgroundColor, ScreenCharacterStyle.Bold);
                }
                panelDraw.panDraw(new MetaData(oldDirectory.getPath()));
                //todo path draw

                screen.refresh();
                screen.getTerminal().setCursorVisible(false);
            }
            key = terminal.readInput();
        }
        screen.stopScreen();
    }

    public int getFilePosition(Cursor cursor, int startitr)
    {
        int position = (cursor.getRowPosition() + startitr);
        return (position > 0)?position - cursor.getDefaultTopRow(): position;
    }
}
