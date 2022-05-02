public class CrackerBarrelPeg
{
    public static void main(String[] args)
    {
        //
    }
}
class Move
{
    //Adding constructor parameters //
    public int from;
    public int over;
    public int to;

    //defining the moves for the board, for 18 moves are the direct combination give//
    //in the code, whereas the next 18 are the two and from values interchanged//
    public static Move[] MOVES = new Move[]{
        new Move(0,1,3),
        new Move(0,2,5),
        new Move(1,3,6),
        new Move(1,4,8),
        new Move(2,4,7),
        new Move(2,5,9),
        new Move(3,6,10),
        new Move(3,7,12),
        new Move(4,7,11),
        new Move(4,8,13),
        new Move(5,8,12),
        new Move(5,9,14),
        new Move(3,4,5),
        new Move(6,7,8),
        new Move(7,8,9),
        new Move(10,11,12),
        new Move(11,12,13),
        new Move(12,13,14),

        new Move(3,1,0),
        new Move(5,2,0),
        new Move(6,3,1),
        new Move(8,4,1),
        new Move(7,4,2),
        new Move(9,5,2),
        new Move(10,6,3),
        new Move(12,7,3),
        new Move(11,7,4),
        new Move(13,8,4),
        new Move(12,8,5),
        new Move(14,9,5),
        new Move(5,4,3),
        new Move(8,7,6),
        new Move(9,8,7),
        new Move(12,11,10),
        new Move(13,12,11),
        new Move(14,13,12)
    };
    public Move(int from, int over, int to)
    {
        this.from = from;
        this.over = over;
        this.to = to;
    }
    public String stringConvert(){
        return String.format("(%d,%d,%d)", this.from, this.over, this.to);
    }
}

class Movement
{
    public int counter;
    public int board[] = new int[15];
    public int startPos;
    public int i = 0;
    public int again = 0;
    public Move againP[] = new Move[2000];
    public boolean flag1 = false;
        public Movement(int start)
        {
            while(i<10)
            {
                board[i] = 1;
                i++;
            }
            startPos = start;
            board[start] = 0;
            counter = 14;
        }
        public Movement(Movement mov)
        {
            while(i<15)
            {
                board[i] = mov.board[i];
                i++;
            }
            while(i< mov.again)
            {
                againP[i] = mov.againP[i];
                i++;
            }
            counter = mov.counter;
            again = mov.again;
            startPos = mov.startPos;
            flag1 = false;
        }

}