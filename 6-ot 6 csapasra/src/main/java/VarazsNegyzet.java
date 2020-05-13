/* 34. Varázsnégyzet. Írj olyan programot, amely kitölti az 1.34. ábra üres mezőit az 1-től
6- ig terjedő egész számokkal úgy, hogy minden sorban és oszlopban, valamint a színnel jelölt
átlókban minden szám csak egyszer szerepeljen!
A feladat a Füles rejtvényújság 2002/8-as számában jelent meg.*/


class VarazsNegyzet
{
    public static boolean isSafe(int[][] tabla,
                                 int sor, int oszlop,
                                 int szám)
    {
        // egyedi sor
        for (int i = 0; i < tabla.length; i++)
        {
            //hamisat ad vissza, ha a szám, amit próbálunk, már helyén van a sorban
            if (tabla[sor][i] == szám)
            {
                return false;
            }
        }

        // egyedi oszlop
        for (int sorIndex = 0; sorIndex < tabla.length; sorIndex++)
        {
            ////hamisat ad vissza, ha a szám, amit próbálunk, már helyén van az oszlopban

            if (tabla[sorIndex][oszlop] == szám)
            {
                return false;
            }
        }

        // egyedi négyzet
        int sqrt = (int) Math.sqrt(tabla.length);
        int sorEleje = sor - sor % sqrt;
        int oszlopEleje = oszlop - oszlop % sqrt;

        for (int r = sorEleje;
             r < sorEleje + sqrt; r++)
        {
            for (int d = oszlopEleje;
                 d < oszlopEleje + sqrt; d++)
            {
                if (tabla[r][d] == szám)
                {
                    //ilyenkor a board[r][d] helyen van szám, false
                    return false;
                }
            }
        }

        // ha nincs ütközés, akkor true
        return true;
    }



    public static boolean Megold(int[][] tabla, int n)
    {
        int sor = -1;
        int oszlop = -1;
        boolean üresE = true;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (tabla[i][j] == 0)
                {
                    sor = i;
                    oszlop = j;

                    // továbbra is vannak hiányzó értékek
                    üresE = false;
                    break;
                }
            }
            //ha még van üres hely, akkor break
            if (!üresE)
            {
                break;
            }
        }

        // nincs üres hely
        if (üresE)
        {
            return true;
        }

        // else ág: backtrack
        for (int szam = 1; szam <= n; szam++)
        {
            if (isSafe(tabla, sor, oszlop, szam))
            {
                tabla[sor][oszlop] = szam;
                if (Megold(tabla, n))
                {
                    return true;
                }
                else
                {
                    tabla[sor][oszlop] = 0; // cserélje ki
                }
            }
        }
        return false;
    }



    public static void Rajzol(int[][] tabla, int N)
    {
        // lerajzolás
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                System.out.print(tabla[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((i + 1) % (int) Math.sqrt(N) == 0)
            {
                System.out.print("");
            }
        }
    }

    static void sorTores() {
        System.out.print("\n---------------------------------\n");
    }


    public static void main(String args[])
    {


        int[][] tabla = new int[][]
                {
                        {0, 0, 6, 5, 2, 4},
                        {4, 2, 5, 6, 0, 0},
                        {0, 4, 0, 0, 0, 5},
                        {2, 0, 0, 0, 6, 0},
                        {0, 0, 4, 0, 5, 0},
                        {5, 0, 0, 3, 0, 0},

                };

        /**int[][] tabla2 = new int[][]
                {
                        {3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}
                };
         */
        /**int[][] tabla3 = new int[][]
                {
                        {0, 0, 3},
                        {0, 2, 0},
                        {0, 0, 0},
                };
         */


        int N = tabla.length;

        Rajzol(tabla, N);
        sorTores();


        if (Megold(tabla, N))
        {
            Rajzol(tabla, N); // eredmény, ha van
        }
        else
        {
            System.out.println("Nincs megoldás:(");
        }
    }
} 