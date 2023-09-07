import java.util.Arrays;

public class Arrow {
    public int[] solution(int n, int[] info) {
        /*
        핵심은 10 9 8 7 .... 10 을 점수를 포기하냐 가져가냐의 차이
        이런건 부르트 포스가 기본으로 깔고가기
        - 재귀함수를 도는것

        */
        int[] answer = new int[11];

        fun(n,info,answer,0);
        System.out.println(diffMax);
        System.out.println(Arrays.toString(semiAnswer));
        if(diffMax <= 0 ){
            return new int[] {-1};
        }else{
            return semiAnswer; // 결국 답
        }
    }

    private static int diffMax = Integer.MIN_VALUE;

    private static int[] semiAnswer = new int[11];



    public void fun(int arrow, int[] info, int[] result, int score){


        //base case
        if(arrow == 0 || score > 10){

            result[10] += arrow;

            int diff = getDiff(info, result);

            if(diff > diffMax){
                semiAnswer = Arrays.copyOf(result,11);
                diffMax = diff;

            }else if(diff == diffMax){
                semiAnswer = checkingStartWithLow(result, semiAnswer); // return은 semiAnswer
            }
            result[10] -= arrow;
            return;
        }





        // recursive case
        //이기는 경우
        if(info[score] < arrow){
            result[score] = info[score]+1;
            fun(arrow-(info[score]+1), info, result, score +1);
            result[score] = 0;
        }


        //지는 경우
        fun(arrow,info,result,score +1);
    }

    public int getDiff(int[] info, int[] lionResult){
        int score = 10;
        int apeachSum  = 0;
        int lioneSum = 0;

        for(int i = 0; i< info.length; i++){
            if(info[i] < lionResult[i]){
                lioneSum += score;
            }else if(info[i] > 0){
                apeachSum += score;
            }
            score--;
        }
        return lioneSum - apeachSum;

    }

    public int[] checkingStartWithLow(int[] result, int[] semiAnswer){
        for(int i =10; i >= 0; i--){
            if (result[i] > semiAnswer[i]){
                return Arrays.copyOf(result,11);
            }else if(result[i] < semiAnswer[i]){
                return semiAnswer;
            }
        }
        return Arrays.copyOf(result,11);
    }
}
