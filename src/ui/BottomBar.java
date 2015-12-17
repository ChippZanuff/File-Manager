package ui;


import com.googlecode.lanterna.terminal.Terminal;

public class BottomBar
{
    private String keyNum;
    private String barName;
    private Terminal.Color bgColor = Terminal.Color.GREEN;
    private int length;

    public BottomBar(String barName, String keyNum, int length)
    {
        this.barName = barName;
        this.keyNum = keyNum;
        this.length = length;
    }

    public String getKeyNum()
    {
        return this.keyNum;
    }

    public String getBarName()
    {
        int length = this.getLength();

        StringBuilder newBarNameLength = new StringBuilder(String.valueOf(new char[length]).replace('\u0000', ' '));

        String barName = this.barName;
        if (this.barName.length() > length)
        {
            barName = this.barName.substring(0, length);
        }

        return newBarNameLength.replace(0, barName.length(), barName).toString();
    }

    public int getLength()
    {
        return this.length - this.keyNum.length();
    }
}
