import java.util.ArrayList;
import java.util.List;

public class Kakao2017 {
    public static int count = 0;

    public int solution(int n, String[] data) {
        /*
        1.친구들을 줄을세운다.(같은사람이 다른줄에 중복이 안되게끔 새우는거)
        2. data에 들어있는 조건을 건다.
        3. 그조건이 성립이 되었을때 카운트++;
        */

        int select = 8;
        boolean[] visit = new boolean[select];
        String people = "ACFJMNRT";
        List<String> gather = new ArrayList<>();
        count = 0;

        fun(select,visit,people, gather, data);
        return count;

    }


    private void fun( int select, boolean[] visit, String people, List<String> gather, String[] data){


        // base case
        if(gather.size() == select){
            // 조건걸기 N과 F의 길이를 찾자
            for(int i= 0; i< data.length; i++){
                String a =data[i];
                String fisrtAlp = Character.toString(a.charAt(0));
                String secAlp = Character.toString(a.charAt(2));
                char ssb = a.charAt(3); // same small big

                int diff = Character.getNumericValue(a.charAt(4));

                int firstNum = gather.indexOf(fisrtAlp);
                int secNum = gather.indexOf(secAlp);

                int numOfDif = Math.abs(firstNum - secNum) -1;
                if(ssb == '='){
                    if(diff != numOfDif){
                        return;
                    }
                }
                else if(ssb == '>'){
                    if(diff >= numOfDif){
                        return;
                    }
                }
                else{
                    if(diff <= numOfDif){
                        return;
                    }
                }
            }
            count++;
        }



        //recursive case
        for(int i =0; i< select; i++){
            if(visit[i]){
                continue;
            }
            visit[i] = true;
            gather.add(Character.toString(people.charAt(i)));
            fun(select,visit,people,gather,data);
            gather.remove(gather.size()-1);
            visit[i] = false;

        }
    }
}
