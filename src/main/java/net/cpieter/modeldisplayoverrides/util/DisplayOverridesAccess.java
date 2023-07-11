package net.cpieter.modeldisplayoverrides.util;

import net.cpieter.modeldisplayoverrides.client.render.model.json.BakedDisplayOverrideList;

public interface DisplayOverridesAccess {
    void setDisplayOverrides(BakedDisplayOverrideList displayOverrides);
    BakedDisplayOverrideList getDisplayOverrides();
}
