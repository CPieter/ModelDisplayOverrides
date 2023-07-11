package net.cpieter.modeldisplayoverrides.mixin;

import net.cpieter.modeldisplayoverrides.client.render.model.json.DisplayOverride;
import net.cpieter.modeldisplayoverrides.util.JsonUnbakedModelAccess;
import net.minecraft.client.render.model.json.ItemModelGenerator;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Function;

@Mixin(ItemModelGenerator.class)
public abstract class ItemModelGeneratorMixin {
    @Inject(method = "create", at = @At(value = "RETURN"), cancellable = true)
    private void injectCreate(Function<SpriteIdentifier, Sprite> textureGetter, JsonUnbakedModel blockModel, CallbackInfoReturnable<JsonUnbakedModel> cir) {
        JsonUnbakedModel jsonUnbakedModel = cir.getReturnValue();
        List<DisplayOverride> displayOverrides = ((JsonUnbakedModelAccess)blockModel).getDisplayOverrides();
        ((JsonUnbakedModelAccess)jsonUnbakedModel).setDisplayOverrides(displayOverrides);
        cir.setReturnValue(jsonUnbakedModel);
    }
}
