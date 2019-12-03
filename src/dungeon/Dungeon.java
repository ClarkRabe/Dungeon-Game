package dungeon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Dungeon implements Serializable
{
	private String topRoom = "*-* ";
	private String sideRoom = "| ";
	private String sideBorderRoom = "*";
	private String topBorderRoom = "*** ";

	private int roomWidth = 5;
	private int roomHeight = 5;
	private Hero hero;

	private ArrayList<Room> rooms;

	public Dungeon(Hero hero)
	{
		this.hero = hero;
		rooms = new ArrayList<>();
		generateRooms();
	}

	public Dungeon(Hero hero, boolean toRestore)
	{
		this.hero = hero;
		rooms = new ArrayList<>();
	}

	public ArrayList<Room> getRooms()
	{
		return rooms;
	}

	public Room getRoom()
	{
		int y = hero.getyPos();
		int adjY = y *5;
		int x = hero.getxPos();
		int loc = adjY+x;
		Room room = rooms.get(loc);
		return room;
	}

	public void restoreRooms(ArrayList<Room> oldRooms)
	{
		rooms.clear();
		for(Room r: oldRooms)
		{
			rooms.add(r);
		}
	}

	private void generateRooms()
	{
		boolean monster = false;
		boolean pit = false;
		boolean visionPotion = false;
		boolean healPotion = false;
		boolean pillar = false;
		ArrayList<Integer> xInts = new ArrayList<>();
		xInts.add(1);
		xInts.add(2);
		xInts.add(3);
		ArrayList<Integer> yInts = new ArrayList<>();
		yInts.add(1);
		yInts.add(2);
		yInts.add(3);
		Collections.shuffle(xInts);
		Collections.shuffle(yInts);
		int pillar1X = xInts.get(0);
		int pillar1Y = yInts.get(0);
		int pillar2X = xInts.get(1);
		int pillar2Y = yInts.get(1);
		int pillar3X = xInts.get(2);
		int pillar3Y = yInts.get(2);
		int pillar4X = xInts.get(0);
		int pillar4Y = yInts.get(1);
		for(int x = 0; x<roomWidth; x++)
		{
			for(int y = 0; y<roomHeight; y++)
			{
				if(x==0 && y==0)
				{
					Room r = new Room(false,false,false,false,false,true,false, x, y);
					rooms.add(r);
				}
				else if(x==4 && y==4)
				{
					Room r = new Room(false,false, false,false,false,false,true, x, y);
					rooms.add(r);
				}
				else
				{
					if(Math.random() <= .1)
					{
						pit = true;
					}
					if(Math.random() <= .1)
					{
						visionPotion = true;
					}
					if(Math.random() <= .1)
					{
						healPotion = true;
					}
					if(Math.random() <= .2)
					{
						monster = true;
					}
					if((x==pillar1X && y==pillar1Y) || (x==pillar2X && y==pillar2Y) || (x==pillar3X && y==pillar3Y) || (x==pillar4X && y==pillar4Y))
					{
						pillar = true;
					}
					Room r = new Room(pillar, monster,pit,visionPotion,healPotion,false,false, x, y);
					rooms.add(r);
				}
				pit = false;
				visionPotion = false;
				healPotion = false;
				monster = false;
				pillar = false;
			}
		}
	}

	public String printDungeon(int startX, int startY, int endX, int endY)
	{
		StringBuilder pattern = new StringBuilder();

		for(int x = startX; x<=endX; x++)
		{
			if(x != 0)
			{
				for (int i = 0; i <=endX; i++)
				{
					pattern.append(topRoom);
				}
			}
			else
			{
				for(int i = startX; i<=endX; i++)
				{
					pattern.append(topBorderRoom);
				}
			}
			pattern.append("\n");
			for(int y = startY; y<=endY; y++)
			{
				if(y == 0)
				{
					pattern.append(sideBorderRoom);
				}
				int adjY = y *5;
				int loc = adjY+x;
				pattern.append(rooms.get(loc).toString());
				if(y == 4)
				{
					pattern.append(sideBorderRoom);
				}
				else
					pattern.append(sideRoom);
			}
			pattern.append("\n");
		}
		if(endY==4)
		{
			for (int i = startX; i <= endX; i++)
			{
				pattern.append(topBorderRoom);
			}
		}
		else
		{
			for (int i = startX; i <= endX; i++)
			{
				pattern.append(topRoom);
			}
		}
		return pattern.toString();
	}


	public String activateVisionPotion()
	{
		int startX;
		int startY;
		int endX;
		int endY;

		int y = hero.getyPos();
		int x = hero.getxPos();

		if(x==0 && y==0)
		{
			startX = x;
			startY = y;
			endX = x+1;
			endY = y+1;
		}
		else if(x==0 && y==4)
		{
			startX = x;
			startY = y-1;
			endX = x+1;
			endY = y;
		}
		else if(x==4 && y==0)
		{
			startX = x-1;
			startY = y;
			endX = x;
			endY = y+1;
		}
		else if(x==4 && y==4)
		{
			startX = x-1;
			startY = y-1;
			endX = x;
			endY = y;
		}
		else if(x==0)
		{
			startX = x;
			startY = y-1;
			endX = x+1;
			endY = y+1;
		}
		else if(x==4)
		{
			startX = x-1;
			startY = y-1;
			endX = x;
			endY = y+1;
		}
		else if(y==0)
		{
			startX = x-1;
			startY = y;
			endX = x+1;
			endY = x+1;
		}
		else if(y==4)
		{
			startX = x-1;
			startY = y-1;
			endX = x+1;
			endY = y;
		}
		else
		{
			startX = x-1;
			startY = y-1;
			endX = x+1;
			endY = y+1;
		}

		return printDungeon(startX, startY,endX,endY);
	}

	@Override
	public String toString()
	{
		int y = hero.getyPos();
		int adjY = y *5;
		int x = hero.getxPos();
		int loc = adjY+x;
		Room room = rooms.get(loc);
		StringBuilder pattern = new StringBuilder();
		if(x==0 && y==0)
		{
			pattern.append(topBorderRoom);
			pattern.append("\n" + sideBorderRoom);
			pattern.append(room.toString());
			pattern.append(sideRoom);
			pattern.append("\n" + topRoom);
		}
		else if(x==0 && y==4)
		{
			pattern.append(topRoom);
			pattern.append("\n" + sideBorderRoom);
			pattern.append(room.toString());
			pattern.append(sideRoom);
			pattern.append("\n" + topBorderRoom);
		}
		else if(x==4 && y==0)
		{
			pattern.append(topBorderRoom);
			pattern.append("\n" + sideRoom);
			pattern.append(room.toString());
			pattern.append(sideBorderRoom);
			pattern.append("\n" + topRoom);
		}
		else if(x==4 && y==4)
		{
			pattern.append(topRoom);
			pattern.append("\n" + sideRoom);
			pattern.append(room.toString());
			pattern.append(sideBorderRoom);
			pattern.append("\n" + topBorderRoom);
		}
		else if(x==0)
		{
			pattern.append(topRoom);
			pattern.append("\n" + sideBorderRoom);
			pattern.append(room.toString());
			pattern.append(sideRoom);
			pattern.append("\n" + topRoom);
		}
		else if(x==4)
		{
			pattern.append(topRoom);
			pattern.append("\n" + sideRoom);
			pattern.append(room.toString());
			pattern.append(sideBorderRoom);
			pattern.append("\n" + topRoom);
		}
		else if(y==0)
		{
			pattern.append(topBorderRoom);
			pattern.append("\n" + sideRoom);
			pattern.append(room.toString());
			pattern.append(sideRoom);
			pattern.append("\n" + topRoom);
		}
		else if(y==4)
		{
			pattern.append(topRoom);
			pattern.append("\n" + sideRoom);
			pattern.append(room.toString());
			pattern.append(sideRoom);
			pattern.append("\n" + topBorderRoom);
		}
		else
		{
			pattern.append(topRoom);
			pattern.append("\n" + sideRoom);
			pattern.append(room.toString());
			pattern.append(sideRoom);
			pattern.append("\n" + topRoom);
		}
		return pattern.toString();
	}
}
