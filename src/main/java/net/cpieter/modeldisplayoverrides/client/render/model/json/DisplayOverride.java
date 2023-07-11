package net.cpieter.modeldisplayoverrides.client.render.model.json;

import com.google.gson.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

@Environment(EnvType.CLIENT)
public class DisplayOverride {
    private final Identifier modelId;
    private final ModelTransformationMode renderMode;

    public DisplayOverride(Identifier modelId, ModelTransformationMode renderMode) {
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
    public static class Deserializer {
        public static final Hashtable<String, ModelTransformationMode> transformationModeDeserializer;

        public Deserializer() {
        }

        public List<DisplayOverride> deserialize(JsonElement jsonElement) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("display_overrides") == false) {
                return new ArrayList<>();
            }
            List<DisplayOverride> overrides = new ArrayList<>();
            JsonObject jsonOverrides = JsonHelper.getObject(jsonObject, "display_overrides");

            Enumeration<String> keys = transformationModeDeserializer.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                if (JsonHelper.hasString(jsonOverrides, key)) {
                    ModelTransformationMode renderMode = transformationModeDeserializer.get(key);
                    String overrideId = JsonHelper.getString(jsonOverrides, key);

                    Identifier id = new Identifier(overrideId);
                    DisplayOverride override = new DisplayOverride(id, renderMode);
                    overrides.add(override);
                }
            }
            return overrides;
        }

        static {
            transformationModeDeserializer = new Hashtable<>();
            for (var mode : ModelTransformationMode.values()) {
                transformationModeDeserializer.put(mode.asString(), mode);
            }
        }
    }
}
