import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import ui.Cursor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class StringLlist
{
    public ArrayList<File> getFileList(String path)
    {
        File list = new File(path);

        ArrayList<File> dirs = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();

        ArrayList<File> result = new ArrayList<>();

        for (File item : list.listFiles())
        {
            if (item.isDirectory())
            {
                dirs.add(item);
            }
            if (item.isFile())
            {
                files.add(item);
            }
        }

        result.addAll(dirs);
        result.addAll(files);

        if (list.getParent() != null)
        {
            result.add(0, new File(".."));
        }
        return result;
    }

    public void terminal(ArrayList<File> files)
    {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out);

        Screen screen = new Screen(terminal);
        screen.startScreen();
        TerminalSize ts = screen.getTerminalSize();
        Key key = null;
        screen.getTerminal().setCursorVisible(false);
        Cursor cursor = new Cursor(screen);

        int rows = ts.getRows();
        int startitr = 0;
        int enditr = (rows <= files.size()) ? rows : files.size();

        while (key == null || key.getCharacter() != 'Y')
        {
            int x = 0;
            int y = 0;

            if (key != null)
            {
                System.out.println(key.getKind().name());

                switch (key.getKind())
                {
                    case ArrowDown:
                        if(cursor.getRowPosition() < files.size() - 1 && !cursor.isBottom())
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
                            files = this.getFileList(this.getBaseName(files.get(1)));
                        }
                        else if (files.get(startitr + cursor.getRowPosition()).isDirectory())
                        {
                            files = this.getFileList(files.get(startitr + cursor.getRowPosition()).getPath());
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
                screen.refresh();
                screen.getTerminal().setCursorVisible(false);
            }
            //todo: check bottom...change from and to
            key = terminal.readInput();
        }
        screen.stopScreen();
    }

    public String getBaseName(File file)
    {
        return new File(file.getParent()).getParent();
    }
}
