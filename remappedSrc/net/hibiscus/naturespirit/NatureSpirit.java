package net.hibiscus.naturespirit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.hibiscus.naturespirit.entity.HibiscusBoatEntity;
import net.hibiscus.naturespirit.items.HibiscusBoatDispensorBehavior;
import net.hibiscus.naturespirit.items.HibiscusVinegarDispenserBehavior;
import net.hibiscus.naturespirit.mixin.StatsTypeAccessor;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.registration.HibiscusEntityTypes;
import net.hibiscus.naturespirit.registration.HibiscusItemGroups;
import net.hibiscus.naturespirit.registration.HibiscusSounds;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.util.HibiscusCauldronBehavior;
import net.hibiscus.naturespirit.util.HibiscusEvents;
import net.hibiscus.naturespirit.util.HibiscusVillagers;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.block.DispenserBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NatureSpirit implements ModInitializer {

   public static final String MOD_ID = "natures_spirit";
   public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
   public static final ResourceLocation EAT_PIZZA_SLICE = StatsTypeAccessor.registerNew("eat_pizza_slice", StatFormatter.DEFAULT);
   public static final ResourceLocation EAT_CHEESE = StatsTypeAccessor.registerNew("eat_cheese", StatFormatter.DEFAULT);
   public static final SimpleParticleType RED_MAPLE_LEAVES_PARTICLE = FabricParticleTypes.simple(false);
   public static final SimpleParticleType ORANGE_MAPLE_LEAVES_PARTICLE = FabricParticleTypes.simple(false);
   public static final SimpleParticleType YELLOW_MAPLE_LEAVES_PARTICLE = FabricParticleTypes.simple(false);
   public static final SimpleParticleType MILK_PARTICLE = FabricParticleTypes.simple(false);
   public static final SimpleParticleType CALCITE_BUBBLE_PARTICLE = FabricParticleTypes.simple(false);

   @Override public void onInitialize() {

      Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(MOD_ID, "red_maple_leaves"), RED_MAPLE_LEAVES_PARTICLE);
      Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(MOD_ID, "orange_maple_leaves"), ORANGE_MAPLE_LEAVES_PARTICLE);
      Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(MOD_ID, "yellow_maple_leaves"), YELLOW_MAPLE_LEAVES_PARTICLE);
      Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(MOD_ID, "milk"), MILK_PARTICLE);
      Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(MOD_ID, "calcite_bubble"), CALCITE_BUBBLE_PARTICLE);
      HibiscusVillagers.registerVillagers();
      HibiscusBiomes.registerBiomes();
      HibiscusWoods.registerWoods();
      HibiscusBlocksAndItems.registerHibiscusBlocks();
      HibiscusEvents.registerEvents();
      HibiscusWorldGen.registerWorldGen();
      HibiscusItemGroups.registerItemGroup();
      HibiscusEntityTypes.registerEntityTypes();
      HibiscusSounds.registerSounds();
      HibiscusCauldronBehavior.registerBehavior();
      PotionBrewing.addContainerRecipe(Items.POTION, Items.SWEET_BERRIES, HibiscusBlocksAndItems.VINEGAR_BOTTLE);

      CompostingChanceRegistry.INSTANCE.add(HibiscusBlocksAndItems.DESERT_TURNIP_BLOCK.asItem(), 0.5F);
      CompostingChanceRegistry.INSTANCE.add(HibiscusBlocksAndItems.DESERT_TURNIP_ROOT_BLOCK.asItem(), 0.4F);

      DispenserBlock.registerBehavior(HibiscusBlocksAndItems.VINEGAR_BOTTLE, new HibiscusVinegarDispenserBehavior());

      for(HibiscusBoatEntity.HibiscusBoat boat : HibiscusBoatEntity.HibiscusBoat.values()) {
         DispenserBlock.registerBehavior(boat.boat(), new HibiscusBoatDispensorBehavior(boat, false));
         DispenserBlock.registerBehavior(boat.chestBoat(), new HibiscusBoatDispensorBehavior(boat, true));
      }

      Registry.register(BuiltInRegistries.CAT_VARIANT, "trans", new CatVariant(new ResourceLocation(MOD_ID, "textures/entity/cat/trans" + ".png")));
   }
}
