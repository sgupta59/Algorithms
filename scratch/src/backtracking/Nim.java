package backtracking;

import java.util.Scanner;

public class Nim {

	public static int N_COINS = 7;
	public static int MAX_MOVE = 3;
	public static int NO_GOOD_MOVE = -1;
	enum  Player {
		HUMAN,
		COMPUTER
	}
	int coins = N_COINS;
	Player whoseTurn;
	public Nim()
	{
		 coins = N_COINS;
		whoseTurn = Player.HUMAN;
	}
	
	public static Player nextPlayer(Player player)
	{
		return player == Player.HUMAN ? Player.COMPUTER : Player.HUMAN;
	}
	
	public void play()
	{
		Scanner sc = new Scanner(System.in);
		while (coins > 1)
		{
			
			System.out.println("Current coin count: " + coins);
			if (whoseTurn == Player.HUMAN)
			{
				System.out.print("Enter coins to take: " );
				int i = sc.nextInt();
				coins -= i;
			} else if (whoseTurn == Player.COMPUTER)
			{
				int nTaken = getComputerMove();
				System.out.println("Computer takes: " + nTaken + " coins");
				coins -= nTaken;
			}
			whoseTurn = nextPlayer(whoseTurn);
		}
		// announce results
		announceResults();
	}
	
	public int getComputerMove()
	{
		int nTaken = findGoodMove(coins,-1, 0);
		return nTaken == NO_GOOD_MOVE ? 1 : nTaken;
	}
	
	public int findGoodMove(int nCoins, int ctaken, int level)
	{
		int limit = nCoins < MAX_MOVE ? nCoins : MAX_MOVE;
		for (int taken = 1; taken <= limit; ++taken) 
		{
			if (isBadposition(nCoins-taken,taken, level+1))
			{
				return taken;
			}
		}
		return NO_GOOD_MOVE;
	}
	
	public boolean isBadposition(int nCoins,int taken, int level)
	{
		if (nCoins == 1) 
		{
			return true;
		}
		int ret = findGoodMove(nCoins,taken, level+1) ;
		boolean status =  ret == NO_GOOD_MOVE;
		return status;
	}
	
	public String player()
	{
		return whoseTurn == Player.COMPUTER ? "Computer" : "Human";
	}
	public void announceResults() 
	{
		if (coins == 0) {
			System.out.println("You took the last coin, you lose");
		} else {
			System.out.println("Only one coin left");
			if (whoseTurn == Player.HUMAN)
				System.out.println("Computer wins");
			else
				System.out.println("Computer loses");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Nim nim = new Nim();
		nim.play();
	}

}
