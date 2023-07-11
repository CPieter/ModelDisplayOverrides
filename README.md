# ModelDisplayOverrides
Model display overrides for minecraft

![Display Overrides Demo](https://github.com/CPieter/ModelDisplayOverrides/assets/56731651/09b21010-c6d0-42c7-856f-adc71239edf3)

Example model json
```json
{
  "parent": "item/generated",
  "textures": {
    "layer0": "minecraft:item/diamond"
  },
  "display_overrides": [
    {
      "display": "firstperson_lefthand",
      "model": "minecraft:item/gold_ingot"
    },
    {
      "display": "ground",
      "model": "minecraft:block/dirt"
    }
  ]
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
