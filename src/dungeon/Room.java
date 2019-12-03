package dungeon;

import java.io.Serializable;

public class Room implements Serializable
{
	private int XPos;
	private int YPos;
	private boolean monster;
	private boolean pit;
	private boolean visionPotion;
	private boolean healPotion;
	private boolean entrance;
	private boolean exit;
	private boolean pillar;
	private boolean multiple;

	public Room(boolean pillar, boolean monster, boolean pit, boolean visionPotion, boolean healPotion, boolean entrance, boolean exit, int XPos, int YPos)
	{
		this.XPos = XPos;
		this.YPos = YPos;
		this.pillar =pillar;
		this.monster = monster;
		this.pit = pit;
		this.visionPotion = visionPotion;
		this.healPotion = healPotion;
		this.entrance = entrance;
		this.exit = exit;
	}

	@Override
	public String toString()
	{
		String letter;
		multiple = checkMultiple();
		if(!multiple)
		{
			if(pillar)
			{
				letter = "J";
			}
			else if(monster)
			{
				letter = "X";
			}
			else if(pit)
			{
				letter = "P";
			}
			else if(visionPotion)
			{
				letter = "V";
			}
			else if(healPotion)
			{
				letter = "H";
			}
			else if(entrance)
			{
				letter = "I";
			}
			else if(exit)
			{
				letter = "O";
			}
			else
				letter = "E";
		}
		else
		{
			letter = "M";
		}
		return letter ;
	}

	private boolean checkMultiple()
	{
		if(monster && (pillar || pit || visionPotion || healPotion))
			return true;
		else if(pillar && (monster || pit || visionPotion || healPotion))
			return true;
		else if(pit && (monster || pillar || visionPotion || healPotion))
			return true;
		else if(visionPotion && (monster || pit || pillar || healPotion))
			return true;
		else
			return false;
	}

	public boolean isMonster() {
		return monster;
	}

	public void setMonster(boolean monster) {
		this.monster = monster;
	}

	public boolean isPit() {
		return pit;
	}

	public boolean isVisionPotion() {
		return visionPotion;
	}

	public void setVisionPotion(boolean visionPotion) {
		this.visionPotion = visionPotion;
	}

	public boolean isHealPotion() {
		return healPotion;
	}

	public void setHealPotion(boolean healPotion) {
		this.healPotion = healPotion;
	}

	public boolean isExit() {
		return exit;
	}

	public boolean isPillar() {
		return pillar;
	}

	public void setPillar(boolean pillar) {
		this.pillar = pillar;
	}
}
