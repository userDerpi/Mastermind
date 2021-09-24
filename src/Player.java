import java.util.ArrayList;

public class Player {

    protected ArrayList<Integer> hand = new ArrayList<Integer>(4);
    protected int len = 0;

    Player(){
        for (int i = 0; i < 4; i++)
            hand.add(null);
    }

    public int getLen() {
        return len;
    }

    public ArrayList<Integer> getHand() {
        return hand;
    }

    public void setHand(int index,int ball){
        if(hand.get(index) == null)
            len++;
        hand.set(index,ball);
    }

    public void resetHand(){
        len = 0;
        for (int i = 0; i < 4; i++)
            hand.set(i,null);
    }

}