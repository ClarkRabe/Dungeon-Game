package dungeon;

import java.io.Serializable;

public abstract class DungeonCharacter implements Attack, Serializable
{

	protected String name;
	protected int hitPoints;
	protected int attackSpeed;
	protected double chanceToHit;
	protected int damageMin;
	protected int damageMax;


	public DungeonCharacter()
	{

	}

	public String getName()
	{
		return name;
	}

	public int getHitPoints()
	{
		return hitPoints;
	}
	public int getAttackSpeed()
	{
		return attackSpeed;
	}


	public void addHitPoints(int hitPoints)
	{
		if (hitPoints <=0)
			System.out.println("Hitpoint amount must be positive.");
		else
		{
			this.hitPoints += hitPoints;

		}
	}
	public void subtractHitPoints(int hitPoints)
	{
		if (hitPoints <0)
			System.out.println("Hitpoint amount must be positive.");
		else if (hitPoints >0)
		{
			this.hitPoints -= hitPoints;
			if (this.hitPoints < 0)
				this.hitPoints = 0;
			System.out.println(getName() + " hit " +
					" for <" + hitPoints + "> points damage.");
			System.out.println(getName() + " now has " +
					getHitPoints() + " hit points remaining.");
			System.out.println();
		}//end else if

		if (this.hitPoints == 0)
			System.out.println(name + " has been killed :-(");


	}
	public boolean isAlive()
	{
		return (hitPoints > 0);
	}

	public void attackEnemy(DungeonCharacter opponent)
	{
		boolean canAttack;
		int damage;

		canAttack = Math.random() <= chanceToHit;

		if (canAttack)
		{
			damage = (int)(Math.random() * (damageMax - damageMin + 1))
					+ damageMin ;
			opponent.subtractHitPoints(damage);



			System.out.println();
		}
		else
		{

			System.out.println(getName() + "'s attack on " + opponent.getName() +
					" failed!");
			System.out.println();
		}
	}


}