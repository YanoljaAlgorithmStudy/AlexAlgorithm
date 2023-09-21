import java.util.LinkedList;
import java.util.Queue;

public class TwoQueueResult {
    public int solution(int[] queue1, int[] queue2) {
        /*
        1. Queue 2개 만들기 -> 배열초과 안나게 하기 위해서 ***
        2. 넣기전에 sum을 만들어서 합 구하기 ***
        ***while (count <= 두큐의 length 의 합)
        3. 두개의 Q의 합을 비교하기 -> 두큐의 합이 똑같다면 break***
        4. 합이 높은 큐는 pop해서 합이 작은 큐에 넣어주기
        5. count++ 해주기 ***
        ***
        6. count 추출
        */


        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sumQ = 0;
        long sumQ2 = 0;

        int count = 0; // 뺴고 들어가는걸 1번 으로 침
        for(int i = 0; i< queue1.length; i++){
            q.add(queue1[i]);
            q2.add(queue2[i]);

            sumQ += queue1[i];
            sumQ2 += queue2[i];
        }

        final int TOTAL_LENGTH = q.size() + q2.size() + 2;

        while(count <= TOTAL_LENGTH){
            if(sumQ == sumQ2 ){ //같을때
                return count;
            }else if(sumQ > sumQ2){ // Q1가 클때
                int qValue = q.poll(); // 뽑기
                q2.add(qValue); // 넣기
                sumQ -= qValue;
                sumQ2 += qValue;
            }else{ // Q2가 클때
                int q2Value = q2.poll();
                q.add(q2Value);
                sumQ += q2Value;
                sumQ2 -= q2Value;
            }
            count++;
        }
        return -1;
    }
}
