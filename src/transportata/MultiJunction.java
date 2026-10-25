package transportata;

import arc.struct.IntMap;
import arc.struct.Seq;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.gen.Building;
import mindustry.gen.Teamc;
import mindustry.type.Item;
import mindustry.world.Edges;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.Junction;

/**
 * Junction with one independent straight-through lane for every tile along each edge.
 * A 2x2 block therefore has 4 independent conveyor lines; a 3x3 block has 6.
 * This is intentionally simple beta code: items currently inside the junction are not
 * persisted across save/load yet.
 */
public class MultiJunction extends Junction{
    public int laneCapacity = 6;

    public MultiJunction(String name){
        super(name);
    }

    @Override
    public void load(){
        super.load();
        region = Blocks.junction.region;
    }

    public class MultiJunctionBuild extends JunctionBuild{
        private final IntMap<Seq<LaneItem>> lanes = new IntMap<>();

        @Override
        public void updateTile(){
            for(IntMap.Entry<Seq<LaneItem>> entry : lanes){
                Seq<LaneItem> queue = entry.value;
                if(queue.isEmpty()) continue;

                LaneItem first = queue.first();
                if(Time.time < first.entered + speed / timeScale) continue;

                Building dest = Vars.world.build(first.outX, first.outY);
                if(dest == null || dest.team != team || !dest.acceptItem(this, first.item)) continue;

                dest.handleItem(this, first.item);
                queue.remove(0);
            }
        }

        @Override
        public int acceptStack(Item item, int amount, Teamc source){
            return 0;
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            Port port = portFor(source);
            if(port == null) return false;

            Seq<LaneItem> queue = lanes.get(port.key);
            if(queue != null && queue.size >= laneCapacity) return false;

            Building dest = Vars.world.build(port.outX, port.outY);
            return dest != null && dest.team == team;
        }

        @Override
        public void handleItem(Building source, Item item){
            Port port = portFor(source);
            if(port == null) return;

            Seq<LaneItem> queue = lanes.get(port.key);
            if(queue == null){
                queue = new Seq<>();
                lanes.put(port.key, queue);
            }
            queue.add(new LaneItem(item, Time.time, port.outX, port.outY));
        }

        private Port portFor(Building source){
            Tile edge = Edges.getFacingEdge(source, this);
            if(edge == null) return null;

            int dx = Integer.signum(edge.x - source.tileX());
            int dy = Integer.signum(edge.y - source.tileY());

            if(dx != 0 && dy != 0) return null;
            if(dx == 0 && dy == 0) return null;

            int outX = edge.x + dx * size;
            int outY = edge.y + dy * size;
            int key = edge.pos();

            return new Port(key, outX, outY);
        }
    }

    private static class Port{
        final int key, outX, outY;
        Port(int key, int outX, int outY){
            this.key = key;
            this.outX = outX;
            this.outY = outY;
        }
    }

    private static class LaneItem{
        final Item item;
        final float entered;
        final int outX, outY;
        LaneItem(Item item, float entered, int outX, int outY){
            this.item = item;
            this.entered = entered;
            this.outX = outX;
            this.outY = outY;
        }
    }
                 }
