package net.cpieter.modeldisplayoverrides.mixin;

import net.cpieter.modeldisplayoverrides.client.render.model.json.ModelDisplayOverrideList;
import net.cpieter.modeldisplayoverrides.util.DisplayOverridesAccess;
import net.minecraft.client.render.model.BuiltinBakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BuiltinBakedModel.class)
public abstract class BuiltinBakedModelMixin implements DisplayOverridesAccess {
    @Unique
    private ModelDisplayOverrideList displayOverrides;

    @Override
    public void setDisplayOverrides(ModelDisplayOverrideList displayOverrides) {
        this.displayOverrides = displayOverrides;
    }

    @Override
    public ModelDisplayOverrideList getDisplayOverrides() {
        return this.displayOverrides;
    }
}
