# Model Display Overrides
Client side model display overrides for minecraft

![Display Overrides Demo](https://github.com/CPieter/ModelDisplayOverrides/assets/56731651/a3cedaa6-9249-48a9-b33d-128f2c5d2fcb)

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
Valid display positions:
- "thirdperson_lefthand"
- "thirdperson_righthand"
- "firstperson_lefthand"
- "firstperson_righthand"
- "head"
- "gui"
- "ground"
- "fixed"
