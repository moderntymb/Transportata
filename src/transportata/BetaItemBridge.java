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
        region = Blocks.itemBridge.region;
        endRegion = Blocks.itemBridge.endRegion;
        bridgeRegion = Blocks.itemBridge.bridgeRegion;
        arrowRegion = Blocks.itemBridge.arrowRegion;
    }
}
