class CrackerBarrelPeg
{
    public static void main(String[] args)
    {
        Game(0);
    }

    public static Movement play(Movement mov, Play p)
    {
        if(mov.board[p.from] != 1 && mov.board[p.over] != 1 && mov.board[p.to] != 0)
        {
            mov.flag1 = false;
            return mov;
        }
        else
        {
            mov.counter -= 1;
            mov.flag1 = true;
            mov.board[p.from] = 0;
            mov.board[p.over] = 0;
            mov.board[p.to] = 1;

            return mov;

        }
    }

    public static void solve(Movement mov, BoardMovement bm)
    {

        int i = 0;
        while(i<bm.counter)
        {
            Movement movNew = new Movement(mov);
            Play temporary = bm.getMove(i);
            movNew = play(movNew, temporary);
            i++;

            if(movNew.flag1)
            {
                movNew.againP[movNew.again] = temporary;
                movNew.again = movNew.again + 1;

                if(movNew.counter > 2)
                {
                    solve(movNew, bm);

                }
                printing(movNew);
            }
        }
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

    public static void Game(int startPos)
    {
        if(startPos > 5)
        {
            System.exit(0);
        }
        System.out.println("=== " + startPos + " ===");
        BoardMovement bm = new BoardMovement();
        Movement mov = new Movement(startPos);
        solve(mov, bm);
    }
}

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

        int j = 18;
        // for(int i = 0; i < 18; i++)
        int i = 0;
        while(i < 18)
        {
            boardMovement[j] = new Play(boardMovement[i].to, boardMovement[i].over, boardMovement[i].from);
            i++; j++;
        }

        counter = 36;
    }

    public Play getMove(int i)
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
        for(int i = 0; i < 15; i++)
        {
            board[i] = 1;
        }
        startPos = s;
        board[s] = 0;
        counter = 14;

    }

    public Movement(Movement mov)
    {
        for(int i = 0; i < 15; i++)
        {
            board[i] = mov.board[i];

        }
        for(int i = 0; i < mov.again; i++)

        {
            againP[i] = mov.againP[i];
        }
        flag1 = false;
        startPos = mov.startPos;
        counter = mov.counter;
        again = mov.again;

    }

    public void restartGame()
    {
        for(int i = 0; i < 15; i++)

        {
            board[i] = 1;

        }
        board[startPos] = 0;
    }

    public void print()
    {
        System.out.println("    " + board[0]);
        System.out.println("   " + board[1] + " " + board[2]);
        System.out.println("  " + board[3] + " " + board[4] + " " + board[5]);
        System.out.println(" " + board[6] + " " + board[7] + " " + board[8] + " " + board[9]);
        System.out.println(board[10] + " " + board[11] + " " + board[12] + " " + board[13] + " " + board[14]);
        System.out.print("\n");
    }
}
