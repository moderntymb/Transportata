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

    public static void load(){

        // =========================
        // ROUTERS
        // =========================

        titaniumRouter1 = new BetaRouter("titanium-router-1"){{
            requirements(
                Category.distribution,
                with(
                    Items.titanium, 3,
                    Items.lead, 2
                )
            );

            size = 1;
            speed = 6f;
            health = 70;

            itemCapacity = 1;
            alwaysUnlocked = true;
        }};


        titaniumRouter2 = new BetaRouter("titanium-router-2"){{
            requirements(
                Category.distribution,
                with(
                    Items.titanium, 12,
                    Items.lead, 8
                )
            );

            size = 2;
            speed = 6f;
            health = 180;

            itemCapacity = 1;
            alwaysUnlocked = true;
        }};


        titaniumRouter3 = new BetaRouter("titanium-router-3"){{
            requirements(
                Category.distribution,
                with(
                    Items.titanium, 27,
                    Items.lead, 18
                )
            );

            size = 3;
            speed = 6f;
            health = 360;

            itemCapacity = 1;
            alwaysUnlocked = true;
        }};


        // =========================
        // JUNCTIONS
        // =========================

        titaniumJunction1 = new MultiJunction("titanium-junction-1"){{
            requirements(
                Category.distribution,
                with(
                    Items.titanium, 4,
                    Items.lead, 2
                )
            );

            size = 1;
            speed = 14f;
            laneCapacity = 6;
            health = 70;

            alwaysUnlocked = true;
        }};


        titaniumJunction2 = new MultiJunction("titanium-junction-2"){{
            requirements(
                Category.distribution,
                with(
                    Items.titanium, 16,
                    Items.lead, 8
                )
            );

            size = 2;
            speed = 14f;
            laneCapacity = 6;
            health = 190;

            alwaysUnlocked = true;
        }};


        titaniumJunction3 = new MultiJunction("titanium-junction-3"){{
            requirements(
                Category.distribution,
                with(
                    Items.titanium, 36,
                    Items.lead, 18
                )
            );

            size = 3;
            speed = 14f;
            laneCapacity = 6;
            health = 380;

            alwaysUnlocked = true;
        }};


        // =========================
        // ITEM BRIDGES
        // =========================

        titaniumBridge1 = new BetaItemBridge("titanium-bridge-1"){{
            requirements(
                Category.distribution,
                with(
                    Items.titanium, 6,
                    Items.lead, 4
                )
            );

            size = 1;

            range = 6;
            transportTime = 4f;
            itemCapacity = 12;

            health = 80;
            alwaysUnlocked = true;
        }};


        titaniumBridge2 = new BetaItemBridge("titanium-bridge-2"){{
            requirements(
                Category.distribution,
                with(
                    Items.titanium, 24,
                    Items.lead, 16
                )
            );

            size = 2;

            range = 9;
            transportTime = 2.5f;
            itemCapacity = 28;

            health = 220;
            alwaysUnlocked = true;
        }};


        titaniumBridge3 = new BetaItemBridge("titanium-bridge-3"){{
            requirements(
                Category.distribution,
                with(
                    Items.titanium, 54,
                    Items.lead, 36
                )
            );

            size = 3;

            range = 12;
            transportTime = 1.7f;
            itemCapacity = 54;

            health = 440;
            alwaysUnlocked = true;
        }};
    }
                }
