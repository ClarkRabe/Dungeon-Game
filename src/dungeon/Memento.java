package dungeon;

import java.io.*;
import java.util.ArrayList;

public class Memento
{
	private Hero hero;
	private ArrayList<Room> rooms;

	public Memento(Hero hero, ArrayList<Room> rooms)
	{
		this.hero = hero;
		this.rooms = rooms;
	}

	public Memento()
	{
		this.hero = null;
		this.rooms = null;
	}

	public void saveToFile()
	{
		System.out.println("Saving hero and dungeon");
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream("game.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(hero);
			out.writeObject(rooms);
			out.close();
			fileOutputStream.close();
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void loadFromFile()
	{
		Hero h = null;
		ArrayList<Room> r = null;
		System.out.println("Restoring hero and dungeon");
		try
		{
			FileInputStream fileInputStream = new FileInputStream("game.ser");
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			h = (Hero) inputStream.readObject();
			r = (ArrayList<Room>) inputStream.readObject();
			inputStream.close();
			fileInputStream.close();
		}catch (IOException e)
		{
			e.printStackTrace();
		}catch (ClassNotFoundException e)
		{
			System.out.println("Error in loading class");
			e.printStackTrace();
		}
		hero = h;
		rooms = r;
	}

	public Hero getHero()
	{
		return hero;
	}

	public ArrayList<Room> getRooms()
	{
		return rooms;
	}
}
