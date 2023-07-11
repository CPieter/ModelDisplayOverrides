package net.cpieter.modeldisplayoverrides.client.render.model.json;

import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelRotation;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class ModelDisplayOverrideList {
    public static final ModelDisplayOverrideList EMPTY = new ModelDisplayOverrideList();
    private final BakedDisplayOverride[] overrides;

    private ModelDisplayOverrideList() {
        this.overrides = new BakedDisplayOverride[0];
    }

    public ModelDisplayOverrideList(Baker baker, JsonUnbakedModel parent, List<ModelDisplayOverride> overrides) {
        List<BakedDisplayOverride> list = Lists.newArrayList();
        for (var override : overrides) {
            BakedModel bakedModel = this.bakeOverridingModel(baker, parent, override);
            list.add(new BakedDisplayOverride(override.getRenderMode(), bakedModel));
        }
        this.overrides = list.toArray(new BakedDisplayOverride[0]);
    }

    @Nullable
    private BakedModel bakeOverridingModel(Baker baker, JsonUnbakedModel parent, ModelDisplayOverride override) {
        UnbakedModel unbakedModel = baker.getOrLoadModel(override.getModelId());
        return Objects.equals(unbakedModel, parent) ? null : baker.bake(override.getModelId(), ModelRotation.X0_Y0);
    }

    @Nullable
    public BakedModel apply(BakedModel model, ModelTransformationMode renderMode) {
        if (this.overrides.length == 0) {
            return model;
        }
        for (var override : overrides) {
            if (override.renderMode == renderMode) {
                return override.model;
            }
        }

        return model;
    }

    @Environment(EnvType.CLIENT)
    static class BakedDisplayOverride {
        private final ModelTransformationMode renderMode;
        @Nullable
        final BakedModel model;

        BakedDisplayOverride(ModelTransformationMode renderMode, @Nullable BakedModel model) {
            this.renderMode = renderMode;
            this.model = model;
        }
    }
}
