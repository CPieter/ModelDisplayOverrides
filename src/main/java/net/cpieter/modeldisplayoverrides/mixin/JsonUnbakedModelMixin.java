package net.cpieter.modeldisplayoverrides.mixin;

import net.cpieter.modeldisplayoverrides.client.render.model.json.ModelDisplayOverride;
import net.cpieter.modeldisplayoverrides.client.render.model.json.ModelDisplayOverrideList;
import net.cpieter.modeldisplayoverrides.util.DisplayOverridesAccess;
import net.cpieter.modeldisplayoverrides.util.JsonUnbakedModelAccess;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

@Mixin(JsonUnbakedModel.class)
public abstract class JsonUnbakedModelMixin implements UnbakedModel, JsonUnbakedModelAccess {
    @Unique
    private List<ModelDisplayOverride> displayOverrides = new ArrayList<>();

    @Inject(method = "getModelDependencies", at = @At("RETURN"), cancellable = true)
    private void injectGetModelDependencies(CallbackInfoReturnable<Set<Identifier>> cir) {
        Set<Identifier> set = cir.getReturnValue();
        for (var override : displayOverrides) {
            set.add(override.getModelId());
        }
        cir.setReturnValue(set);
    }

    @Inject(method = "setParents", at = @At("TAIL"))
    private void injectSetParents(Function<Identifier, UnbakedModel> modelLoader, CallbackInfo ci) {
        JsonUnbakedModel jsonUnbakedModel = (JsonUnbakedModel)(Object)this;

        for (var override : displayOverrides) {
            UnbakedModel unbakedModel = modelLoader.apply(override.getModelId());
            if (!Objects.equals(unbakedModel, jsonUnbakedModel)) {
                unbakedModel.setParents(modelLoader);
            }
        }
    }

    @Inject(method = "bake(Lnet/minecraft/client/render/model/Baker;Lnet/minecraft/client/render/model/json/JsonUnbakedModel;Ljava/util/function/Function;Lnet/minecraft/client/render/model/ModelBakeSettings;Lnet/minecraft/util/Identifier;Z)Lnet/minecraft/client/render/model/BakedModel;",
            at = @At("RETURN"),
            cancellable = true)
    private void injectBake(Baker baker, JsonUnbakedModel parent, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings settings, Identifier id, boolean hasDepth, @NotNull CallbackInfoReturnable<BakedModel> cir) {
        BakedModel bakedModel = cir.getReturnValue();
        if (bakedModel instanceof DisplayOverridesAccess displayOverridesAccess) {
            displayOverridesAccess.setDisplayOverrides(compileDisplayOverrides(baker, parent));
            cir.setReturnValue(bakedModel);
        }
    }

    @Override
    public List<ModelDisplayOverride> getDisplayOverrides() {
        return displayOverrides;
    }

    @Override
    public void setDisplayOverrides(List<ModelDisplayOverride> displayOverrides) {
        this.displayOverrides = displayOverrides;
    }

    @Override
    public ModelDisplayOverrideList compileDisplayOverrides(Baker baker, JsonUnbakedModel parent) {
        return this.displayOverrides.isEmpty() ? ModelDisplayOverrideList.EMPTY : new ModelDisplayOverrideList(baker, parent, this.displayOverrides);
    }
}
