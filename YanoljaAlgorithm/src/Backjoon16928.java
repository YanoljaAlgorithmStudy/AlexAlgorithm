import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Backjoon16928 {
    public static void main(String[] args) {
        /*
        1. 1 번 부터 100 번째까지의 이차원 배열을 만든다. ***
        2. map을 사용해서 뱀과 사다리의 위치 경로를 저장(시작위치, 도착위치) ***
        3. 일단 다 가보겠다! 사다리 || 뱀 에 걸리면 해당 칸으로 이동해야한다.
         */

        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Integer> startNDestination = new HashMap<>();
        int ladder = scanner.nextInt();
        int snake = scanner.nextInt();
        final int TEN = 10;
        int boardNumber = 1;

        int[][] board = new int[TEN][TEN];
        boolean[] flags = new boolean[101];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] =boardNumber;
                boardNumber++;
            }
        }
        for (int i = 0; i < ladder + snake; i++) {
            startNDestination.put(scanner.nextInt(), scanner.nextInt());
        }

        fun(board, startNDestination, flags, 0, 0, 0);
    }

    private static void fun(int[][] board, HashMap<Integer,Integer> startDestination, boolean[] flags, int row, int colum, int dice) {
        final int MAX_DICE = 6;
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(board[row][colum]);
        queue.offer(dice);

        while (!queue.isEmpty()) {
            Integer currentNum = queue.poll();
            Integer newDice = queue.poll();
            if (flags[currentNum-1]) {
                continue;
            }
            flags[currentNum-1] = true;

            if (currentNum == 100) {
                System.out.println(newDice);
                break;
            }

            for (int i = 1; i <= MAX_DICE; i++) {
                Integer nextDestination = currentNum +i;

                if (startDestination.containsKey(nextDestination)) {
                    int jump = startDestination.get(nextDestination);
                    queue.offer(jump);
                    queue.offer(newDice+1);
                } else {
                    queue.offer(nextDestination);
                    queue.offer(newDice+1);

                }
            }
        }
    }
}
