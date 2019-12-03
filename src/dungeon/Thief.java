package dungeon;

import java.util.Scanner;

public class Thief extends Hero
{

	public Thief()
	{
		this.name = getName();
		this.hitPoints = 75;
		this.attackSpeed = 6;
		this.chanceToHit = .8;
		this.damageMin = 20;
		this.damageMax = 40;
		this.chanceToBlock = .5;
	}

	public void surpriseAttack(DungeonCharacter opponent)
	{
		double surprise = Math.random();
		if (surprise <= .4)
		{
			System.out.println("Surprise attack was successful!\n" +
					name + " gets an additional turn.");
			numTurns++;
			attackEnemy(opponent);
		}
		else if (surprise >= .9)
		{
			System.out.println("Uh oh! " + opponent.getName() + " saw you and" +
					" blocked your attack!");
		}
		else
			attackEnemy(opponent);
	}


	public void battleChoices(DungeonCharacter opponent)
	{
		super.battleChoices(opponent);
		int choice;
		Scanner kb = new Scanner(System.in);

		do
		{
			System.out.println("1. Attack Opponent");
			System.out.println("2. Surprise dungeon.Attack");
			System.out.print("Choose an option: ");
			choice = kb.nextInt();

			switch (choice)
			{
				case 1: attackEnemy(opponent);
					break;
				case 2: surpriseAttack(opponent);
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