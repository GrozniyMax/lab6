package CommonClasses.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class IDManager {
    //TODO add usage of ID manager to commands
    public static Long lastId = 0L;

    private static Queue<Long> freeIDs = new LinkedList<>();

    public static Long getID(){
        if (freeIDs.isEmpty()){
            return lastId++;
        }
        return freeIDs.poll();
    }

    public static void addFreeID(Long ID){
        freeIDs.add(ID);
    }
}
