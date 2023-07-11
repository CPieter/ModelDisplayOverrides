package net.cpieter.modeldisplayoverrides.client.render.model.json;

import com.google.gson.*;
import com.mojang.serialization.Codec;
import net.cpieter.modeldisplayoverrides.ModelDisplayOverrides;
import net.cpieter.modeldisplayoverrides.client.ModelDisplayOverridesClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.StringIdentifiable;

import java.lang.reflect.Type;
import java.util.Dictionary;
import java.util.Hashtable;

@Environment(EnvType.CLIENT)
public class ModelDisplayOverride {
    private final Identifier modelId;
    private final ModelTransformationMode renderMode;

    public ModelDisplayOverride(Identifier modelId, ModelTransformationMode renderMode) {
        this.modelId = modelId;
        this.renderMode = renderMode;
    }

    public Identifier getModelId() {
        return this.modelId;
    }

    public ModelTransformationMode getRenderMode() {
        return this.renderMode;
    }

    @Environment(EnvType.CLIENT)
    public static class Deserializer implements JsonDeserializer<ModelDisplayOverride> {
        public static final Hashtable<String, ModelTransformationMode> transformationModeDeserializer;

        public Deserializer() {
        }

        public ModelDisplayOverride deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            Identifier identifier = new Identifier(JsonHelper.getString(jsonObject, "model"));

            String renderModeString = JsonHelper.getString(jsonObject, "display");
            ModelTransformationMode renderMode = transformationModeDeserializer.get(renderModeString);

            return new ModelDisplayOverride(identifier, renderMode);
        }

        static {
            transformationModeDeserializer = new Hashtable<>();
            for (var mode : ModelTransformationMode.values()) {
                transformationModeDeserializer.put(mode.asString(), mode);
            }
        }
    }
}
