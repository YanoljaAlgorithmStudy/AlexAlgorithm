import java.util.Scanner;

public class ZeroOneTile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tiles = scanner.nextInt();
        int[] cache = new int[tiles+1];
        int fun = fun(tiles, cache);
        System.out.println(fun % 15746);
    }

    public static int fun(int tiles, int[] cache) {
        //base case -> 떠넘기지 않아도 되는것 == n만 큼 진행 됐다
        if (tiles <= 0) {
            return 0;
        }else if (cache[tiles] != 0) {
            return cache[tiles];
        }else if (tiles == 2) {
            return 2;
        } else if (tiles == 1) {
            return 1;
        }

        //recursive case -> 떠넘겨야 하는것 -> 2가지 tiles-1(1) || tiles -2(00)
        cache[tiles] = fun(tiles - 1, cache) % 15746 + fun(tiles - 2, cache) % 15746;
        return cache[tiles];
    }
}
