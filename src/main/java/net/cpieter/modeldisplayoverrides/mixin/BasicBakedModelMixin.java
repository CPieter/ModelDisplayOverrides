package net.cpieter.modeldisplayoverrides.mixin;

import net.cpieter.modeldisplayoverrides.client.render.model.json.BakedDisplayOverrideList;
import net.cpieter.modeldisplayoverrides.util.DisplayOverridesAccess;
import net.minecraft.client.render.model.BasicBakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BasicBakedModel.class)
public abstract class BasicBakedModelMixin implements DisplayOverridesAccess {
    @Unique
    private BakedDisplayOverrideList displayOverrides;

    @Override
    public void setDisplayOverrides(BakedDisplayOverrideList displayOverrides) {
        this.displayOverrides = displayOverrides;
    }

    @Override
    public BakedDisplayOverrideList getDisplayOverrides() {
        return this.displayOverrides;
    }
}
