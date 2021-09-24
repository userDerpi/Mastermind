import java.util.*;

public class Table {

    private short round = 9;
    private short ballsPerRow = 4;
    private short roundGame = 9;

    protected ArrayList<Integer>[]table;

    Table(){
        table = new ArrayList[round];
        for (int i = 0; i < round; i++) {
            table[i] = new ArrayList<>(ballsPerRow);
            table[i].addAll(Arrays.asList(null,null,null,null));
        }
    }

    public void resetTable(){
        roundGame = 9;
        for (int i = 0; i < round; i++) {
            table[i].clear();
            table[i].addAll(Arrays.asList(null,null,null,null));
        }
    }



    public int getRoundGame() {
        return roundGame - 1;
    }

    public void nextRound(){
        roundGame--;
    }

    public void setTable(int index,int ball) {
        table[roundGame - 1].set(index,ball);
    }


    public ArrayList<Integer> compareHands(Player user,Computer enemy){
        int samePos = 0,same = 0;
        ArrayList<Integer> handUser = user.getHand();
        ArrayList<Integer> handComputer = enemy.getHand();

        for (int i = 0; i < ballsPerRow; i++)
            if(handComputer.contains(handUser.get(i)))
                if(Objects.equals(handComputer.get(i),handUser.get(i)))
                    samePos++;
                else
                    same++;


        return new ArrayList<>(Arrays.asList(same, samePos)) ;
    }
}