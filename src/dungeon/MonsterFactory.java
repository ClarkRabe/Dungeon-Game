package dungeon;

import java.util.HashMap;

public class MonsterFactory
{
    private Monster monster;
    private HashMap<String, Monster> map = new HashMap<>();

    public Monster getMonster(String s)
    {

        if(map.containsKey(s))
        {
            monster = map.get(s);
            System.out.println("Already in hashmap");
        }
        else {

            switch (s)
            {
                case "Ogre":
                    monster = new Ogre();
                    break;
                case "Skeleton":
                    monster = new Skeleton();
                    break;
                case "Gremlin":
                    monster = new Gremlin();
                    break;
                case "Zombie":
                    monster = new Zombie();
                    break;
                case "Banshee":
                    monster = new Banshee();
                    break;
                default:
                    monster = new Ogre();
            }
            map.put(monster.name, monster);
        }

        return monster;

    }
    public Monster createMonster()
    {

        int choice = (int)(Math.random() * 5) + 1;

        switch (choice) {
            case 1:
                monster = getMonster("Ogre");
                break;
            case 2:
                monster = getMonster("Skeleton");
                break;
            case 3:
                monster = getMonster("Gremlin");
                break;
            case 4:
                monster = getMonster("Zombie");
                break;
            case 5:
                monster = getMonster("Banshee");
                break;
            default:
                monster = getMonster("Ogre");
        }

        return monster;
    }

}
