import biz.FileOperations;
import biz.FileSkipTake;
import biz.MetaData;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import ui.Cursor;
import ui.FilesDrawing;
import ui.PanelDraw;

import java.io.File;
import java.util.ArrayList;

public class StringLlist
{
    public void terminal(ArrayList<File> files1, MetaData metaData)
    {

        int topRowPosition = 1;
        int bottomRowPosition = 2;
        ArrayList<File> files = files1;

        FileSkipTake fileSkipTake = new FileSkipTake();

        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out);
        Screen screen = new Screen(terminal);
        screen.startScreen();
        Key key = null;

        Cursor cursor = new Cursor(screen,topRowPosition, bottomRowPosition);
        PanelDraw panelDraw = new PanelDraw(screen);
        FilesDrawing filesDrawing = new FilesDrawing(screen, cursor);
        FileOperations fileOperations = new FileOperations(terminal, screen, cursor);
        metaData.setScreen(screen);

        int rows = screen.getTerminalSize().getRows() - 2;
        fileSkipTake.setSkip(0);
        fileSkipTake.setTake((rows <= files.size()) ? rows : files.size());

        while (key == null || key.getCharacter() != 'Y')
        {
            if (key != null)
            {
                System.out.println("File pos:" + cursor.getFilePosition(fileSkipTake));
                System.out.println("File row pos:" + cursor.getRowPosition());
                System.out.println(key.getKind().name());

                boolean isChild = (files.get(0) != null) && files.get(0).getName().equals("..");

                switch (key.getKind())
                {
                    case ArrowDown:
                        cursor.moveDown(files, fileSkipTake);
                        break;
                    case ArrowUp:
                        cursor.moveUp(fileSkipTake);
                        break;
                    case Enter:
                        if (cursor.getRowPosition() == topRowPosition && isChild)
                        {
                            metaData.setPath(metaData.back());
                            files = metaData.getFiles();
                        }
                        else if (files.get(cursor.getFilePosition(fileSkipTake)).isDirectory())
                        {
                            metaData.setPath(files.get(cursor.getFilePosition(fileSkipTake)).getPath());
                            files = metaData.getFiles();
                        }
                        else if (files.get(cursor.getFilePosition(fileSkipTake)).isFile())
                        {
                            fileOperations.execute(files.get(cursor.getFilePosition(fileSkipTake)));
                        }
                        panelDraw.setSizeOfString(metaData.getSizeOfString());
                        fileSkipTake.resetSkipper();
                        fileSkipTake.setTake((rows <= files.size()) ? rows : files.size());
                        cursor.resetRowPosition();
                        break;
                    case F1:
                        files = fileOperations.getRootDirectories(metaData);
                        panelDraw.setSizeOfString(metaData.getSizeOfString());
                        fileSkipTake.resetSkipper();
                        fileSkipTake.setTake((rows <= files.size()) ? rows : files.size());
                        cursor.resetRowPosition();
                        break;
                }
                screen.clear();
                filesDrawing.filesDrawing(files, fileSkipTake);
                panelDraw.panDraw(metaData);
                screen.refresh();
                screen.getTerminal().setCursorVisible(false);
            }
            key = terminal.readInput();
        }
        screen.stopScreen();
    }
}
