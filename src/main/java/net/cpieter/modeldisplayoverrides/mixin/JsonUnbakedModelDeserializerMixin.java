package net.cpieter.modeldisplayoverrides.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.cpieter.modeldisplayoverrides.client.render.model.json.ModelDisplayOverride;
import net.cpieter.modeldisplayoverrides.util.JsonUnbakedModelAccess;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.JsonHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Mixin(JsonUnbakedModel.Deserializer.class)
public abstract class JsonUnbakedModelDeserializerMixin {
    @Unique
    private static final ModelDisplayOverride.Deserializer displayOverrideDeserializer = new ModelDisplayOverride.Deserializer();

    @Inject(method = "deserialize(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/client/render/model/json/JsonUnbakedModel;",
            at = @At("RETURN"), cancellable = true)
    private void injectDeserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext, CallbackInfoReturnable<JsonUnbakedModel> cir) {
        JsonUnbakedModel jsonUnbakedModel = cir.getReturnValue();
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        List<ModelDisplayOverride> displayOverrides = this.displayOverridesFromJson(jsonDeserializationContext, jsonObject);
        ((JsonUnbakedModelAccess)jsonUnbakedModel).setDisplayOverrides(displayOverrides);

        cir.setReturnValue(jsonUnbakedModel);
    }

    protected List<ModelDisplayOverride> displayOverridesFromJson(JsonDeserializationContext context, JsonObject object) {
        List<ModelDisplayOverride> overrides = new ArrayList<>();
        if (object.has("display_overrides")) {
            JsonArray jsonArray = JsonHelper.getArray(object, "display_overrides");
            for (var serialized : jsonArray) {
                overrides.add(displayOverrideDeserializer.deserialize(serialized, ModelDisplayOverride.class, context));
            }
        }
        return overrides;
    }
}
