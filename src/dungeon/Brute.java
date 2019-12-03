package dungeon;

import java.util.Scanner;

public class Brute extends Hero implements Attack
{

    public Brute()
    {
    	this.name = getName();
        this.hitPoints = 100;
        this.attackSpeed = 2;
        this.chanceToHit = .9;
        this.damageMin = 50;
        this.damageMax = 85;
        this.chanceToBlock = .4;
    }


	private void barrelCharge(DungeonCharacter opponent)
	{
		if (Math.random() <= .4)
		{
			int blowPoints = (int)(Math.random() * 76) + 100;
			System.out.println(name + " BARREL CHARGES for " + blowPoints
					+ " damage!");
			opponent.subtractHitPoints(blowPoints);
		}//end blow succeeded
		else
		{
			System.out.println(name + " failed to land BARREL CHARGE");
			System.out.println();
		}//blow failed

	}

    @Override
    public void attackEnemy(DungeonCharacter opponent)
    {
        System.out.println(name + " swings a mighty hammer at " +
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
            System.out.println("2. Barrel Charge at Opponent");
            System.out.print("Choose an option: ");
            choice = kb.nextInt();

            switch (choice)
            {
                case 1: attackEnemy(opponent);
                    break;
                case 2: barrelCharge(opponent);
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
