import java.util.Collections;
import java.util.List;

public class Main {

    public static int countTrees(List<Tree> forest, double angle){
        if (forest == null || forest.isEmpty()){
            return 0;
        }
        if (forest.size() == 1){
            return 1;
        }
        if (angle < 0){ // can also throw an error...

            angle = angle * -1;

        }
        if (angle == 360.0){
            return forest.size(); //check check
        }
        //calculate the degree from center to each tree
        for (Tree t : forest){
            t.calculateAndSetDegree(0,0);
        }
        //sort the forest by degree
        Collections.sort(forest);
        //we need to do a bit more then 1 full circle (360+angle angle)
        int currIndex = 0;
        int firstTreeIndex = 0;
        int maxTreesInAngle = 0;
        int currTreesInAngle = 0;
        while (currIndex < forest.size()){
            if (treesCanBeFoundOnSameViewAngle(forest.get(firstTreeIndex), forest.get(currIndex), angle)){
                currTreesInAngle++;
                maxTreesInAngle = Math.max(currTreesInAngle, maxTreesInAngle);
            }else{
                while(firstTreeIndex <= currIndex){ //we will move firstTreeIndex to the next tree which is relevant
                    if (treesCanBeFoundOnSameViewAngle(forest.get(firstTreeIndex), forest.get(currIndex), angle)) {
                        if (firstTreeIndex == currIndex){
                            currTreesInAngle = 1;
                        }else{
                            currTreesInAngle++;
                        }
                        break;
                    }else{
                        currTreesInAngle--; //we will decrease by 1 currTreesInAngle
                        firstTreeIndex++;
                    }
                }
            }
            currIndex++;
            if (currIndex == forest.size()){ //we finished circle, need to keep going on +angle more
                maxTreesInAngle = countTreesFromCertainIndexToAngleMore(forest, angle, firstTreeIndex,
                        currTreesInAngle, maxTreesInAngle);
            }
        }

        return maxTreesInAngle;
    }

    //could have made a more complicated implementation that uses only 1 while loop in countTrees function
    //decided to keep it Stupid&Simple with a bit of duplication (if I had more time I would have done that)
    private static int countTreesFromCertainIndexToAngleMore(List<Tree> forest, double angle, int firstTreeIndex,
                                                             int currTreesInAngle, int maxTreesInAngle) {
        int currIndex = 0;
        while (firstTreeIndex < forest.size() && currIndex < forest.size() &&
                firstTreeIndex != currIndex && forest.get(currIndex).getDegreeFromCenter() <= angle){
            if (treesCanBeFoundOnSameViewAngle(forest.get(firstTreeIndex), forest.get(currIndex), angle)){
                currTreesInAngle++;
                maxTreesInAngle = Math.max(currTreesInAngle, maxTreesInAngle);
            }else{
                while(firstTreeIndex < forest.size()){ //we will move firstTreeIndex to the next tree which is relevant
                    if (treesCanBeFoundOnSameViewAngle(forest.get(firstTreeIndex), forest.get(currIndex), angle)) {
                        if (firstTreeIndex == currIndex){
                            currTreesInAngle = 1;
                        }else{
                            currTreesInAngle++;
                        }
                    }else{
                        currTreesInAngle--; //we will decrease by 1 currTreesInAngle
                    }
                    firstTreeIndex++;
                }
            }
            currIndex++;
        }
        return maxTreesInAngle;
    }


    public static boolean treesCanBeFoundOnSameViewAngle(Tree t1, Tree t2, double angle){
        //getDegreeFromCenter always returns positive numbers
        double degreeBetweenAngles = Math.abs(t1.getDegreeFromCenter() - t2.getDegreeFromCenter());
        if (degreeBetweenAngles > 180){
            degreeBetweenAngles = degreeBetweenAngles - 180;
        }
        return degreeBetweenAngles <= angle;

    }

    public static void main(String[] args) {

    }
}
