package net.cpieter.modeldisplayoverrides.mixin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.cpieter.modeldisplayoverrides.client.render.model.json.DisplayOverride;
import net.cpieter.modeldisplayoverrides.util.JsonUnbakedModelAccess;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Type;
import java.util.List;

@Mixin(JsonUnbakedModel.Deserializer.class)
public abstract class JsonUnbakedModelDeserializerMixin {
    @Unique
    private static final DisplayOverride.Deserializer displayOverrideDeserializer = new DisplayOverride.Deserializer();

    @Inject(method = "deserialize(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/client/render/model/json/JsonUnbakedModel;",
            at = @At("RETURN"), cancellable = true)
    private void injectDeserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext, CallbackInfoReturnable<JsonUnbakedModel> cir) {
        JsonUnbakedModel jsonUnbakedModel = cir.getReturnValue();
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        List<DisplayOverride> displayOverrides = displayOverrideDeserializer.deserialize(jsonObject);
        ((JsonUnbakedModelAccess)jsonUnbakedModel).setDisplayOverrides(displayOverrides);

        cir.setReturnValue(jsonUnbakedModel);
    }
}
