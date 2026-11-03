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
     * Графіку зробимо пізніше.
     */
    public Color betaBridgeColor = Color.valueOf("6c7690");

    public BetaItemBridge(String name){
        super(name);
    }

    /*
     * Перевірка дальності.
     *
     * Для 1x1 та 3x3 залишаємо ванільну логіку.
     *
     * Для 2x2 використовуємо спеціальну перевірку:
     * опорний tile вважаємо не точкою 1x1,
     * а квадратом 2x2.
     */
    @Override
    public boolean positionsValid(
        int x1,
        int y1,
        int x2,
        int y2,
        int baseRange
    ){

        if(size != 2){
            return super.positionsValid(
                x1,
                y1,
                x2,
                y2,
                baseRange
            );
        }

        /*
         * 2x2 у Mindustry прив'язаний до одного tile.
         *
         * Для нашої перевірки уявляємо:
         *
         * x1,y1
         *   ┌─────┐
         *   │     │
         *   │ 2x2 │
         *   │     │
         *   └─────┘
         *
         * і так само для другого блока.
         */

        int dx = x2 - x1;
        int dy = y2 - y1;

        /*
         * Діагональні мости заборонені.
         */
        if(dx != 0 && dy != 0){
            return false;
        }

        /*
         * Відстань між опорними tile.
         */
        int distance =
            Math.max(
                Math.abs(dx),
                Math.abs(dy)
            );

        /*
         * Два блоки мають ширину 2.
         *
         * Між їхніми найближчими краями відстань
         * на один тайл менша, ніж між опорними tile.
         *
         * Тому:
         *
         * vanilla:
         *
         * X----------X
         *
         * наш 2x2:
         *
         * [XX]------[XX]
         *
         * Компенсуємо цей один тайл.
         */
        int edgeDistance =
            Math.max(
                0,
                distance - 1
            );

        return edgeDistance <= baseRange;
    }

    public class BetaItemBridgeBuild extends ItemBridgeBuild{

        @Override
        public void draw(){

            /*
             * Сам блок.
             *
             * ItemBridgeBuild.draw() не викликаємо,
             * бо він намалює ванільне вузьке полотно.
             */
            Draw.rect(
                block.region,
                x,
                y
            );

            Tile other =
                Vars.world.tile(link);

            if(!linkValid(tile, other)){
                return;
            }

            if(Mathf.zero(Renderer.bridgeOpacity)){
                return;
            }

            Draw.z(Layer.power);

            float alpha =
                (
                    fadeIn
                        ? Math.max(
                            hasPower
                                ? warmup
                                : 1f,
                            0.25f
                        )
                        : 1f
                )
                * Renderer.bridgeOpacity;

            /*
             * Центри двох Building.
             */
            float x1 = x;
            float y1 = y;

            float x2 =
                other.drawx();

            float y2 =
                other.drawy();

            /*
             * Повна ширина блока.
             *
             * 1x1 = 8
             * 2x2 = 16
             * 3x3 = 24
             */
            float width =
                size * tilesize;

            /*
             * Від геометричного центра
             * до краю блока.
             */
            float edge =
                width / 2f;

            boolean horizontal =
                Math.abs(x2 - x1)
                    >
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
             * Суцільне полотно мосту.
             */
            if(bridgeLength > 0f){

                Draw.color(
                    betaBridgeColor
                );

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
             * Тимчасова центральна стрілка.
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
