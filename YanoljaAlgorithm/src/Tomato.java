import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tomato {
    /**
     * 1. 일단 1을 찾아야 한다 (여러개 일수 있다) - 출발점이 여러개인 BFS
     * 2. 1을 찾으면 BFS를 진행 시킨다
     * <p>
     * BFS 를 진행할떄!! --> 몇일이 지나는지 찾아야함
     * Queue를 만든다 <- 1에 해당되는 row Colum
     * 위,오른쪽, 아래, 왼쪽을 돌면서 0을 찾아야한다
     * 0을 찾으면 Queue에다가 집어 넣는다.
     * <p>
     * 처음에 4방향 가면 하루 지남
     */
    public static void main(String[] args) {
        int[] dirRow = {-1,0,1,0};
        int[] dirCol = {0,1,0,-1};
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        boolean[][] verify = new boolean[n][m];
        Queue<Integer> queue = new LinkedList<>();

        int[][] arr = new int[n][m];
        for (int i = 0; i < arr.length; i++) {
            for (int k = 0; k < arr[0].length; k++) {
                arr[i][k] = scanner.nextInt();
                if (arr[i][k] == 1) {
                    queue.offer(i); //row
                    queue.offer(k);// col
                    queue.offer(0);
                } else if (arr[i][k] == -1) {
                    verify[i][k] = true;
                }
            }
        }
        Integer day = 0;

        while (!queue.isEmpty()) {
            Integer row = queue.poll();
            Integer col = queue.poll();
            if (verify[row][col]) {
                queue.poll();
                continue;
            }
            day = queue.poll();
            verify[row][col] = true;


            for (int i = 0; i < dirRow.length; i++) {
                int nextRow = row + dirRow[i];
                int nextCol = col + dirCol[i];

                if (isSafeLength(nextRow, nextCol,arr)&&arr[nextRow][nextCol] == 0 && !verify[nextRow][nextCol]) {
                    queue.add(nextRow);
                    queue.add(nextCol);
                    queue.add(day + 1);
                }
            }

        }

        for (int i = 0; i < verify.length; i++) {
            for (int j = 0; j < verify[0].length; j++) {
                if (!verify[i][j]) {
                    System.out.println("-1");
                    return;
                }
            }
        }

        System.out.println(day);
    }

    private static boolean isSafeLength(int nextRow, int nextCol, int[][] arr) {
        return nextRow >= 0 && nextRow < arr.length && nextCol >= 0 && nextCol < arr[0].length;
    }

}
