import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakereisner on 3/6/17.
 */
public class Chip {
    private String name;
    private int pointValue;

    public Chip(String name, int pointValue){
        this.name = name;
        this.pointValue = pointValue;
    }

    public static List<Chip> generateChips(int amt){
        List<Chip> list = new ArrayList<>();
        //black chips
        int numBlack = amt % 100;
        for(int i = 0; i<numBlack; i++){
            list.add(new Chip("black", 100));
        }
        amt -= numBlack * 100;

        //green chips
        int numGreen = amt % 25;
        for(int i = 0; i<numGreen; i++){
            list.add(new Chip("green", 25));
        }
        amt -= numGreen * 25;

        //red chips
        int numRed = amt % 5;
        for(int i = 0; i<numRed; i++){
            list.add(new Chip("red", 5));
        }
        amt -= numRed * 5;

        //white chips
        for(int i=0; i<amt; i++){
            list.add(new Chip("white", 1));
        }
        return list;
    }

    public String getName(){
        return name;
    }

    public int getPointValue(){
        return pointValue;
    }
}