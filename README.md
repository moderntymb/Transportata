# Transportata — Beta 0.1.0

First mechanics test for a multi-tier, multi-size Mindustry transport mod.

## Included in this beta

Titanium test tier only:
- Routers: 1x1, 2x2, 3x3
- Junctions: 1x1, 2x2, 3x3
- Item bridges: 1x1, 2x2, 3x3

## Intended behavior

- Large routers use the full perimeter. A 2x2 router can interact with up to 8 adjacent conveyor positions; a 3x3 router with up to 12.
- Large junctions have independent straight-through lanes. 2x2 = 2 horizontal + 2 vertical lines; 3x3 = 3 horizontal + 3 vertical lines.
- Large bridges intentionally start from vanilla ItemBridge logic. Size, range, capacity and transfer rate scale upward. The beta exists partly to verify how vanilla bridge input/output behaves with size 2 and 3 before adding custom bridge code.

## Beta limitations

- Temporary visuals reuse and scale vanilla textures.
- Balance values are placeholders.
- Items currently travelling inside the custom 2x2/3x3 junction are not serialized yet, so they may disappear if a save is made while they are inside the block.
- Only the titanium test tier exists.

## Build

The included GitHub Actions workflow builds a universal Android/Desktop `Transportata.jar` using the same general approach as the official Mindustry Java mod template.
