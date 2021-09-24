import java.util.ArrayList;
import java.util.Arrays;

public class Deck { ;

    protected static ArrayList<Boolean> deck = new ArrayList<>(Arrays.asList(
            Boolean.TRUE,Boolean.TRUE,Boolean.TRUE,Boolean.TRUE,Boolean.TRUE,
            Boolean.TRUE,Boolean.TRUE,Boolean.TRUE));


    static void setDeck(int index){
        deck.set(index,Boolean.FALSE);
    }

    static void resetDeck(){
        for (Boolean availableBall : deck)
            availableBall = Boolean.TRUE;
    }

    static boolean checkDrawn(int ball){
        return !(deck.get(ball));
    }

}