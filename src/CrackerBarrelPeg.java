class CrackerBarrelPeg
{
    public static void main(String[] args)
    {
        Puzzle(0);
    }

    public static Movement play(Movement mov, Play p)
    {
        if (mov.board[p.from] == 1 && mov.board[p.over] == 1 && mov.board[p.to] == 0)
        {
            mov.flag1 = true;
            mov.board[p.from] = 0;
            mov.board[p.over] = 0;
            mov.board[p.to] = 1;
            mov.counter -= 1;
            return mov;
        }
        else
        {
            mov.flag1 = false;
            return mov;
        }
    }

//        if (mov.board[p.from] == 1)
//        {
//            if (mov.board[p.over] == 1)
//            {
//                if (mov.board[p.to] == 0)
//                {
//                    mov.flag1 = true;
//                    mov.board[p.from] = 0;
//                    mov.board[p.over] = 0;
//                    mov.board[p.to] = 1;
//                    mov.counter -= 1;
//                    return mov;
//                }
//            }
//        }
//        else
//        {
//            mov.flag1 = false;
//            return mov;
//        }
//    }


    public static void solution(Movement mov, boardMovement bm)
    {
        for (int i = 0; i < bm.counter; i++)
        {
            Movement newMov = new Movement(mov);
            Play temporary = bm.nextMove(i);
            newMov = play(newMov, temporary);
            if (newMov.flag1)
            {
                newMov.againP[newMov.again] = temporary;
                newMov.again = newMov.again + 1;
                if (newMov.counter < 2)
                {
                    printing(newMov);
                }
                solution(newMov, bm);
            }
        }
    }

    public static void printing(Movement mov) {
        mov.boardRestart();
        mov.print();
        int i = 0;
        while (i < mov.again) {
            mov = play(mov, mov.againP[i]);
            mov.print();
            i++;
        }
        Puzzle(mov.startPos + 1);
    }

    public static void Puzzle(int startPos)
    {
        if (startPos > 5)
        {
            System.exit(0);
        }
        System.out.println("==" + startPos + "==");
        boardMovement bm = new boardMovement();
        Movement mov = new Movement(startPos);
        solution(mov, bm);
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

class boardMovement
{
    public Play boardMovement[] = new Play[36];
    public int counter;

    public boardMovement()
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
        int i = 0;
        while(i < 18)
        {
            boardMovement[j] = new Play(boardMovement[i].to, boardMovement[i].over, boardMovement[i].from);
            j++;
            i++;
        }
        counter = 36;
    }

    public Play nextMove(int i)
    {
        return boardMovement[i];
    }
}

class Movement
{
    public int counter;
    public int board[] = new int[15];
    public int startPos;
    public int i = 0;
    public int again = 0;
    public Play againP[] = new Play[2000];
    public boolean flag1 = false;

    public void boardRestart()
    {
        while(i < 15)
        {
            board[i] = 1;
            i++;
        }
        board[startPos] = 0;
    }

    public Movement(int start)
    {
        i = 0;
        while(i<15)
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
        int i = 0;
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
        flag1 = false;
        startPos = mov.startPos;
        counter = mov.counter;
        again = mov.again;
    }


    public void print()
    {
        System.out.println("    "+ board[0]);
        System.out.println("   "+ board[1]+ " " +board[2]);
        System.out.println("  " + board[3] + " " + board[4] + " " + board[5]);
        System.out.println(" " + board[6] + " " + board[7] + " " + board[8] + " " + board[9]);
        System.out.println(board[10] + " " + board[11] + " " + board[12] + " " + board[13] + " " + board[14]);
        System.out.print("\n");
    }
}

