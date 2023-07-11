package net.cpieter.modeldisplayoverrides.mixin;

import net.cpieter.modeldisplayoverrides.client.render.model.json.BakedDisplayOverrideList;
import net.cpieter.modeldisplayoverrides.util.DisplayOverridesAccess;
import net.minecraft.client.render.model.BuiltinBakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BuiltinBakedModel.class)
public abstract class BuiltinBakedModelMixin implements DisplayOverridesAccess {
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
