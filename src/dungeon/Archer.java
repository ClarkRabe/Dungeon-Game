package dungeon;

import java.util.Scanner;

public class Archer extends Hero implements Attack
{

	public Archer()
	{
		this.name = getName();
		this.hitPoints = 75;
		this.attackSpeed = 5;
		this.chanceToHit = .6;
		this.damageMin = 20;
		this.damageMax = 70;
		this.chanceToBlock = .3;
	}


	public void soaringArrow(DungeonCharacter opponent)
	{
		if (Math.random() <= .4)
		{
			int blowPoints = (int)(Math.random() * 76) + 100;
			System.out.println(name + " fires off a SOARING ARROW for " + blowPoints
					+ " damage!");
			opponent.subtractHitPoints(blowPoints);
		}//end blow succeeded
		else
		{
			System.out.println(name + " failed to fire SOARING ARROW");
			System.out.println();
		}//blow failed

	}

	@Override
	public void attackEnemy(DungeonCharacter opponent)
	{
		System.out.println(name + " fire an arrow at " +
				opponent.getName() + ":");
		super.attackEnemy(opponent);
	}




	public void battleChoices(DungeonCharacter opponent)
	{
		int choice;

		super.battleChoices(opponent);

		Scanner kb = new Scanner(System.in);
		do
		{
			System.out.println("1. Attack Opponent");
			System.out.println("2. Soaring Arrow at Opponent");
			System.out.print("Choose an option: ");
			choice = kb.nextInt();

			switch (choice)
			{
				case 1: attackEnemy(opponent);
					break;
				case 2: soaringArrow(opponent);
					break;
				default:
					System.out.println("invalid choice!");
			}//end switch

			numTurns--;
			if (numTurns > 0)
				System.out.println("Number of turns remaining is: " + numTurns);

		} while(numTurns > 0);

	}

}
