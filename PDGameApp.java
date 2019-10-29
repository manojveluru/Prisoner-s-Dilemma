package com.javaclass.assignments;
/*******************************************
Programmer Name: Manoj Veluru, Vasista Gutta
Zid: Z1840907, Z1839489
Course : CSCI502
Assignment: 3
Instructor: James Leon
TA: Sindhuja Parimi
Deadline: 10/13/2019
********************************************/
import java.util.Scanner;
import java.util.Date;
import java.util.HashMap;
/*PDGameApp class implementing main method - flow of the program*/
public class PDGameApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	//creating scanner	
		GameStat gs1 = null; //GameStat class object
		PDGame currentGamePtr1 = null; //PDGame class object
		HashMap<String, GameStat> map = new HashMap<>();  //HashMap to hold String key and GameStat pointer values
		boolean playGame = true;
		String currentDirectory = System.getProperty("user.dir"); //To get the current directory location
		while(playGame==true) 
		{
			boolean match = false;
			currentGamePtr1 = new PDGame(currentDirectory+"\\inputs.txt"); //PDGame class constructor with file path input
			System.out.println("***Starting A Game of Prisoner's Dilemma ***-5 rounds in this version of game");
			System.out.println();
			System.out.println("--HERE ARE STRATEGIES AVAILABLE FOR THE COMPUTER\n");
			for(String strategies : currentGamePtr1.getStrategiesList()) //Printing the strategies available in ArrayList
			{
				System.out.println(strategies);
			}
			System.out.println("\nSelect a strategy from above for the Computer to use in the 5 rounds: ");
			int userinput = sc.nextInt(); //Getting the Computer strategy input from user
			if(userinput>4 || userinput<1) //Error checks
			{
				System.out.println("Please Enter in the given range");
				continue;
			}
			currentGamePtr1.setStrategy(userinput);  //Setting the strategy
			gs1 = currentGamePtr1.getStats(); //Getting pointer to GameStat object out of PDGame 
			Date date = new Date(); //Creating Date object
			map.put(date.toString(), gs1);  //Assigning to hash map
			for(int i=0;i<5;i++)  //5 Rounds of play
			{
				System.out.println("\nBEGIN A ROUND - Here are your 2 choices\n1. Remain silent.\n2. Betray and testify against.\n");
				System.out.println("----What is your decision this round? ");
				int decision = sc.nextInt(); //Getting decision from the given choices
				if(decision>2 || decision<1)  //Error checks
				{
					System.out.println("Please Enter in given 2 choices");
					i--;
					continue;
				}
				String result  = currentGamePtr1.playRound(decision);
				System.out.println(result); //Printing result after the one round of play
			}
			String scores = currentGamePtr1.getScores(); //Getting final scores
			System.out.println("\nEND OF ROUNDS, GAME OVER");
			System.out.println(scores);
			System.out.println(gs1.getWinner()); //Printing the winner
			System.out.println("\n--Would you like to play another game (y/n)? "); //Prompting user to input to play game again
			if(!sc.next().equalsIgnoreCase("Y")) 
			{
				playGame = false;
			}
		}
		System.out.println("\t\tSummary of games and session times:");
		for(String timeStamp : map.keySet()) //Printing the summary of the games from hash map
		{
			System.out.println(timeStamp);
			System.out.println(map.get(timeStamp).getWinner());
			System.out.println("The Computer Used: "+map.get(timeStamp).getComputerStrategy());
		}
		sc.close(); //Scanner close
	}

}
