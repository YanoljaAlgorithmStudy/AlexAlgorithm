public class Park {
    public static int next = 0;
    public static int row = 0;
    public static int colum = 0;


    public int[] solution(String[] park, String[] routes) {
        /*
        result = index 숫자로 표기
        나가거나, 못지나가는것들 유의( 아예 진행 X)

        1. 2차원 배열 만들기 (char 배열[][]) **
        2. S를 찾으면 routes를 진행 하는데 거기서 지나갈때 X가 있으면 해당 명령어 " "는 진행 못함
        3. S를 먼저 찾고 그다음에 rout를 꺼내서 DFS로 진행
        */
        int[] answer = new int[2];
        char[][] arr = new char[park.length][park[0].length()];
        for(int i =0; i< park.length; i++){
            for(int k=0; k < park[i].length(); k++){
                arr[i][k] = park[i].charAt(k);
                if(arr[i][k] == 'S'){
                    row = i;
                    colum = k;
                }
            }
        }
        fun(arr,routes);
        answer[0] = row;
        answer[1] = colum;
        return answer;

    }
    public void fun( char[][] arr, String[] routes){
        boolean flag = true;

        // base case
        if(next == routes.length){
            return;
        }

        int jump =Character.getNumericValue(routes[next].charAt(2));





        // recursive case


        char alpha = routes[next].charAt(0);
        next++;
        if(alpha == 'E'){
            for(int k =1; k<= jump; k++){
                if(!valid(row,colum+ k,arr) || arr[row][colum+k] == 'X'){
                    flag = false;
                    break;
                }
            }
        }
        else if(alpha == 'S'){

            for(int k =1; k<= jump; k++){
                if(!valid(row+k,colum,arr) || arr[row+k][colum] == 'X'){
                    flag = false;
                    break;
                }
            }
        }
        else if(alpha == 'W'){


            for(int k =1; k<= jump; k++){
                if(!valid(row,colum- k,arr) || arr[row][colum-k] == 'X'){
                    flag = false;
                    break;
                }
            }
        }
        else{

            for(int k =1; k<= jump; k++){
                if(!valid(row-k,colum,arr) || arr[row-k][colum] == 'X'){
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            flagMethod(alpha, jump);
        }
        fun(arr,routes);
    }

    public void flagMethod( char alpha, int jump) {
        if (alpha == 'E' ) {
            colum += jump;

        } else if (alpha == 'S') {

            row += jump;

        } else if (alpha == 'W') {

            colum -= jump;

        } else {
            row -= jump;
        }

    }



    public boolean valid(int row,int colum,char[][] arr){
        return (row < arr.length && row >= 0 && colum < arr[0].length && colum >= 0);
    }
}
