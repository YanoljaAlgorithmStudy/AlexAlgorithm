import java.util.Stack;

public class ProgrammersKakao2019 {
    public int solution(int[][] board, int[] moves) {
        //1 .숫자가 나올때 까지 해당 로우를 탐색(컬럼은 정해짐)
        //2. 나온숫자를 int로 저장
        //3. 해당 노드를 비워놓는다.
        //4. 스택에 집어넣기
        //5. stack에서 poll 2번하기(스택이 비어있는지 확인)
        //6. 뺸 두개가 숫자가 맞는지(맞으면 카운트 +2) 안맞으면 다시 집어넣기
        int count = 0;
        int a = 0;
        int b = 0;

        for(int i =0; i < moves.length; i++){
            int colum = moves[i]-1;
            fun(board,colum,0);
            if(!stack.isEmpty()){
                a = stack.pop();
            }
            if(!stack.isEmpty()){
                b = stack.pop();
            }

            if(a == b){
                count +=2;
            }else{
                stack.push(b);
                stack.push(a);
            }
        }
        return count;


    }
    static Stack<Integer> stack = new Stack<>();

    public void fun(int[][] board, int colum, int row){

        //base case
        if(board[row][colum] != 0){ // 숫자 나올시
            stack.push(board[row][colum]);
            board[row][colum] = 0;
            return;
        }

        //recursive case
        if(isValid(board, row+1) &&board[row][colum] == 0){
            fun(board,colum,row+1);
        }
    }

    public boolean isValid(int[][] board, int nextRow){
        return nextRow < board.length;
    }
}
