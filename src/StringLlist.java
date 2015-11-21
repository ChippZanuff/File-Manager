import biz.FilesLists;
import biz.PanelDraw;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import ui.Cursor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class StringLlist
{
    public void terminal(ArrayList<File> files)
    {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out);
        FilesLists filesLists = new FilesLists();

        Screen screen = new Screen(terminal);
        screen.startScreen();
        Key key = null;
        screen.getTerminal().setCursorVisible(false);
        Cursor cursor = new Cursor(screen);
        PanelDraw panelDraw = new PanelDraw(screen);
        int rows = screen.getTerminalSize().getRows() - 2;
        int startitr = 0;
        int enditr = (rows <= files.size()) ? rows : files.size();


        while (key == null || key.getCharacter() != 'Y')
        {
            int x = 1;
            int y = 1;

            if (key != null)
            {
                System.out.println(key.getKind().name());

                switch (key.getKind())
                {
                    case ArrowDown:
                        if(cursor.getRowPosition() < files.size() && !cursor.isBottom())
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
                        if (cursor.getRowPosition() == 0 && Objects.equals(files.get(0).getName(), ".."))
                        {
                            files = filesLists.getFileList(filesLists.getFileParent(files.get(1)));
                        }
                        else if (files.get(startitr + cursor.getRowPosition()).isDirectory())
                        {
                            files = filesLists.getFileList(files.get(startitr + cursor.getRowPosition()).getPath());
                        }
                        else if (files.get(startitr + cursor.getRowPosition()).isFile())
                        {
                            try
                            {
                                String cmd = files.get(startitr + cursor.getRowPosition()).getPath();
                                if (! files.get(startitr + cursor.getRowPosition()).canExecute())
                                {
                                    cmd = "start " + cmd;
                                }
                                Runtime.getRuntime().exec(cmd);
                            } catch (IOException e)
                            {
                                System.out.println(e.getMessage());
                            }
                        }
                        enditr = (rows <= files.size()) ? rows : files.size();
                        startitr = 0;
                        cursor.setRowPosition(0);
                }
                screen.clear();

                for (File item : files.subList(startitr, enditr))
                {
                    Terminal.Color c = Terminal.Color.BLUE;
                    if(cursor.getRowPosition() + startitr != files.indexOf(item))
                    {
                        c = Terminal.Color.DEFAULT;
                    }
                    screen.putString(x, y++, item.getName(), Terminal.Color.CYAN, c, ScreenCharacterStyle.Bold);
                }
                //panelDraw.panDraw();
                screen.refresh();
                //screen.getTerminal().setCursorVisible(false);
            }
            key = terminal.readInput();
        }
        screen.stopScreen();
    }


}
