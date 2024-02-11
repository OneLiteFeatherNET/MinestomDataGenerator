package net.minestom.generators;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minestom.datagen.DataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FeatureFlagGenerator extends DataGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureFlagGenerator.class);

    @Override
    public JsonElement generate() throws Exception {
        for (Feature<?> feature : BuiltInRegistries.FEATURE) {
            System.out.println(BuiltInRegistries.FEATURE.getKey(feature));
        }
        return new JsonObject();
    }
}
