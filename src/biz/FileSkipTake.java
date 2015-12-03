package biz;

public class FileSkipTake
{
    private int skip = 0;
    private int take = 0;

    public int getSkip()
    {
        return this.skip;
    }

    public int getTake()
    {
        return this.take;
    }

    public void setSkip(int skip)
    {
        this.skip = skip;
    }

    public void setTake(int take)
    {
        this.take = take;
    }

    public void increment()
    {
        this.skip++;
        this.take++;
    }

    public void decrement()
    {
        this.skip--;
        this.take--;
    }

    public void resetSkipper()
    {
        this.skip = 0;
    }
}
