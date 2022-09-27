import java.util.ArrayList;
import java.util.HashMap;

public class Lightbulb {
    public static ArrayList<Boolean> solve(ArrayList<ArrayList<Integer>> bulbs) {
        boolean allOn = false;
        ArrayList<Boolean> possibleConfig = new ArrayList<Boolean>();
        int highestNum = findHighestNum(bulbs);

        HashMap<Integer, Boolean> config = new HashMap<Integer, Boolean>();
        for(int i = 1; i <= highestNum; i++) {
            config.put(i, false);
        }

        int rowCounter = 0;
        int numOfIterations = 0;
        while(!allOn) {
            if(rowCounter == bulbs.size()) {
                break;
            }
            rowCounter = 0;
            if(numOfIterations < 10000000) {
                outer: for(int i = 0; i < bulbs.size(); i++) {
                    for(int j = 0; j < bulbs.get(i).size(); j++) {
                        if(((bulbs.get(i).get(j) < 0) && config.get(Math.abs(bulbs.get(i).get(j))) == false) || (bulbs.get(i).get(j) > 0) && config.get(Math.abs(bulbs.get(i).get(j))) == true) {
                            rowCounter++;
                            break;
                        }
                        else if(j != 2) {
                            continue;
                        }
                        else {
                            int ranBulb = (int)Math.floor(Math.random()*(2-0+1)+0);
                            if(config.get(Math.abs(bulbs.get(i).get(ranBulb))) == false) {
                                config.replace(Math.abs(bulbs.get(i).get(ranBulb)), true);
                                //System.out.println("Bulb switched: " + (Math.abs(bulbs.get(i).get(ranBulb))));
                                break outer;
                            }
                            else if(config.get(Math.abs(bulbs.get(i).get(ranBulb))) == true) {
                                config.replace(Math.abs(bulbs.get(i).get(ranBulb)), false);
                                //System.out.println("Bulb switched: " + (Math.abs(bulbs.get(i).get(ranBulb))));
                                break outer;
                            }

                        }
                    }
                    numOfIterations++;
                }
            }
            else {
                break;
            }
        }

        for(int i = 1; i <= highestNum; i++) {
            possibleConfig.add(config.get(i));
        }

        return possibleConfig;
    }
    
    public static int findHighestNum(ArrayList<ArrayList<Integer>> bulbs) {
        int highestNum = 0;
            for(int i = 0; i < bulbs.size(); i++) {
                for(int j = 0; j < bulbs.get(i).size(); j++) {
                    if(Math.abs(bulbs.get(i).get(j)) > highestNum) {
                        highestNum = Math.abs(bulbs.get(i).get(j));
                    }
                }
            }
        return highestNum;
    }
}

