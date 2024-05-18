package net.minestom.datagen;

import net.minestom.generators.*;
import net.minestom.generators.loot_tables.BlockLootTableGenerator;
import net.minestom.generators.loot_tables.ChestLootTableGenerator;
import net.minestom.generators.loot_tables.EntityLootTableGenerator;
import net.minestom.generators.loot_tables.GameplayLootTableGenerator;
import net.minestom.generators.tags.*;

import java.util.List;

public enum DataGenType {
    ATTRIBUTES("attributes", new AttributeGenerator()),
    BIOMES("biomes", new BiomeGenerator()),
    BLOCKS("blocks", new BlockGenerator()),
    COMMAND_ARGUMENTS("command_arguments", new CommandArgumentGenerator()),
    CUSTOM_STATISTICS("custom_statistics", new CustomStatisticGenerator()),
    DYE_COLORS("dye_colors", new DyeColorGenerator()),
    ENCHANTMENTS("enchantments", new EnchantmentGenerator()),
    FEATURE_FLAGS("feature_flags", new FeatureFlagGenerator()),
    ENTITIES("entities", new EntityGenerator()),
    FLUIDS("fluids", new FluidGenerator()),
    GAME_EVENTS("game_events", new GameEventGenerator()),
    MATERIALS("items", new MaterialGenerator()),
    MAP_COLORS("map_colors", new MapColorGenerator()),
    PARTICLES("particles", new ParticleGenerator()),
    MOB_EFFECTS("potion_effects", new MobEffectGenerator()),
    POTIONS("potions", new PotionGenerator()),
    SOUNDS("sounds", new SoundGenerator()),
    SOUND_SOURCES("sound_sources", new SoundSourceGenerator()),
    VILLAGER_PROFESSIONS("villager_professions", new VillagerProfessionGenerator()),
    VILLAGER_TYPES("villager_types", new VillagerTypeGenerator()),
//    COMPONENTS("components", new ComponentGenerator()),
    RECIPE_TYPE("recipe_types", new RecipeTypeGenerator()),

    BLOCK_TAGS("tags/block_tags", new BlockTagGenerator()),
    ENTITY_TYPE_TAGS("tags/entity_type_tags", new EntityTypeTagGenerator()),
    FLUID_TAGS("tags/fluid_tags", new FluidTagGenerator()),
    GAMEEVENT_TAGS("tags/gameplay_tags", new GameEventTagGenerator()),
    ITEM_TAGS("tags/item_tags", new ItemTagGenerator()),

    DIMENSION_TYPES("dimension_types", new GenericResourceGenerator("dimension_type")),
    CHAT_TYPES("chat_types", new GenericResourceGenerator("chat_type")),
    DAMAGE_TYPES("damage_types", new GenericResourceGenerator("damage_type")),
    BANNER_PATTERNS("banner_patterns", new GenericResourceGenerator("banner_pattern")),
    WOLF_VARIANTS("wolf_variants", new GenericResourceGenerator("wolf_variant")),
    TRIM_MATERIALS("trim_materials", new GenericResourceGenerator("trim_material")),
    TRIM_PATTERNS("trim_patterns", new GenericResourceGenerator("trim_pattern")),

    BLOCK_LOOT_TABLES("loot_tables/block_loot_tables", new BlockLootTableGenerator()),
    CHEST_LOOT_TABLES("loot_tables/chest_loot_tables", new ChestLootTableGenerator()),
    ENTITY_LOOT_TABLES("loot_tables/entity_loot_tables", new EntityLootTableGenerator()),
    GAMEPLAY_LOOT_TABLES("loot_tables/gameplay_loot_tables", new GameplayLootTableGenerator());

    private final String fileName;
    private final DataGenerator generator;

    DataGenType(String fileName, DataGenerator generator) {
        this.fileName = fileName;
        this.generator = generator;
    }

    public String getFileName() {
        return fileName;
    }

    public DataGenerator getGenerator() {
        return generator;
    }
}