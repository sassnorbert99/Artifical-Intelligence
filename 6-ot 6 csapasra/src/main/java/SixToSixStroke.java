// Java code to for n Queen placement 
class SixToSixStroke
{

    static void breakLine()
    {
        System.out.print("\n---------------------------------\n");
    }
    static int MAX = 10;

    static int arr[] = new int[MAX], no;

    // Function to check queens placement  
    static void nQueens(int k, int n)
    {

        for (int i = 1; i <= n; i++)
        {
            if (canPlace(k, i))
            {
                arr[k] = i;
                if (k == n)
                {
                    display(n);
                }
                else
                {
                    nQueens(k + 1, n);
                }
            }
        }
    }

    // Helper Function to check if queen can be placed  
    static boolean canPlace(int k, int i)
    {
        for (int j = 1; j <= k - 1; j++)
        {
            if (arr[j] == i ||
                    (Math.abs(arr[j] - i) == Math.abs(j - k)))
            {
                return false;
            }
        }
        return true;
    }

    // Function to display placed queen  
    static void display(int n)
    {
        breakLine();
        System.out.print("Arrangement No. " + ++no);
        breakLine();
        int queenX = 0;

        for (int i = 1; i <= n; i++)
        {

            for (int j = 1; j <= n; j++)
            {
                if (arr[i] != j)
                {
                    System.out.print("\t_");
                }
                else
                {

                    System.out.print("\tQ");
                }
            }
            System.out.println("");
        }

        breakLine();
    }

    //--------------------------------------------------------------------------------

    // Function that returns true if the queen
    // can attack the opponent
    static boolean canQueenAttack(int queenRow, int queenColumn,
                                  int opponentRow, int opponentColumn)
    {
        // If queen and the opponent
        // are in the same row
        if (queenRow == opponentRow)
            return true;


        // If queen and the opponent
        // are in the same column
        if (queenColumn == opponentColumn)
            return true;

        // If queen can attack diagonally
        if (Math.abs(queenRow - opponentRow) == Math.abs(queenColumn - opponentColumn))
            return true;

        // Opponent is safe
        return false;
    }

    //------------------------------------------------------------------------------------






    public static void main(String[] args)
    {
        int n = 4;
        nQueens(1, n);

        //int queenRow = 1, queenColumn = 1;
        int queenRow = 3, queenColumn = 1;
        int opponentRow = 2, opponentColumn = 2;


        if (canQueenAttack(queenRow, queenColumn, opponentRow, opponentColumn))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}

