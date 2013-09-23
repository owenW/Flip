package eecs285.proj4;

public class Record implements Comparable<Record>{
    String user_id;
    Integer user_score;
    Integer id;
    public Record(String userId, Integer score, Integer ID1){
        user_id = userId;
        user_score = score;
        id = ID1;
    }
    
    public int compareTo(Record arg0) {
        if(user_score - arg0.user_score != 0)
            return user_score - arg0.user_score;
        else if(user_id.compareTo(arg0.user_id)!=0)
            return user_id.compareTo(arg0.user_id); 
        else
            return id - arg0.id;
    }
}