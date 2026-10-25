package transportata;

import mindustry.content.Blocks;
import mindustry.world.blocks.distribution.Router;

public class BetaRouter extends Router{
    public BetaRouter(String name){
        super(name);
    }

    @Override
    public void load(){
        super.load();
        region = size == 1 ? Blocks.router.region : Blocks.distributor.region;
    }
}
