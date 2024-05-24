package net.minestom.generators;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.world.flag.FeatureFlags;
import net.minestom.datagen.DataGenerator;

public final class FeatureFlagGenerator extends DataGenerator {

    @Override
    public JsonElement generate() throws Exception {
        JsonObject featureFlags = new JsonObject();
        FeatureFlags.REGISTRY.toNames(FeatureFlags.REGISTRY.allFlags()).forEach(resourceLocation -> {
            featureFlags.add(resourceLocation.toString(), new JsonObject());
        });
        return featureFlags;
    }
}
