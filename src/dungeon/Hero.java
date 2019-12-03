package dungeon;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Hero extends DungeonCharacter implements Serializable
{
	protected double chanceToBlock;
	protected int numTurns;
	protected int healingPotions, visionPotions, pillars;
	protected boolean usingVision = false;
	private int xPos;

	public void setxPos(int xPos)
	{
		this.xPos = xPos;
	}

	public void setyPos(int yPos)
	{
		this.yPos = yPos;
	}

	private int yPos;

	public int getxPos()
	{
		return xPos;
	}

	public int getyPos()
	{
		return yPos;
	}

	private static Scanner kb = new Scanner(System.in);

	public Hero()
	{
		readName();
		this.healingPotions = 0;
		this.visionPotions = 0;
		this.pillars = 0;
	}

	private void readName()
	{
		System.out.print("Enter character name: ");
		this.name = kb.nextLine();
	}

	private boolean defend()
	{
		return Math.random() <= chanceToBlock;

	}

	public void subtractHitPoints(int hitPoints)
	{
		if (defend())
		{
			System.out.println(name + " BLOCKED the attack!");
		}
		else
		{
			super.subtractHitPoints(hitPoints);
		}


	}

	public void battleChoices(DungeonCharacter opponent)
	{
		numTurns = attackSpeed/opponent.getAttackSpeed();

		if (numTurns == 0)
			numTurns++;

		System.out.println("Number of turns this round is: " + numTurns);

	}

	public void useHealingPotion()
	{
		if(this.healingPotions > 0) {
			this.addHitPoints(50);
			System.out.println( this.getName() + " uses healing potion\n" +
					this.getName() + " was healed 50HP!\n" +
					this.getName() + " now has " + this.getHitPoints());
			this.healingPotions--;
		}
		else
			System.out.println("No healing potions left\n");

	}

	public void useVisionPotion()
	{
		if(this.visionPotions > 0)
		{
			this.usingVision = true;
			this.addHitPoints(50);
			System.out.println( this.getName() + " uses vision potion!\n");
			this.visionPotions--;

		}
		else
			System.out.println("No vision potions left\n");
	}

}