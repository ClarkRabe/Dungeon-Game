package dungeon;

import java.util.Scanner;

public class Sorceress extends Hero implements Attack
{
	private final int MIN_ADD = 25;
	private final int MAX_ADD = 50;


	public Sorceress()
	{
		this.name = getName();
		this.hitPoints = 75;
		this.attackSpeed = 5;
		this.chanceToHit = .7;
		this.damageMin = 25;
		this.damageMax = 50;
		this.chanceToBlock = .3;


	}

	public void increaseHitPoints()
	{
		int hPoints;

		hPoints = (int)(Math.random() * (MAX_ADD - MIN_ADD + 1)) + MIN_ADD;
		addHitPoints(hPoints);
		System.out.println(name + " added [" + hPoints + "] points.\n"
				+ "Total hit points remaining are: "
				+ hitPoints);
		System.out.println();

	}

	@Override
	public void attackEnemy(DungeonCharacter opponent)
	{
		System.out.println(name + " casts a spell of fireball at " +
				opponent.getName() + ":");
		super.attackEnemy(opponent);
	}

	public void battleChoices(DungeonCharacter opponent)
	{
		super.battleChoices(opponent);
		int choice;
		Scanner kb = new Scanner(System.in);

		do
		{
			System.out.println("1. Attack Opponent");
			System.out.println("2. Increase Hit Points");
			System.out.print("Choose an option: ");
			choice = kb.nextInt();

			switch (choice)
			{
				case 1: attackEnemy(opponent);
					break;
				case 2: increaseHitPoints();
					break;
				default:
					System.out.println("invalid choice!");
			}//end switch

			numTurns--;
			if (numTurns > 0)
				System.out.println("Number of turns remaining is: " + numTurns);

		} while(numTurns > 0 && hitPoints > 0 && opponent.getHitPoints() > 0);

	}

}