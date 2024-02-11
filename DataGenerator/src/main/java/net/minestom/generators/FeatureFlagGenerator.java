package net.minestom.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.minecraft.world.flag.FeatureFlags;
import net.minestom.datagen.DataGenerator;

public final class FeatureFlagGenerator extends DataGenerator {

    @Override
    public JsonElement generate() throws Exception {
        var jsonList = new JsonArray();
        FeatureFlags.REGISTRY.toNames(FeatureFlags.REGISTRY.allFlags()).forEach(resourceLocation -> {
            jsonList.add(resourceLocation.toString());
        });
        return jsonList;
    }
}
