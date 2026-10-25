package transportata;

import mindustry.content.Blocks;
import mindustry.world.blocks.distribution.ItemBridge;

public class BetaItemBridge extends ItemBridge{

    public BetaItemBridge(String name){
        super(name);
    }

    @Override
    public void load(){
        super.load();

        ItemBridge vanilla = (ItemBridge)Blocks.itemBridge;

        region = vanilla.region;
        endRegion = vanilla.endRegion;
        bridgeRegion = vanilla.bridgeRegion;
        arrowRegion = vanilla.arrowRegion;
    }
}
