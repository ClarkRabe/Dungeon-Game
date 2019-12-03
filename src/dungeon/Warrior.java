package dungeon;

import java.util.Scanner;

public class Warrior extends Hero implements Attack
{

	public Warrior()
	{
		this.name = getName();
		this.hitPoints = 125;
		this.attackSpeed = 4;
		this.chanceToHit = .8;
		this.damageMin = 35;
		this.damageMax = 60;
		this.chanceToBlock = .2;
	}


	public void crushingBlow(DungeonCharacter opponent)
	{
		if (Math.random() <= .4)
		{
			int blowPoints = (int)(Math.random() * 76) + 100;
			System.out.println(name + " lands a CRUSHING BLOW for " + blowPoints
					+ " damage!");
			opponent.subtractHitPoints(blowPoints);
		}
		else
		{
			System.out.println(name + " failed to land a crushing blow");
			System.out.println();
		}

	}

	@Override
	public void attackEnemy(DungeonCharacter opponent)
	{
		System.out.println(name + " swings a mighty sword at " +
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
			System.out.println("2. Crushing Blow on Opponent");
			System.out.print("Choose an option: ");
			choice = kb.nextInt();

			switch (choice)
			{
				case 1: attackEnemy(opponent);
					break;
				case 2: crushingBlow(opponent);
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