public class Ai{
  int[] scores = {1,-1,0};

  //Game game = new Game(false);

  public int[] findMove(char[][] board){
    double bestScore = Double.NEGATIVE_INFINITY;
    int[] move = new int[2];
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(board[i][j] == ' '){
          board[i][j] = getMarker(false);
          double score = minimax(board, 0, false);
          board[i][j] = ' ';
          if(score > bestScore){
            bestScore = score;
            move[0] = i;
            move[1] = j;
          }
        }
      }
    }
    return move;
  }

  public double minimax(char[][] board, int depth, boolean isMax){
     String result = checkWinner(board);
    if(result != null){
      return scores[convertWinner(result)];
    }
    if(isMax){
      double bestScore = Double.NEGATIVE_INFINITY;
      for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
          if(board[i][j] == ' '){
            board[i][j] = getMarker(false);
            double score = minimax(board, depth+1, true);
            board[i][j] = ' ';
            bestScore = Math.max(score, bestScore);
          }
        }
    }
    return bestScore;
  }else{
    double bestScore = Double.POSITIVE_INFINITY;
      for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
          if(board[i][j] == ' '){
            board[i][j] = getMarker(true);
            double score = minimax(board, depth+1, true);
            board[i][j] = ' ';
            bestScore = Math.min(score, bestScore);
          }
        }
      }
      return bestScore;
  }
  }

  
  private int convertWinner(String checkWinner){
    switch(checkWinner){
      case "X":
        return 0;
      case "O":
        return 1;
      case "tie":
        return 2;
      default:
        return 0;
    }
  }

  private char getMarker(boolean player){
    Game game = new Game(false);
    if(player){
      return game.playerMarker;
    }else{
      return game.aiMarker;
    }
  }

  private String checkWinner(char[][] board){
    Game game = new Game(false);
    return game.checkWinner(board);
  }
}