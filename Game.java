import java.util.Scanner;

public class Game{

  private Scanner sc = new Scanner(System.in);
  private Ai ai = new Ai();

  public char[][] board= {
      {' ', ' ',' '},
      {' ', ' ',' '},
      {' ', ' ',' '}
    };
    public boolean playerTurn;
    public int round;
    public char playerMarker;
    public char aiMarker;

  public Game(boolean genBoard){
    this.playerTurn = true;
    this.round = 0;
    this.playerMarker = 'O';
    this.aiMarker = 'X';

    if(genBoard){
      gameInstructions();
      genBoard();
    }

  }

  public void genBoard(){
    //generate the gameboard with a for loop
    int[] move = new int[2];
    this.round++;
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        System.out.print(this.board[i][j]+ " | ");
      }
      System.out.println("\n--------------------");
    }
    //after board is generated place markers
    placeMarker();
  }

  private void placeMarker(){
    //before all this we wanna check if the game is over
    if(checkWinner(this.board) == null){
      if(this.playerTurn){
        //place the player marker with an input from them
        System.out.print("Please enter your position 1-9:\t");
        try{
          int pos = sc.nextInt();
          int[] move = assignPosition(pos);
            //make sure the position isn't already taken
            if(this.board[move[0]][move[1]] == ' '){
              this.board[move[0]][move[1]] = this.playerMarker;
              this.playerTurn = false;
              genBoard();
            }else{
              System.out.println("Please Chose a position that isn't already taken");
              placeMarker();
            }
          }catch(Exception e){
            System.out.println("Please Enter an integer");
        }
      }else{
 
        System.out.println("AI makes his turn.......");
        try{
          int[] move = ai.findMove(this.board);
          this.board[move[0]][move[1]] = this.aiMarker;
        this.playerTurn = true;
        genBoard();
        }catch(Exception e){
          System.out.println(e);
        }
        
      }
    }else{
      if(checkWinner(this.board) == "tie"){
        System.out.println("Ended in tie =^._.^= ");
      }else{
        System.out.println(checkWinner(this.board) + " has won the game!");
      }
    }
  }

private int[] assignPosition(int pos){
    //switch to mark the correct position
    int[] move = new int[2];
    switch(pos){
      case 7 : 
        move[0] = 0;
        move[1] = 0;
        break;
      case 8 :
        move[0] = 0;
        move[1] = 1;
        break;
      case 9 :
        move[0] = 0;
        move[1] = 2;
        break;
      case 4 : 
        move[0] = 1;
        move[1] = 0;
        break;
      case 5 :
        move[0] = 1;
        move[1] = 1;
        break;
      case 6 :
        move[0] = 1;
        move[1] = 2;
        break;
      case 1 :
        move[0] = 2;
        move[1] = 0;
        break;
      case 2 : 
        move[0] = 2;
        move[1] = 1;
        break;
      case 3 :
        move[0] = 2;
        move[1] = 2;
        break;
    }
    return move;
  }
   
   private static boolean equals3(char a, char b, char c){
     return a == b && b == c && a != ' ';
   }

   public static String checkWinner(char[][] board){
     String winner = null;

    // horizontal
    for (int i = 0; i < 3; i++) {
      if (equals3(board[i][0], board[i][1], board[i][2])) {
        winner = String.valueOf(board[i][0]);
      }
    }

    // Vertical
    for (int i = 0; i < 3; i++) {
      if (equals3(board[0][i], board[1][i], board[2][i])) {
        winner = String.valueOf(board[0][i]);
      }
    }

    // Diagonal
    if (equals3(board[0][0], board[1][1], board[2][2])) {
      winner = String.valueOf(board[0][0]);
    }
    if (equals3(board[2][0], board[1][1], board[0][2])) {
      winner = String.valueOf(board[2][0]);
    }

    int openSpots = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') {
          openSpots++;
        }
      }
    }

    if (winner == null && openSpots == 0) {
      return "tie";
    } else {
      return winner;
    }
  }

  private void gameInstructions(){
    System.out.println("You are O the AI is X, try to win.");
    System.out.println("The positions are arranged as a numpad");
    System.out.println("7|8|9");
    System.out.println("-----");
    System.out.println("4|5|6");
    System.out.println("-----");
    System.out.println("1|2|3");
    System.out.println("Press Enter to continue");
    sc.nextLine();
    System.out.println("\n\n\n");
  }
  
}

 