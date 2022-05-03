/*** This program is eqavalent to the python program at
 https://github.com/ptarau/CrackerBarrelPegPuzzle */
class CrackerBarrelPeg
{
    /** This is the main program where we call the Game board by giving the starting
     * positon as 0*/
    public static void main(String[] args)
    {
        Game(0);
    }

    /** the solution function,where we use the board values and the moves for the board as parameter*/
    public static void solution(Movement mov, BoardMovement bm)
    {
        int i = 0;
        while(i<bm.counter)
        {
            Movement movNew = new Movement(mov);
            Play temporary = bm.nextPos(i);
            movNew = play(movNew, temporary);
            i++;

            if(movNew.flag1)
            {
                movNew.againP[movNew.again] = temporary;
                movNew.again = movNew.again + 1;

                if(movNew.counter < 2)
                {
                    printing(movNew);
                }
                solution(movNew, bm);
            }
        }
    }

    /** This function is used for the board details where a check is made for the board positions that is from, over and to
     * which is given in the play function. If the from positon and the over position is 1 and the to position is 0 we decrease the
     * counter by 1 and the from and the over values are updated to 0 and the to value becomes 1**/
    public static Movement play(Movement mov, Play p)
    {
        if(mov.board[p.from] == 1 && mov.board[p.over] == 1 && mov.board[p.to] == 0)
        {
            mov.counter -= 1;
            mov.flag1 = true;
            mov.board[p.from] = 0;
            mov.board[p.over] = 0;
            mov.board[p.to] = 1;

            return mov;
        }
        else
        {
            mov.flag1 = false;
            return mov;
        }

    }
    /** In this function, define the game with the starting position, initially it is 0*/
    public static void Game(int startPos)
    {
        if(startPos > 5)
        {
            System.exit(0);
        }
        System.out.println("=== " + startPos + " ===");
        BoardMovement bm = new BoardMovement();
        Movement mov = new Movement(startPos);
        solution(mov, bm);
    }
    public static void printing(Movement mov)
    {
        mov.restartGame();
        mov.print();

        int i = 0;
        while(i < mov.again)
        {
            mov = play(mov, mov.againP[i]);
            mov.print();
            i++;
        }
        Game(mov.startPos + 1);
    }
}

/** The play function will have the from, over and the to positions*/
class Play
{
    public int from;
    public int over;
    public int to;

    public Play(int a, int b, int c)
    {
        from = a;
        over = b;
        to = c;
    }

    public void print()
    {
        System.out.println("(" + from + ", " + over + ", " + to + ")");
    }
}

class BoardMovement
{
    public Play boardMovement[] = new Play[36];
    public int counter;

    /**here, we define the board moves with the from, over and to position and each moves is named one movement */
    public BoardMovement()
    {
        boardMovement[0] = new Play(0,1,3);
        boardMovement[1] = new Play(0,2,5);
        boardMovement[2] = new Play(1,3,6);
        boardMovement[3] = new Play(1,4,8);
        boardMovement[4] = new Play(2,4,7);
        boardMovement[5] = new Play(2,5,9);
        boardMovement[6] = new Play(3,6,10);
        boardMovement[7] = new Play(3,7,12);
        boardMovement[8] = new Play(4,7,11);
        boardMovement[9] = new Play(4,8,13);
        boardMovement[10] = new Play(5,8,12);
        boardMovement[11] = new Play(5,9,14);
        boardMovement[12] = new Play(3,4,5);
        boardMovement[13] = new Play(6,7,8);
        boardMovement[14] = new Play(7,8,9);
        boardMovement[15] = new Play(10,11,12);
        boardMovement[16] = new Play(11,12,13);
        boardMovement[17] = new Play(12,13,14);

        /** we make use of 18 as it is a 5 column game and 18 positions which can be filled with 0 or 1 **/
        int j = 18;
        int i = 0;
        while(i < 18)
        {
            boardMovement[j] = new Play(boardMovement[i].to, boardMovement[i].over, boardMovement[i].from);
            i++; j++;
        }

        /** The counter will be twice the positions count as we have 0's and 1's each 18 count**/
        counter = 36;
    }

    public Play nextPos(int i)
    {
        return boardMovement[i];
    }
}
class Movement
{
    public int board[] = new int[15];
    public int counter;
    public boolean flag1 = false;
    public Play againP[] = new Play[1000];
    public int again = 0;
    public int startPos;
    public Movement(int s)
    {
         int i = 0;
         while(i < 15)
        {
            board[i] = 1;
             i++;
        }
        board[s] = 0;
        counter = 14;
        startPos = s;
    }

    /** the board is checked for the conditon for playing again, for each move it keeps a counter and we mention the start
     * position and a flag is monitered **/
    public Movement(Movement mov)
    {
         int i = 0;
         while(i < 15)
        {
            board[i] = mov.board[i];
             i++;
        }
        for(i = 0; i < mov.again; i++)

        {
            againP[i] = mov.againP[i];
        }
        flag1 = false;
        startPos = mov.startPos;
        counter = mov.counter;
        again = mov.again;

    }
     /** For every iteration when we have to restart the board from the beginning, we assign the start position to 0
      * and start again**/
    public void restartGame()
    {
         int i = 0;
         while(i < 15)

        {
            board[i] = 1;
             i++;
        }
        board[startPos] = 0;
    }

    /** printing pattern for the output as given in the python code's output**/
    public void print()
    {
        System.out.println("   " + board[0]);
        System.out.println("  " + board[1] + " " + board[2]);
        System.out.println(" " + board[3] + " " + board[4] + " " + board[5]);
        System.out.println(" " + board[6] + " " + board[7] + " " + board[8] + " " + board[9]);
        System.out.println(board[10] + " " + board[11] + " " + board[12] + " " + board[13] + " " + board[14]);
        System.out.print("\n");


    }
}
