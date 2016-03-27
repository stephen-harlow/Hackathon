import java.io.IOException;

class Main{
    public static void main(String[] args){
        try {
            new Wund("365ab323bb38592fa0d670c612d213dd7654ceb6304bc9b1991a4a3cb194").createTask("Ben is actually good", "He can make tasks", null, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}