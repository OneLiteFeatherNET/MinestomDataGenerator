package net.minestom.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minestom.datagen.DataGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EnchantmentGenerator extends DataGenerator {
    @Override
    public @NotNull JsonObject generate() {
        JsonObject enchantments = new JsonObject();
        var registry = BuiltInRegistries.ENCHANTMENT;
        for (var enchantment : registry) {
            final var location = registry.getKey(enchantment);
            JsonObject enchantmentJson = new JsonObject();
            enchantmentJson.addProperty("id", registry.getId(enchantment));
            enchantmentJson.addProperty("translationKey", enchantment.getDescriptionId());
            enchantmentJson.addProperty("maxLevel", enchantment.getMaxLevel());
            enchantmentJson.addProperty("rarity", enchantment.getRarity().toString());
            addDefaultable(enchantmentJson, "curse", enchantment.isCurse(), false);
            addDefaultable(enchantmentJson, "discoverable", enchantment.isDiscoverable(), true);
            addDefaultable(enchantmentJson, "tradeable", enchantment.isTradeable(), true);
            addDefaultable(enchantmentJson, "treasureOnly", enchantment.isTreasureOnly(), false);
            enchantmentJson.addProperty("category", enchantment.category.name());
            enchantmentJson.add("incompatibleEnchantments", getIncompatibleEnchantments(enchantment));
            enchantments.add(location.toString(), enchantmentJson);
        }
        return enchantments;
    }

    /**
     * Get the incompatible enchantments for the given enchantment
     * @param origin the enchantment to check
     * @return a JsonArray containing the incompatible enchantments
     */
    private @Nullable JsonArray getIncompatibleEnchantments(net.minecraft.world.item.enchantment.Enchantment origin) {
        JsonArray jsonArray = new JsonArray();
        var registry = BuiltInRegistries.ENCHANTMENT;

        for (Enchantment current : registry) {
            if (registry.getId(origin) == registry.getId(current)) continue;
            if (!origin.isCompatibleWith(current)) {
                jsonArray.add(registry.getKey(current).toString());
            }
        }

        return jsonArray.isEmpty() ? null : jsonArray;
    }
}
