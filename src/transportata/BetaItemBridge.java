package transportata;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;

import mindustry.Vars;
import mindustry.core.Renderer;
import mindustry.entities.units.BuildPlan;
import mindustry.graphics.Layer;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.ItemBridge;

import static mindustry.Vars.tilesize;

public class BetaItemBridge extends ItemBridge{

    public BetaItemBridge(String name){
        super(name);
    }

    /**
     * Половина фізичної ширини блока.
     *
     * 1x1 -> 4 world units
     * 2x2 -> 8 world units
     * 3x3 -> 12 world units
     */
    public float bridgeEdgeOffset(){
        return size * tilesize / 2f;
    }

    /**
     * Ширина полотна мосту.
     *
     * Ванільний ItemBridge має bridgeWidth = 6.5,
     * тому масштабуємо її разом із розміром блока.
     */
    public float actualBridgeWidth(){
        return bridgeWidth * size;
    }

    /**
     * Малювання мосту під час планування будівництва.
     */
    @Override
    public void drawBridge(BuildPlan req, float ox, float oy, float flip){

        if(Mathf.zero(Renderer.bridgeOpacity)){
            return;
        }

        Draw.alpha(Renderer.bridgeOpacity);

        float width = actualBridgeWidth();
        float edge = bridgeEdgeOffset();

        Lines.stroke(width);

        // Напрямок від першого мосту до другого.
        Tmp.v1
            .set(ox, oy)
            .sub(req.drawx(), req.drawy())
            .setLength(edge);

        // Полотно починається від КРАЮ великого блока,
        // а не від краю одного тайла.
        Lines.line(
            bridgeRegion,

            req.drawx() + Tmp.v1.x,
            req.drawy() + Tmp.v1.y,

            ox - Tmp.v1.x,
            oy - Tmp.v1.y,

            false
        );

        Draw.rect(
            arrowRegion,
            (req.drawx() + ox) / 2f,
            (req.drawy() + oy) / 2f,
            Angles.angle(
                req.drawx(),
                req.drawy(),
                ox,
                oy
            ) + flip
        );

        Draw.reset();
    }

    public class BetaItemBridgeBuild extends ItemBridgeBuild{

        @Override
        public void draw(){

            /*
             * Building.draw(), а не ItemBridgeBuild.draw(),
             * бо ванільний draw() намалював би старе вузьке
             * полотно поверх нашого.
             */
            superDrawBase();

            Draw.z(Layer.power);

            Tile other = Vars.world.tile(link);

            if(!linkValid(tile, other)){
                return;
            }

            if(Mathf.zero(Renderer.bridgeOpacity)){
                return;
            }

            int direction = relativeTo(other.x, other.y);

            if(pulse){
                Draw.color(
                    arc.graphics.Color.white,
                    arc.graphics.Color.black,
                    Mathf.absin(Time.time, 6f, 0.07f)
                );
            }

            float bridgeWarmup =
                hasPower ? warmup : 1f;

            Draw.alpha(
                (fadeIn
                    ? Math.max(bridgeWarmup, 0.25f)
                    : 1f)
                * Renderer.bridgeOpacity
            );

            /*
             * Кінці мосту.
             *
             * Поки залишаємо ванільні текстури.
             * Коли вставимо твої готові спрайти,
             * окремо зробимо нормальні великі endRegion.
             */
            Draw.rect(
                endRegion,
                x,
                y,
                direction * 90 + 90
            );

            Draw.rect(
                endRegion,
                other.drawx(),
                other.drawy(),
                direction * 90 + 270
            );

            float width = actualBridgeWidth();
            float edge = bridgeEdgeOffset();

            Lines.stroke(width);

            /*
             * Ось головне виправлення.
             *
             * Vanilla:
             * tilesize / 2
             *
             * Transportata:
             * size * tilesize / 2
             *
             * Тобто:
             *
             * 1x1 -> 4
             * 2x2 -> 8
             * 3x3 -> 12
             */
            Tmp.v1
                .set(x, y)
                .sub(
                    other.worldx(),
                    other.worldy()
                )
                .setLength(edge)
                .scl(-1f);

            Lines.line(
                bridgeRegion,

                x + Tmp.v1.x,
                y + Tmp.v1.y,

                other.worldx() - Tmp.v1.x,
                other.worldy() - Tmp.v1.y,

                false
            );

            /*
             * Стрілки поки залишаємо простими.
             * Головне зараз перевірити геометрію полотна.
             */
            float distance =
                Mathf.dst(
                    x,
                    y,
                    other.worldx(),
                    other.worldy()
                );

            float usableDistance =
                Math.max(
                    0f,
                    distance - edge * 2f
                );

            if(usableDistance > 0f){

                float angle =
                    Angles.angle(
                        x,
                        y,
                        other.worldx(),
                        other.worldy()
                    );

                int arrows =
                    Math.max(
                        1,
                        (int)(usableDistance / arrowSpacing)
                    );

                for(int a = 0; a < arrows; a++){

                    float progress =
                        (a + 0.5f) / arrows;

                    float ax =
                        Mathf.lerp(
                            x + Tmp.v1.x,
                            other.worldx() - Tmp.v1.x,
                            progress
                        );

                    float ay =
                        Mathf.lerp(
                            y + Tmp.v1.y,
                            other.worldy() - Tmp.v1.y,
                            progress
                        );

                    Draw.rect(
                        arrowRegion,
                        ax,
                        ay,
                        angle
                    );
                }
            }

            Draw.reset();
        }

        /**
         * Малюємо сам блок.
         *
         * Цей окремий метод потрібен, щоб не викликати
         * ItemBridgeBuild.draw() з його старим полотном.
         */
        private void superDrawBase(){
            Draw.rect(block.region, x, y);
        }
    }
                            }
