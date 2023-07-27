public class ProgrammersKaKaoInternship {
    public int[] solution(String[][] places) {
        /*


         */
        char[][] partPlace = new char[5][5];
        int[] result = new int[5];
        for(int i =0; i < places.length; i++){
            boolean answer = true;
            for(int k =0; k< places.length; k++){
                for(int j = 0; j < places.length; j++){
                    partPlace[k][j] = places[i][k].charAt(j);
                }
            }
            for(int k = 0; k< places.length; k++){
                for(int j = 0; j < places.length; j++){
                    if(partPlace[k][j] == 'P'){
                        answer = answer && checkCovid(k,j,partPlace);
                    }
                }
            }
            if(answer){
                result[i] = 1;
            }else{
                result[i] = 0;
            }
        }
        return result;
    }

    public boolean checkCovid(int row, int col, char[][] partPlace ){
        //round 1
        final int[] DIR_ROW = {-1,0,1,0};
        final int[] DIR_COL = {0,1,0,-1};

        for(int i =0; i< DIR_ROW.length; i++){ // 4방향 다 돌기
            int changeRow = row + DIR_ROW[i];
            int changeCol = col + DIR_COL[i];

            if(isLengthFine(partPlace, changeRow, changeCol) && partPlace[changeRow][changeCol] == 'P'){
                return false;
            }
            //round 2;
            int moveDoubleRow = row + DIR_ROW[i] * 2; // 2배씩 뛰기
            int moveDoubleCol = col + DIR_COL[i] * 2;

            if(isLengthFine(partPlace, moveDoubleRow, moveDoubleCol) && partPlace[moveDoubleRow][moveDoubleCol] == 'P' && partPlace[changeRow][changeCol] != 'X'){
                return false;
            }
        }

        //round 2;
        final int[] NEW_ROW = {-1,1,1,-1};
        final int[] NEW_COL = {1,1,-1,-1};

        final int[] CHECK_ROW = {0,1,-1,0,0,-1,0,1};
        final int[] CHECK_COL ={-1,0,0,-1,1,0,1,0};


        for(int i = 0; i< NEW_ROW.length; i++){

            int newRow = row + NEW_ROW[i];
            int newCol = col + NEW_COL[i];

            if(isLengthFine(partPlace, newRow, newCol) &&partPlace[newRow][newCol] == 'P'){
                int checkRow = newRow + CHECK_ROW[2*i];
                int checkCol = newCol + CHECK_COL[2*i];

                int checkRow2 = newRow + CHECK_ROW[2*i + 1];
                int checkCol2 = newCol + CHECK_COL[2*i + 1];

                if(partPlace[checkRow][checkCol] != 'X' || partPlace[checkRow2][checkCol2] != 'X' ){
                    return false;
                }
            }
        }
        return true;

    }

    public boolean isLengthFine(char[][] partPlace, int row, int col){
        return (partPlace.length > row && partPlace[0].length > col && 0 <= row && 0 <= col);
    }
}
