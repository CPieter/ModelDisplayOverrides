# Model Display Overrides
Client side model display overrides for minecraft

![Display Overrides Demo](https://github.com/CPieter/ModelDisplayOverrides/assets/56731651/09b21010-c6d0-42c7-856f-adc71239edf3)

Example model json
```json
{
  "parent": "item/generated",
  "textures": {
    "layer0": "minecraft:item/diamond"
  },
  "display_overrides": {
    "firstperson_lefthand": "minecraft:item/gold_ingot",
    "ground": "minecraft:block/dirt"
  }
}
```
Valid display modes:
- "none"
- "thirdperson_lefthand"
- "thirdperson_righthand"
- "firstperson_lefthand"
- "firstperson_righthand"
- "head"
- "gui"
- "ground"
- "fixed"
