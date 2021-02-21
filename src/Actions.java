import java.util.ArrayList;
public class Actions {
    ArrayList<String> actions;
    State state;
    public Actions(State state){
        this.state = state;
        this.actions = new ArrayList<>();
        for(int r=0;r<state.getBoard().length;r++){
            for(int c=0;c<state.getBoard().length;c++){
                if(state.getBoard()[r][c]*state.getActivePlayer()<0){
                    addAdjacentActions(r,c);
                }
            }
        }
       //System.out.println(this.actions);

    }
    private void addAdjacentActions(int r,int c) {
<<<<<<< HEAD
       // System.out.println(r);
        //System.out.println(r);

=======
>>>>>>> 71844adb70a393e2d315efdc62db7f4fdec0ef3e
        for (int i = Math.max(r - 1, 0); i < Math.min(r + 2, this.state.getBoard().length); i++) {
            for (int j = Math.max(c - 1, 0); j < Math.min(c + 2, this.state.getBoard().length); j++) {
                if (this.state.getBoard()[i][j] == 0) {
                    System.out.println("i "+i+" j "+r);

                    if (checkDirection(r, c, r-i, c-j)) {
                        //System.out.println("in second if");
                        if (!this.actions.contains(Integer.toString(i) + Integer.toString(j))) {
                            this.actions.add(Integer.toString(i) + Integer.toString(j));
                        }
                    }
                }
            }
        }
    }

    private boolean checkDirection(int r,int c, int x, int y) {
        System.out.println("r "+r+" c "+c);

        /*System.out.println("== check direction ==");
        System.out.print(" c "+c);
        System.out.print(" r "+r);
        System.out.print(" x "+x);
        System.out.println(" y "+y);*/

        if (c + y >= this.state.getBoard().length || r + x >= this.state.getBoard().length || c + y < 0 || r + x < 0) {
            return false;
        }
        if (this.state.getBoard()[x + r][y + c] == 0) {
            return false;
        }
        if (this.state.getBoard()[x + r][y + c] == this.state.getActivePlayer()) {
            return true;
        }
        return checkDirection(c + x, r + y, x, y);
    }
    public ArrayList<String> getActions(){
        return actions;
    }
}
