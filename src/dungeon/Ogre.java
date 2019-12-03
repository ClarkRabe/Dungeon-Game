package dungeon;

public class Ogre extends Monster implements Attack
{

	public Ogre()
	{
		this.name = "Shrek the Ogre";
		this.hitPoints = 200;
		this.attackSpeed = 2;
		this.chanceToHit = .6;
		this.chanceToHeal = .1;
		this.damageMin = 30;
		this.damageMax = 50;
		this.minHeal = 30;
		this.maxHeal = 50;
	}


	@Override
	public void attackEnemy(DungeonCharacter opponent)
	{
		System.out.println(name + " slowly swings a club towards " + opponent.getName() + ":");
		super.attackEnemy(opponent);
	}
}