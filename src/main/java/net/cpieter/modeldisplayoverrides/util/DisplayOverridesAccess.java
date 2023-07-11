package net.cpieter.modeldisplayoverrides.util;

import net.cpieter.modeldisplayoverrides.client.render.model.json.ModelDisplayOverrideList;

public interface DisplayOverridesAccess {
    void setDisplayOverrides(ModelDisplayOverrideList displayOverrides);
    ModelDisplayOverrideList getDisplayOverrides();
}
