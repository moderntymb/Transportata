package transportata;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import static mindustry.type.ItemStack.with;

public class TransportataBlocks{

    public static Block titaniumRouter1;
    public static Block titaniumRouter2;
    public static Block titaniumRouter3;
    public static Block titaniumJunction1;
    public static Block titaniumJunction2;
    public static Block titaniumJunction3;
    public static Block titaniumBridge1;
    public static Block titaniumBridge2;
    public static Block titaniumBridge3;
    public static Block thoriumRouter1;
    public static Block thoriumRouter2;
    public static Block thoriumRouter3;
    public static Block thoriumJunction1;
    public static Block thoriumJunction2;
    public static Block thoriumJunction3;
    public static Block thoriumBridge1;
    public static Block thoriumBridge2;
    public static Block thoriumBridge3;
    public static Block plastaniumRouter1;
    public static Block plastaniumRouter2;
    public static Block plastaniumRouter3;
    public static Block plastaniumJunction1;
    public static Block plastaniumJunction2;
    public static Block plastaniumJunction3;
    public static Block plastaniumBridge1;
    public static Block plastaniumBridge2;
    public static Block plastaniumBridge3;
    public static Block phaseRouter1;
    public static Block phaseRouter2;
    public static Block phaseRouter3;
    public static Block phaseJunction1;
    public static Block phaseJunction2;
    public static Block phaseJunction3;
    public static Block phaseBridge1;
    public static Block phaseBridge2;
    public static Block phaseBridge3;

