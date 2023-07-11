package net.cpieter.modeldisplayoverrides.util;

import net.cpieter.modeldisplayoverrides.client.render.model.json.DisplayOverride;
import net.cpieter.modeldisplayoverrides.client.render.model.json.BakedDisplayOverrideList;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.json.JsonUnbakedModel;

import java.util.List;

public interface JsonUnbakedModelAccess {
    void setDisplayOverrides(List<DisplayOverride> displayOverrides);
    BakedDisplayOverrideList compileDisplayOverrides(Baker baker, JsonUnbakedModel parent);

    List<DisplayOverride> getDisplayOverrides();
}
