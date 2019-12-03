package dungeon;

public class Skeleton extends Monster implements Attack
{

	public Skeleton()
	{
		this.name = "Sargath the Skeleton";
		this.hitPoints = 100;
		this.attackSpeed = 3;
		this.chanceToHit = .8;
		this.chanceToHeal = .3;
		this.damageMin = 30;
		this.damageMax = 50;
		this.minHeal = 30;
		this.maxHeal = 50;

	}

	@Override
	public void attackEnemy(DungeonCharacter opponent)
	{
		System.out.println(name + " slices his rusty blade at " +
				opponent.getName() + ":");
		super.attackEnemy(opponent);

	}
}