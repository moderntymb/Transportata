package transportata;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Angles;
import arc.math.Mathf;

import mindustry.Vars;
import mindustry.core.Renderer;
import mindustry.graphics.Layer;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.ItemBridge;

import static mindustry.Vars.tilesize;

public class BetaItemBridge extends ItemBridge{

    /*
     * Тимчасовий колір полотна.
     * Нормальні текстури зробимо пізніше.
     */
    public Color betaBridgeColor = Color.valueOf("6c7690");

    public BetaItemBridge(String name){
        super(name);
    }

    /*
     * Тимчасовий костиль для 2x2.
     *
     * Опорний tile у блока 2x2 не знаходиться
     * в геометричному центрі.
     *
     * Через це в двох напрямках губиться один тайл.
     *
     * Для 2x2:
     * вправо  -> +1
     * вгору   -> +1
     *
     * Для 1x1 і 3x3 нічого не змінюємо.
     */
    @Override
    public boolean positionsValid(
        int x1,
        int y1,
        int x2,
        int y2,
        int baseRange
    ){

        // тільки пряма лінія
        if(x1 != x2 && y1 != y2){
            return false;
        }

        int actualRange = baseRange;

        if(size == 2){

            /*
             * Вправо.
             */
            if(y1 == y2 && x2 > x1){
                actualRange += 1;
            }

            /*
             * Вгору.
             */
            if(x1 == x2 && y2 > y1){
                actualRange += 1;
            }
        }

        if(x1 == x2){
            return Math.abs(y1 - y2) <= actualRange;
        }

        return Math.abs(x1 - x2) <= actualRange;
    }

    public class BetaItemBridgeBuild extends ItemBridgeBuild{

        @Override
        public void draw(){

            /*
             * Малюємо сам блок.
             *
             * Не викликаємо ванільний ItemBridgeBuild.draw(),
             * бо він намалює старе вузьке полотно.
             */
            Draw.rect(block.region, x, y);

            Tile other = Vars.world.tile(link);

            if(!linkValid(tile, other)){
                return;
            }

            if(Mathf.zero(Renderer.bridgeOpacity)){
                return;
            }

            Draw.z(Layer.power);

            float alpha =
                (fadeIn
                    ? Math.max(
                        hasPower ? warmup : 1f,
                        0.25f
                    )
                    : 1f)
                * Renderer.bridgeOpacity;

            float x1 = x;
            float y1 = y;

            float x2 = other.drawx();
            float y2 = other.drawy();

            /*
             * Фізична ширина мосту.
             *
             * 1x1 = 8
             * 2x2 = 16
             * 3x3 = 24
             */
            float width = size * tilesize;

            /*
             * Від центра блока до його краю.
             */
            float edge = width / 2f;

            boolean horizontal =
                Math.abs(x2 - x1) >
                Math.abs(y2 - y1);

            float centerX =
                (x1 + x2) / 2f;

            float centerY =
                (y1 + y2) / 2f;

            float bridgeLength;

            if(horizontal){

                bridgeLength =
                    Math.abs(x2 - x1)
                    - edge * 2f;

            }else{

                bridgeLength =
                    Math.abs(y2 - y1)
                    - edge * 2f;
            }

            /*
             * Суцільне полотно.
             */
            if(bridgeLength > 0f){

                Draw.color(betaBridgeColor);
                Draw.alpha(alpha);

                if(horizontal){

                    Draw.rect(
                        "whiteui",
                        centerX,
                        centerY,
                        bridgeLength,
                        width
                    );

                }else{

                    Draw.rect(
                        "whiteui",
                        centerX,
                        centerY,
                        width,
                        bridgeLength
                    );
                }

                Draw.reset();
            }

            /*
             * Тимчасова стрілка.
             */
            float angle =
                Angles.angle(
                    x1,
                    y1,
                    x2,
                    y2
                );

            Draw.alpha(alpha);

            Draw.rect(
                arrowRegion,
                centerX,
                centerY,
                angle
            );

            Draw.reset();
        }
    }
}
