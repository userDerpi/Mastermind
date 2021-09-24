import java.util.ArrayList;

public class Computer {

    protected ArrayList<Integer> hand = new ArrayList<Integer>(4);

    Computer(){
        for (int i = 0; i < 4; i++)
            hand.add(null);
    }

    public ArrayList<Integer> getHand() {
        return (hand);
    }

    public void resetHand(){
        for (Integer ball:hand)
            ball = 0;
    }

    public void drawBalls(){
        int ball;
        for (int i = 0; i < 4; i++) {

            do {
                ball = RandomNum.random(1,8);
            }while(Deck.checkDrawn(ball - 1));

            Deck.setDeck(ball - 1);

            hand.set(i,ball);
        }
    }

}
