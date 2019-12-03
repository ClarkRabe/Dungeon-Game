package dungeon;

public abstract class Monster extends DungeonCharacter
{
	protected double chanceToHeal;
	protected int minHeal;
	protected int maxHeal;

	public Monster()
	{

	}

	private void heal()
	{
		boolean canHeal;
		int healPoints;

		canHeal = (Math.random() <= chanceToHeal) && (hitPoints > 0);

		if (canHeal)
		{
			healPoints = (int)(Math.random() * (maxHeal - minHeal + 1)) + minHeal;
			addHitPoints(healPoints);
			System.out.println(name + " healed itself for " + healPoints + " points.\n"
					+ "Total hit points remaining are: " + hitPoints);
			System.out.println();
		}


	}

	public void subtractHitPoints(int hitPoints)
	{
		super.subtractHitPoints(hitPoints);
		heal();

	}


}