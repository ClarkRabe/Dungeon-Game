package dungeon;

import java.util.HashMap;
import java.util.Scanner;

public class HeroFactory
{

    private Hero hero;
    private HashMap<String, Hero> map = new HashMap<>();

    public Hero getHero(String s)
    {
        if(map.containsKey(s))
        {
            hero = map.get(s);
            System.out.println("Already in hashmap");
        }
        else
        {
            switch (s)
            {
                case "Warrior":
                    hero = new Warrior();
                    break;
                case "Archer":
                    hero = new Archer();
                    break;
                case "Brute":
                    hero = new Brute();
                    break;
                case "Sorceress":
                    hero = new Sorceress();
                    break;
                case "Thief":
                    hero = new Thief();
                    break;
                default:
                    hero = new Warrior();
            }
            map.put(hero.name, hero);
        }

        return hero;


    }
    public Hero createHero()
    {

		Scanner kb = new Scanner(System.in);
        System.out.println("Choose a hero:\n" +
                "1. Warrior\n" +
                "2. Sorceress\n" +
                "3. Thief\n" +
                "4. Archer\n" +
                "5. Brute\n");

        int choice = kb.nextInt();


        switch (choice)
        {
            case 1:
                return hero = getHero("Warrior");
            case 2:
                return hero = getHero("Sorceress");
            case 3:
                return hero = getHero("Thief");
            case 4:
                return hero = getHero("Archer");
            case 5:
                return hero = getHero("Brute");
            default:
                System.out.println("invalid choice, returning Thief");
                return hero;
        }
    }
}
