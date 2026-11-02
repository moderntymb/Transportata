package transportata;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Time;

import mindustry.Vars;
import mindustry.core.Renderer;
import mindustry.graphics.Layer;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.ItemBridge;

import static mindustry.Vars.tilesize;

public class BetaItemBridge extends ItemBridge{

    /*
     * Тимчасовий колір полотна.
     * Пізніше замінимо його нормальною текстурою.
     */
    public Color betaBridgeColor = Color.valueOf("6c7690");

    public BetaItemBridge(String name){
        super(name);
    }

    public class BetaItemBridgeBuild extends ItemBridgeBuild{

        @Override
        public void draw(){

            /*
             * Малюємо сам блок.
             *
             * Не викликаємо ItemBridgeBuild.draw(),
             * бо він намалює ванільне вузьке полотно.
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
                    ? Math.max(hasPower ? warmup : 1f, 0.25f)
                    : 1f)
                * Renderer.bridgeOpacity;

            /*
             * Центри двох мостів.
             */
            float x1 = x;
            float y1 = y;

            float x2 = other.drawx();
            float y2 = other.drawy();

            /*
             * Повна фізична ширина блока:
             *
             * 1x1 = 8
             * 2x2 = 16
             * 3x3 = 24
             */
            float width = size * tilesize;

            /*
             * Від центру блока до його краю.
             */
            float edge = width / 2f;

            /*
             * Визначаємо напрямок.
             *
             * Горизонтальний:
             * [BLOCK]==========[BLOCK]
             *
             * Вертикальний:
             * [BLOCK]
             *    ||
             *    ||
             * [BLOCK]
             */
            boolean horizontal =
                Math.abs(x2 - x1) >
                Math.abs(y2 - y1);

            float centerX;
            float centerY;

            float bridgeLength;

            if(horizontal){

                bridgeLength =
                    Math.abs(x2 - x1) - edge * 2f;

                centerX = (x1 + x2) / 2f;
                centerY = (y1 + y2) / 2f;

            }else{

                bridgeLength =
                    Math.abs(y2 - y1) - edge * 2f;

                centerX = (x1 + x2) / 2f;
                centerY = (y1 + y2) / 2f;
            }

            /*
             * Не малюємо від'ємний/нульовий прямокутник,
             * якщо блоки стоять дуже близько.
             */
            if(bridgeLength > 0f){

                Draw.color(betaBridgeColor);
                Draw.alpha(alpha);

                /*
                 * Один суцільний прямокутник.
                 *
                 * Це НЕ 2 або 3 окремі лінії.
                 *
                 * 2x2:
                 *
                 * ██================██
                 * ██================██
                 *
                 * 3x3:
                 *
                 * ███==============███
                 * ███==============███
                 * ███==============███
                 */
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
             * Тимчасово залишаємо одну стрілку,
             * щоб було видно напрямок роботи мосту.
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
