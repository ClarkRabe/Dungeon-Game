package dungeon;

public class Banshee extends Monster implements Attack
{

    public Banshee()
    {
        this.name = "Bridget the Banshee";
        this.hitPoints = 90;
        this.attackSpeed = 5;
        this.chanceToHit = .7;
        this.chanceToHeal = .6;
        this.damageMin = 20;
        this.damageMax = 65;
        this.minHeal = 20;
        this.maxHeal = 60;

    }//end constructor

    @Override
    public void attackEnemy(DungeonCharacter opponent)
    {
        System.out.println(name + " waves her wand at " +
                opponent.getName() + ":");
        super.attackEnemy(opponent);

    }//end override of attack


}//end class dungeon.Gremlin