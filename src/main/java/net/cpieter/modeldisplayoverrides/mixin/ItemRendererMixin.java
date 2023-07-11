package net.cpieter.modeldisplayoverrides.mixin;

import net.cpieter.modeldisplayoverrides.util.DisplayOverridesAccess;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
    at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private BakedModel injectRenderItem(BakedModel model, ItemStack stack, ModelTransformationMode renderMode) {
        if (model instanceof DisplayOverridesAccess displayOverridesAccess) {
            model = displayOverridesAccess.getDisplayOverrides().apply(model, renderMode);
        }
        return model;
    }
}