    public static void load(){

        // TITANIUM

        titaniumRouter1 = new BetaRouter("titanium-router-1"){{
            requirements(Category.distribution, with(Items.titanium, 3, Items.lead, 2));
            size = 1;
            speed = 6f;
            itemCapacity = 1;
            health = 70;
            alwaysUnlocked = true;
        }};

        titaniumRouter2 = new BetaRouter("titanium-router-2"){{
            requirements(Category.distribution, with(Items.titanium, 12, Items.lead, 8));
            size = 2;
            speed = 6f;
            itemCapacity = 1;
            health = 180;
            alwaysUnlocked = true;
        }};

        titaniumRouter3 = new BetaRouter("titanium-router-3"){{
            requirements(Category.distribution, with(Items.titanium, 27, Items.lead, 18));
            size = 3;
            speed = 6f;
            itemCapacity = 1;
            health = 360;
            alwaysUnlocked = true;
        }};

        titaniumJunction1 = new MultiJunction("titanium-junction-1"){{
            requirements(Category.distribution, with(Items.titanium, 4, Items.lead, 2));
            size = 1;
            speed = 14f;
            laneCapacity = 6;
            health = 70;
            alwaysUnlocked = true;
        }};

        titaniumJunction2 = new MultiJunction("titanium-junction-2"){{
            requirements(Category.distribution, with(Items.titanium, 16, Items.lead, 8));
            size = 2;
            speed = 14f;
            laneCapacity = 6;
            health = 190;
            alwaysUnlocked = true;
        }};

        titaniumJunction3 = new MultiJunction("titanium-junction-3"){{
            requirements(Category.distribution, with(Items.titanium, 36, Items.lead, 18));
            size = 3;
            speed = 14f;
            laneCapacity = 6;
            health = 380;
            alwaysUnlocked = true;
        }};

        titaniumBridge1 = new BetaItemBridge("titanium-bridge-1"){{
            requirements(Category.distribution, with(Items.titanium, 6, Items.lead, 4));
            size = 1;
            range = 6;
            transportTime = 4f;
            itemCapacity = 12;
            health = 80;
            alwaysUnlocked = true;
        }};

        titaniumBridge2 = new BetaItemBridge("titanium-bridge-2"){{
            requirements(Category.distribution, with(Items.titanium, 24, Items.lead, 16));
            size = 2;
            range = 9;
            transportTime = 2.5f;
            itemCapacity = 28;
            health = 220;
            alwaysUnlocked = true;
        }};

        titaniumBridge3 = new BetaItemBridge("titanium-bridge-3"){{
            requirements(Category.distribution, with(Items.titanium, 54, Items.lead, 36));
            size = 3;
            range = 12;
            transportTime = 1.7f;
            itemCapacity = 54;
            health = 440;
            alwaysUnlocked = true;
        }};

        // THORIUM

        thoriumRouter1 = new BetaRouter("thorium-router-1"){{
            requirements(Category.distribution, with(Items.thorium, 3, Items.titanium, 2));
            size = 1;
            speed = 8f;
            itemCapacity = 1;
            health = 70;
            alwaysUnlocked = true;
        }};

        thoriumRouter2 = new BetaRouter("thorium-router-2"){{
            requirements(Category.distribution, with(Items.thorium, 12, Items.titanium, 8));
            size = 2;
            speed = 8f;
            itemCapacity = 1;
            health = 180;
            alwaysUnlocked = true;
        }};

        thoriumRouter3 = new BetaRouter("thorium-router-3"){{
            requirements(Category.distribution, with(Items.thorium, 27, Items.titanium, 18));
            size = 3;
            speed = 8f;
            itemCapacity = 1;
            health = 360;
            alwaysUnlocked = true;
        }};

        thoriumJunction1 = new MultiJunction("thorium-junction-1"){{
            requirements(Category.distribution, with(Items.thorium, 4, Items.titanium, 2));
            size = 1;
            speed = 16f;
            laneCapacity = 6;
            health = 70;
            alwaysUnlocked = true;
        }};

        thoriumJunction2 = new MultiJunction("thorium-junction-2"){{
            requirements(Category.distribution, with(Items.thorium, 16, Items.titanium, 8));
            size = 2;
            speed = 16f;
            laneCapacity = 6;
            health = 190;
            alwaysUnlocked = true;
        }};

        thoriumJunction3 = new MultiJunction("thorium-junction-3"){{
            requirements(Category.distribution, with(Items.thorium, 36, Items.titanium, 18));
            size = 3;
            speed = 16f;
            laneCapacity = 6;
            health = 380;
            alwaysUnlocked = true;
        }};

        thoriumBridge1 = new BetaItemBridge("thorium-bridge-1"){{
            requirements(Category.distribution, with(Items.thorium, 6, Items.titanium, 4));
            size = 1;
            range = 6;
            transportTime = 4f;
            itemCapacity = 12;
            health = 80;
            alwaysUnlocked = true;
        }};

        thoriumBridge2 = new BetaItemBridge("thorium-bridge-2"){{
            requirements(Category.distribution, with(Items.thorium, 24, Items.titanium, 16));
            size = 2;
            range = 9;
            transportTime = 2.5f;
            itemCapacity = 28;
            health = 220;
            alwaysUnlocked = true;
        }};

        thoriumBridge3 = new BetaItemBridge("thorium-bridge-3"){{
            requirements(Category.distribution, with(Items.thorium, 54, Items.titanium, 36));
            size = 3;
            range = 12;
            transportTime = 1.7f;
            itemCapacity = 54;
            health = 440;
            alwaysUnlocked = true;
        }};

        // PLASTANIUM

        plastaniumRouter1 = new BetaRouter("plastanium-router-1"){{
            requirements(Category.distribution, with(Items.plastanium, 3, Items.titanium, 2));
            size = 1;
            speed = 10f;
            itemCapacity = 1;
            health = 70;
            alwaysUnlocked = true;
        }};

        plastaniumRouter2 = new BetaRouter("plastanium-router-2"){{
            requirements(Category.distribution, with(Items.plastanium, 12, Items.titanium, 8));
            size = 2;
            speed = 10f;
            itemCapacity = 1;
            health = 180;
            alwaysUnlocked = true;
        }};

        plastaniumRouter3 = new BetaRouter("plastanium-router-3"){{
            requirements(Category.distribution, with(Items.plastanium, 27, Items.titanium, 18));
            size = 3;
            speed = 10f;
            itemCapacity = 1;
            health = 360;
            alwaysUnlocked = true;
        }};

        plastaniumJunction1 = new MultiJunction("plastanium-junction-1"){{
            requirements(Category.distribution, with(Items.plastanium, 4, Items.titanium, 2));
            size = 1;
            speed = 18f;
            laneCapacity = 6;
            health = 70;
            alwaysUnlocked = true;
        }};

        plastaniumJunction2 = new MultiJunction("plastanium-junction-2"){{
            requirements(Category.distribution, with(Items.plastanium, 16, Items.titanium, 8));
            size = 2;
            speed = 18f;
            laneCapacity = 6;
            health = 190;
            alwaysUnlocked = true;
        }};

        plastaniumJunction3 = new MultiJunction("plastanium-junction-3"){{
            requirements(Category.distribution, with(Items.plastanium, 36, Items.titanium, 18));
            size = 3;
            speed = 18f;
            laneCapacity = 6;
            health = 380;
            alwaysUnlocked = true;
        }};

        plastaniumBridge1 = new BetaItemBridge("plastanium-bridge-1"){{
            requirements(Category.distribution, with(Items.plastanium, 6, Items.titanium, 4));
            size = 1;
            range = 6;
            transportTime = 4f;
            itemCapacity = 12;
            health = 80;
            alwaysUnlocked = true;
        }};

        plastaniumBridge2 = new BetaItemBridge("plastanium-bridge-2"){{
            requirements(Category.distribution, with(Items.plastanium, 24, Items.titanium, 16));
            size = 2;
            range = 9;
            transportTime = 2.5f;
            itemCapacity = 28;
            health = 220;
            alwaysUnlocked = true;
        }};

        plastaniumBridge3 = new BetaItemBridge("plastanium-bridge-3"){{
            requirements(Category.distribution, with(Items.plastanium, 54, Items.titanium, 36));
            size = 3;
            range = 12;
            transportTime = 1.7f;
            itemCapacity = 54;
            health = 440;
            alwaysUnlocked = true;
        }};

        // PHASE

        phaseRouter1 = new BetaRouter("phase-router-1"){{
            requirements(Category.distribution, with(Items.phaseFabric, 3, Items.silicon, 2));
            size = 1;
            speed = 12f;
            itemCapacity = 1;
            health = 70;
            alwaysUnlocked = true;
        }};

        phaseRouter2 = new BetaRouter("phase-router-2"){{
            requirements(Category.distribution, with(Items.phaseFabric, 12, Items.silicon, 8));
            size = 2;
            speed = 12f;
            itemCapacity = 1;
            health = 180;
            alwaysUnlocked = true;
        }};

        phaseRouter3 = new BetaRouter("phase-router-3"){{
            requirements(Category.distribution, with(Items.phaseFabric, 27, Items.silicon, 18));
            size = 3;
            speed = 12f;
            itemCapacity = 1;
            health = 360;
            alwaysUnlocked = true;
        }};

        phaseJunction1 = new MultiJunction("phase-junction-1"){{
            requirements(Category.distribution, with(Items.phaseFabric, 4, Items.silicon, 2));
            size = 1;
            speed = 20f;
            laneCapacity = 6;
            health = 70;
            alwaysUnlocked = true;
        }};

        phaseJunction2 = new MultiJunction("phase-junction-2"){{
            requirements(Category.distribution, with(Items.phaseFabric, 16, Items.silicon, 8));
            size = 2;
            speed = 20f;
            laneCapacity = 6;
            health = 190;
            alwaysUnlocked = true;
        }};

        phaseJunction3 = new MultiJunction("phase-junction-3"){{
            requirements(Category.distribution, with(Items.phaseFabric, 36, Items.silicon, 18));
            size = 3;
            speed = 20f;
            laneCapacity = 6;
            health = 380;
            alwaysUnlocked = true;
        }};

        phaseBridge1 = new BetaItemBridge("phase-bridge-1"){{
            requirements(Category.distribution, with(Items.phaseFabric, 6, Items.silicon, 4));
            size = 1;
            range = 6;
            transportTime = 4f;
            itemCapacity = 12;
            health = 80;
            alwaysUnlocked = true;
        }};

        phaseBridge2 = new BetaItemBridge("phase-bridge-2"){{
            requirements(Category.distribution, with(Items.phaseFabric, 24, Items.silicon, 16));
            size = 2;
            range = 9;
            transportTime = 2.5f;
            itemCapacity = 28;
            health = 220;
            alwaysUnlocked = true;
        }};

        phaseBridge3 = new BetaItemBridge("phase-bridge-3"){{
            requirements(Category.distribution, with(Items.phaseFabric, 54, Items.silicon, 36));
            size = 3;
            range = 12;
            transportTime = 1.7f;
            itemCapacity = 54;
            health = 440;
            alwaysUnlocked = true;
        }};
    }
}
