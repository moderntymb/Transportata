package transportata;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import static mindustry.type.ItemStack.with;

public class TransportataBlocks{
    public static Block titaniumRouter1, titaniumRouter2, titaniumRouter3;
    public static Block titaniumJunction1, titaniumJunction2, titaniumJunction3;
    public static Block titaniumBridge1, titaniumBridge2, titaniumBridge3;

    public static void load(){
        titaniumRouter1 = router("titanium-router-1", 1, 6f, 70);
        titaniumRouter2 = router("titanium-router-2", 2, 6f, 180);
        titaniumRouter3 = router("titanium-router-3", 3, 6f, 360);

        titaniumJunction1 = junction("titanium-junction-1", 1, 14f, 6, 70);
        titaniumJunction2 = junction("titanium-junction-2", 2, 14f, 6, 190);
        titaniumJunction3 = junction("titanium-junction-3", 3, 14f, 6, 380);

        titaniumBridge1 = bridge("titanium-bridge-1", 1, 6, 4f, 12, 80);
        titaniumBridge2 = bridge("titanium-bridge-2", 2, 9, 2.5f, 28, 220);
        titaniumBridge3 = bridge("titanium-bridge-3", 3, 12, 1.7f, 54, 440);
    }

    private static Block router(String name, int size, float speed, int health){
        return new BetaRouter(name){{
            requirements(Category.distribution, with(Items.titanium, 3 * size * size, Items.lead, 2 * size * size));
            this.size = size;
            this.speed = speed;
            this.health = health;
            itemCapacity = 1;
            alwaysUnlocked = true;
        }};
    }

    private static Block junction(String name, int size, float delay, int laneCapacity, int health){
        return new MultiJunction(name){{
            requirements(Category.distribution, with(Items.titanium, 4 * size * size, Items.lead, 2 * size * size));
            this.size = size;
            this.speed = delay;
            this.laneCapacity = laneCapacity;
            this.health = health;
            alwaysUnlocked = true;
        }};
    }

    private static Block bridge(String name, int size, int range, float transportTime, int capacity, int health){
        return new BetaItemBridge(name){{
            requirements(Category.distribution, with(Items.titanium, 6 * size * size, Items.lead, 4 * size * size));
            this.size = size;
            this.range = range;
            this.transportTime = transportTime;
            this.itemCapacity = capacity;
            this.health = health;
            alwaysUnlocked = true;
        }};
    }
}
