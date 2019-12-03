package dungeon;

import java.util.ArrayList;
import java.util.Scanner;

public class DungeonAdventure
{
	public static void main(String[] args)
	{
		boolean exit = false;
		do {

			Scanner kb = new Scanner(System.in);
			System.out.println("WELCOME TO DUNGEON ADVENTURE\n" +
					"Please choose from the following:\n-------------------------------------\n" +
					"1. New Game\n" +
					"2. Load Game\n" +
					"3. How to Play\n" +
					"4. Exit");
			int choice = kb.nextInt();
			switch (choice) {
				case 1:
					GameLoop(false);
					break;
				case 2:
					GameLoop(true);
					break;
				case 3:
					howToPlay();
					break;
				case 4:
					exit = true;
					break;

			}
		}while(!exit);
		System.exit(0);
	}

	private static boolean playAgain(Scanner kb)
	{
		char again;

		System.out.println("Play again (y/n)?");
		again = kb.nextLine().charAt(0);

		return (again == 'Y' || again == 'y');
	}

	private static void battle(Hero theHero, Monster theMonster, Scanner kb)
	{
		char pause = 'p';
		System.out.println(theHero.getName() + " battles " +
				theMonster.getName());
		System.out.println("---------------------------------------------");

		while (theHero.isAlive() && theMonster.isAlive() && pause != 'q')
		{
			theHero.battleChoices(theMonster);

			if (theMonster.isAlive())
				theMonster.attackEnemy(theHero);

			System.out.print("\n-->q to quit, anything else to continue: ");
			pause = kb.nextLine().charAt(0);

		}

		if (!theMonster.isAlive())
			System.out.println(theHero.getName() + " was victorious!");
		else if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(");
		else
			System.out.println("Quitters never win ;-)");

	}

	private static void GameLoop(boolean loadGame)
	{
		Hero theHero = null;
		Dungeon dungeon = null;
		Scanner kb = new Scanner(System.in);

		if(loadGame)
		{
			Memento m = new Memento();
			m.loadFromFile();
			theHero = m.getHero();
			dungeon = new Dungeon(theHero, true);
			dungeon.restoreRooms(m.getRooms());
		}

		do
		{
			if(!loadGame)
			{
				gameInfo();
				theHero = new HeroFactory().createHero();
				dungeon = new Dungeon(theHero);
			}
			PlayGame(theHero, dungeon);

		} while (playAgain(kb));
	}

	private static void PlayGame(Hero hero, Dungeon dungeon)
	{
		boolean gameOver = false;
		Scanner kb = new Scanner(System.in);

		getCurrentRoom(dungeon);

		while(hero.isAlive() && !gameOver)
		{

			if(playerMenu(hero, dungeon.getRooms(), dungeon))
			{
				hero.usingVision = false;
				System.out.println("Current Room: \n");
				getCurrentRoom(dungeon);
				Room cur = dungeon.getRoom();

				if(cur.isHealPotion())
				{
					System.out.println("Found a heal potion!\n");
					hero.healingPotions++;
				}

				if(cur.isMonster())
				{
					Monster monster = new MonsterFactory().createMonster();
					battle(hero, monster, kb);
				}

				if(cur.isPillar())
				{
					System.out.println("You found a Pillar!\n");
					hero.pillars++;
				}

				if(cur.isPit())
				{
					int pitDMG = (int)(Math.random() * 30) + 1;

					System.out.println("Oh no! You fell into a pit and took" + pitDMG + " damage\n");

					hero.subtractHitPoints(pitDMG);

					if(!hero.isAlive())
						return;
					else
					{
						System.out.println(hero.getName() + "HP left: " + hero.getHitPoints());
					}
				}
				if(cur.isVisionPotion())
				{
					System.out.println("You found a Vision Potion!");
					hero.visionPotions++;
				}
				if(cur.isExit())
				{
					if(hero.pillars == 4)
					{
						System.out.println("You successfully escaped the dungeon with all four pillars of OO!\n" +
								"Congratulations, you win!!\n");
						gameOver = true;
					}
					else
					{
						System.out.println("You cannot leave the dungeon without the Pillars!");
					}
				}
				cur.setHealPotion(false);
				cur.setMonster(false);
				cur.setPillar(false);
				cur.setVisionPotion(false);
			}
			else if(hero.usingVision)
			{
				System.out.println(dungeon.activateVisionPotion());
			}
		}


		System.out.println(dungeon.printDungeon(0,0,4,4));
	}

	private static void gameInfo()
	{
		System.out.println("Welcome to Dungeon Adventures!\n" +
				"In this game you are a Hero who is searching for the four mysterious Pillars of OO.\n" +
				"In order to obtain these Pillars, you must navigate your way through a dungeon, find the Pillars,\n" +
				"and then make your way to the Exit. But be careful, this dungeon is filled with traps, monsters, and dead ends.\n\n" +
				"However, there are a few healing potions that you can find throughout the dungeon, so if you find one\n" +
				"use it wisely. Now, good luck hero, and be safe in your travels!\n");
	}


	private static boolean playerMenu(Hero hero, ArrayList<Room> rooms, Dungeon dungeon)
	{


		Scanner fin = new Scanner(System.in);
		char move;
		System.out.println("____________________________________\n\t\tHero Menu\n____________________________________");
		System.out.println("W:UP\t\tHP: " + hero.getHitPoints()+
				"\nS: DOWN\t\te: Use healing Potion (" + hero.healingPotions + ")" +
				"\nD: RIGHT\tq: Use vision Potion (" + hero.visionPotions + ")" +
				"\nA: LEFT\t\tPillars Found: " + hero.pillars +
				"\n\t\t\tL: Save Game");

		move = fin.nextLine().charAt(0);

		switch(move)
		{
			case 'a':
				if(hero.getyPos() - 1 < 0) {
					System.out.println("Cannot move LEFT\n");
					return false;
				}
				else {
					hero.setyPos(hero.getyPos() - 1);
					return true;
				}

			case 'd':
				if(hero.getyPos() + 1 > 4) {
					System.out.println("Cannot move RIGHT\n");
					return false;
				}
				else {
					hero.setyPos(hero.getyPos() + 1);
					return true;
				}

			case 's':
				if(hero.getxPos() + 1 > 4) {
					System.out.println("Cannot move DOWN\n");
					return false;
				}
				else {
					hero.setxPos(hero.getxPos() + 1);
					return true;
				}

			case 'w':
				if(hero.getxPos() - 1 < 0) {
					System.out.println("Cannot move UP\n");
					return false;
				}
				else {
					hero.setxPos(hero.getxPos() - 1);
					return true;
				}
			case 'e':
				hero.useHealingPotion();
				return false;

			case 'q':
				hero.useVisionPotion();
				return false;

			case 'l':
				Memento m = new Memento(hero, rooms);
				m.saveToFile();
				return true;

			case 'm':
				System.out.println(dungeon.printDungeon(0,0,4,4));
				return false;

		}

		return false;
	}
	private static void getCurrentRoom(Dungeon dungeon)
	{
		System.out.println("____________________________________\n");
		System.out.println(dungeon);
	}

	private static void howToPlay()
	{
		System.out.println("\t\tHOW TO PLAY\n" +
				"Use the WASD keys to move your character from room to room.\n" +
				"Each room has an event that takes place (fighting a monster, finding a pillar, ect.)\n" +
				"If you find a Vision Potion, you will be able to see an extended view of the map for a single turn.\n" +
				"Labeling is as follows:\n" +
				"M: Multiple Items\n" +
				"P: Pit\n" +
				"I: Entrance\n" +
				"V: Vision Potion\n" +
				"H: Healing Potion\n" +
				"E: Empty room\n" +
				"X: Monster\n");
	}
}