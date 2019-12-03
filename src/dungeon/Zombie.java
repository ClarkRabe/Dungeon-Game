package dungeon;

public class Zombie extends Monster implements Attack
{

    public Zombie()
    {
        this.name = "Ziggy the Zombie";
        this.hitPoints = 130;
        this.attackSpeed = 4;
        this.chanceToHit = .6;
        this.chanceToHeal = .1;
        this.damageMin = 35;
        this.damageMax = 55;
        this.minHeal = 5;
        this.maxHeal = 30;

    }//end constructor

    @Override
    public void attackEnemy(DungeonCharacter opponent)
    {
        System.out.println(name + " digs his teeth into " +
                opponent.getName() + ":");
        super.attackEnemy(opponent);

    }//end override of attack


}//end class dungeon.Gremlin