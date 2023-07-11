package net.cpieter.modeldisplayoverrides.util;

import net.cpieter.modeldisplayoverrides.client.render.model.json.ModelDisplayOverride;
import net.cpieter.modeldisplayoverrides.client.render.model.json.ModelDisplayOverrideList;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.json.JsonUnbakedModel;

import java.util.List;

public interface JsonUnbakedModelAccess {
    void setDisplayOverrides(List<ModelDisplayOverride> displayOverrides);
    ModelDisplayOverrideList compileDisplayOverrides(Baker baker, JsonUnbakedModel parent);

    List<ModelDisplayOverride> getDisplayOverrides();
}
