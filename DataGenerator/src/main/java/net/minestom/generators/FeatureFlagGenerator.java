package net.minestom.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.minecraft.world.flag.FeatureFlags;
import net.minestom.datagen.DataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FeatureFlagGenerator extends DataGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureFlagGenerator.class);

    @Override
    public JsonElement generate() throws Exception {
        var jsonList = new JsonArray();
        FeatureFlags.REGISTRY.toNames(FeatureFlags.REGISTRY.allFlags()).forEach(resourceLocation -> {
            jsonList.add(resourceLocation.toString());
        });
        return jsonList;
    }
}
