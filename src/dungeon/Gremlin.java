package dungeon;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Gremlin extends Monster implements Attack
{

	public Gremlin()
	{
		this.name = "Gnarltooth the Gremlin";
		this.hitPoints = 70;
		this.attackSpeed = 5;
		this.chanceToHit = .8;
		this.chanceToHeal = .4;
		this.damageMin = 15;
		this.damageMax = 30;
		this.minHeal = 20;
		this.maxHeal = 40;

	}//end constructor

	@Override
	public void attackEnemy(DungeonCharacter opponent)
	{
		System.out.println(name + " jabs his kris at " +
				opponent.getName() + ":");
		super.attackEnemy(opponent);

	}//end override of attack


}//end class dungeon.Gremlin