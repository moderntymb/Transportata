package transportata;

import arc.struct.IntMap;
import arc.struct.Seq;
import arc.util.Time;

import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.Teamc;
import mindustry.type.Item;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.Junction;

public class MultiJunction extends Junction{

    // Скільки предметів може чекати в одній окремій лінії.
    public int laneCapacity = 6;

    public MultiJunction(String name){
        super(name);
    }

    public class MultiJunctionBuild extends JunctionBuild{

        /*
         * Кожен вхід має власну чергу.
         *
         * 2x2:
         * → [ ][ ] →
         * → [ ][ ] →
         *
         * плюс дві вертикальні лінії.
         *
         * 3x3:
         * → [ ][ ][ ] →
         * → [ ][ ][ ] →
         * → [ ][ ][ ] →
         *
         * плюс три вертикальні лінії.
         */
        private final IntMap<Seq<LaneItem>> lanes = new IntMap<>();


        @Override
        public void updateTile(){

            for(IntMap.Entry<Seq<LaneItem>> entry : lanes){

                Seq<LaneItem> queue = entry.value;

                if(queue == null || queue.isEmpty()){
                    continue;
                }

                LaneItem first = queue.first();

                // Затримка така сама за принципом, як у Junction.
                if(Time.time < first.entered + speed / timeScale){
                    continue;
                }

                Building destination =
                    Vars.world.build(first.outX, first.outY);

                if(destination == null){
                    continue;
                }

                if(destination.team != team){
                    continue;
                }

                if(!destination.acceptItem(this, first.item)){
                    continue;
                }

                destination.handleItem(this, first.item);

                queue.remove(0);
            }
        }


        @Override
        public boolean acceptItem(Building source, Item item){

            Lane lane = findLane(source);

            if(lane == null){
                return false;
            }

            Seq<LaneItem> queue = lanes.get(lane.key);

            if(queue != null && queue.size >= laneCapacity){
                return false;
            }

            Building destination =
                Vars.world.build(lane.outX, lane.outY);

            if(destination == null){
                return false;
            }

            return destination.team == team;
        }


        @Override
        public void handleItem(Building source, Item item){

            Lane lane = findLane(source);

            if(lane == null){
                return;
            }

            Seq<LaneItem> queue = lanes.get(lane.key);

            if(queue == null){
                queue = new Seq<>();
                lanes.put(lane.key, queue);
            }

            queue.add(
                new LaneItem(
                    item,
                    Time.time,
                    lane.outX,
                    lane.outY
                )
            );
        }


        @Override
        public int acceptStack(
            Item item,
            int amount,
            Teamc source
        ){
            // Junction працює по одному предмету.
            return 0;
        }


        /*
         * Знаходимо:
         *
         * 1. З якого боку стоїть source.
         * 2. На яку конкретно лінію він попадає.
         * 3. Координату прямо навпроти.
         */
        private Lane findLane(Building source){

            if(source == null){
                return null;
            }

            Tile sourceTile = source.tile;

            if(sourceTile == null){
                return null;
            }


            // Межі нашого великого блока.
            int minX = tileX();
            int minY = tileY();

            /*
             * Для size=1 tileX/tileY — єдина клітинка.
             *
             * Для парного розміру Building знаходиться
             * між клітинками, тому коригуємо нижню межу.
             */
            if(size % 2 == 0){
                minX -= size / 2 - 1;
                minY -= size / 2 - 1;
            }else{
                minX -= size / 2;
                minY -= size / 2;
            }

            int maxX = minX + size - 1;
            int maxY = minY + size - 1;


            int sx = source.tileX();
            int sy = source.tileY();


            // =========================
            // SOURCE ЗЛІВА
            // =========================

            if(sx < minX){

                if(sy < minY || sy > maxY){
                    return null;
                }

                // Вихід прямо справа на тому самому Y.
                int outX = maxX + 1;
                int outY = sy;

                int laneIndex = sy - minY;

                return new Lane(
                    laneKey(0, laneIndex),
                    outX,
                    outY
                );
            }


            // =========================
            // SOURCE СПРАВА
            // =========================

            if(sx > maxX){

                if(sy < minY || sy > maxY){
                    return null;
                }

                // Вихід прямо зліва.
                int outX = minX - 1;
                int outY = sy;

                int laneIndex = sy - minY;

                return new Lane(
                    laneKey(1, laneIndex),
                    outX,
                    outY
                );
            }


            // =========================
            // SOURCE ЗНИЗУ
            // =========================

            if(sy < minY){

                if(sx < minX || sx > maxX){
                    return null;
                }

                // Вихід прямо зверху.
                int outX = sx;
                int outY = maxY + 1;

                int laneIndex = sx - minX;

                return new Lane(
                    laneKey(2, laneIndex),
                    outX,
                    outY
                );
            }


            // =========================
            // SOURCE ЗВЕРХУ
            // =========================

            if(sy > maxY){

                if(sx < minX || sx > maxX){
                    return null;
                }

                // Вихід прямо знизу.
                int outX = sx;
                int outY = minY - 1;

                int laneIndex = sx - minX;

                return new Lane(
                    laneKey(3, laneIndex),
                    outX,
                    outY
                );
            }


            // Source не знаходиться за краєм блока.
            return null;
        }


        /*
         * Робимо унікальний ID для кожної лінії.
         *
         * side:
         * 0 = зліва
         * 1 = справа
         * 2 = знизу
         * 3 = зверху
         */
        private int laneKey(int side, int index){
            return side * 100 + index;
        }
    }


    /*
     * Опис напрямку однієї лінії.
     */
    private static class Lane{

        final int key;
        final int outX;
        final int outY;

        Lane(
            int key,
            int outX,
            int outY
        ){
            this.key = key;
            this.outX = outX;
            this.outY = outY;
        }
    }


    /*
     * Один предмет, який зараз проходить
     * через перехрестя.
     */
    private static class LaneItem{

        final Item item;

        final float entered;

        final int outX;
        final int outY;

        LaneItem(
            Item item,
            float entered,
            int outX,
            int outY
        ){
            this.item = item;
            this.entered = entered;
            this.outX = outX;
            this.outY = outY;
        }
    }
}
