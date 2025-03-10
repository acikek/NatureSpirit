package net.hibiscus.naturespirit.terrablender;

import com.mojang.datafixers.util.Pair;
import net.hibiscus.naturespirit.datagen.HibiscusBiomes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

import java.util.function.Consumer;

public class Region5Parameters {
   private final MultiNoiseUtil.ParameterRange defaultParameter = MultiNoiseUtil.ParameterRange.of(-1.0F, 1.0F);
   private final MultiNoiseUtil.ParameterRange[] temperatureParameters = new MultiNoiseUtil.ParameterRange[]{
           MultiNoiseUtil.ParameterRange.of(-1.0F, -0.45F), MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F), MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F), MultiNoiseUtil.ParameterRange.of(0.2F,
           0.55F
   ), MultiNoiseUtil.ParameterRange.of(0.55F, 1.0F)
   };
   private final MultiNoiseUtil.ParameterRange[] humidityParameters = new MultiNoiseUtil.ParameterRange[]{
           MultiNoiseUtil.ParameterRange.of(-1.0F, -0.35F), MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F), MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F), MultiNoiseUtil.ParameterRange.of(0.1F,
           0.3F
   ), MultiNoiseUtil.ParameterRange.of(0.3F, 1.0F)
   };
   private final MultiNoiseUtil.ParameterRange[] erosionParameters = new MultiNoiseUtil.ParameterRange[]{
           MultiNoiseUtil.ParameterRange.of(-1.0F, -0.78F),
           MultiNoiseUtil.ParameterRange.of(-0.78F, -0.375F),
           MultiNoiseUtil.ParameterRange.of(-0.375F, -0.2225F),
           MultiNoiseUtil.ParameterRange.of(-0.2225F, 0.05F),
           MultiNoiseUtil.ParameterRange.of(0.05F, 0.45F),
           MultiNoiseUtil.ParameterRange.of(0.45F, 0.55F),
           MultiNoiseUtil.ParameterRange.of(0.55F, 1.0F)
   };
   private final MultiNoiseUtil.ParameterRange frozenTemperature;
   private final MultiNoiseUtil.ParameterRange nonFrozenTemperatureParameters;
   private final MultiNoiseUtil.ParameterRange erodedRiverTemperatureParameters;
   private final MultiNoiseUtil.ParameterRange riverTemperatureParameters;
   private final MultiNoiseUtil.ParameterRange mushroomFieldsContinentalness;
   private final MultiNoiseUtil.ParameterRange deepOceanContinentalness;
   private final MultiNoiseUtil.ParameterRange oceanContinentalness;
   private final MultiNoiseUtil.ParameterRange coastContinentalness;
   private final MultiNoiseUtil.ParameterRange riverContinentalness;
   private final MultiNoiseUtil.ParameterRange nearInlandContinentalness;
   private final MultiNoiseUtil.ParameterRange midInlandContinentalness;
   private final MultiNoiseUtil.ParameterRange farInlandContinentalness;
   private final RegistryKey <Biome>[][] oceanBiomes;
   private final RegistryKey <Biome>[][] commonBiomes;
   private final RegistryKey <Biome>[][] uncommonBiomes;
   private final RegistryKey <Biome>[][] nearMountainBiomes;
   private final RegistryKey <Biome>[][] specialNearMountainBiomes;
   private final RegistryKey <Biome>[][] windsweptBiomes;

   public Region5Parameters() {
      this.frozenTemperature = this.temperatureParameters[0];
      this.nonFrozenTemperatureParameters = MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[4]);
      this.erodedRiverTemperatureParameters = MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[2]);
      this.riverTemperatureParameters = MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[3], this.temperatureParameters[4]);
      this.mushroomFieldsContinentalness = MultiNoiseUtil.ParameterRange.of(-1.2F, -1.05F);
      this.deepOceanContinentalness = MultiNoiseUtil.ParameterRange.of(-1.05F, -0.455F);
      this.oceanContinentalness = MultiNoiseUtil.ParameterRange.of(-0.455F, -0.19F);
      this.coastContinentalness = MultiNoiseUtil.ParameterRange.of(-0.19F, -0.11F);
      this.riverContinentalness = MultiNoiseUtil.ParameterRange.of(-0.11F, 0.55F);
      this.nearInlandContinentalness = MultiNoiseUtil.ParameterRange.of(-0.11F, 0.03F);
      this.midInlandContinentalness = MultiNoiseUtil.ParameterRange.of(0.03F, 0.3F);
      this.farInlandContinentalness = MultiNoiseUtil.ParameterRange.of(0.3F, 1.0F);
      this.oceanBiomes = new RegistryKey[][]{
              {
                      BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.WARM_OCEAN
              }, {
                      BiomeKeys.FROZEN_OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.WARM_OCEAN
              }
      };
      this.commonBiomes = new RegistryKey[][]{
              {
                      BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA
              }, {
                      BiomeKeys.PLAINS, BiomeKeys.PLAINS, HibiscusBiomes.WISTERIA_FOREST, HibiscusBiomes.WISTERIA_FOREST, BiomeKeys.TAIGA
              }, {
                      BiomeKeys.FLOWER_FOREST, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST
              }, {
              HibiscusBiomes.LAVENDER_FIELDS, HibiscusBiomes.CYPRESS_FIELDS, HibiscusBiomes.CYPRESS_FIELDS, BiomeKeys.SPARSE_JUNGLE, BiomeKeys.JUNGLE
              }, {
                      BiomeKeys.DESERT, BiomeKeys.DESERT, HibiscusBiomes.LIVELY_DUNES, HibiscusBiomes.LIVELY_DUNES, HibiscusBiomes.LIVELY_DUNES
              }
      };
      this.uncommonBiomes = new RegistryKey[][]{
              {BiomeKeys.ICE_SPIKES, null, BiomeKeys.SNOWY_TAIGA, null, null}, {null, null, null, null, BiomeKeys.OLD_GROWTH_PINE_TAIGA}, {
              null, null, null, null, null
      }, {
              HibiscusBiomes.CARNATION_FIELDS, null, null, null, BiomeKeys.BAMBOO_JUNGLE
              }, {null, null, null, HibiscusBiomes.BLOOMING_DUNES, HibiscusBiomes.BLOOMING_DUNES}
      };
      this.nearMountainBiomes = new RegistryKey[][]{
              {
                      BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_TAIGA
              }, {
                      BiomeKeys.MEADOW, BiomeKeys.MEADOW, HibiscusBiomes.WISTERIA_FOREST, HibiscusBiomes.WISTERIA_FOREST, BiomeKeys.TAIGA
              }, {
                      BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.MEADOW, BiomeKeys.DARK_FOREST
              }, {
                      HibiscusBiomes.LAVENDER_FIELDS, HibiscusBiomes.CYPRESS_FIELDS, HibiscusBiomes.CYPRESS_FIELDS, BiomeKeys.FOREST, BiomeKeys.JUNGLE
              }, {
                      BiomeKeys.BADLANDS, BiomeKeys.BADLANDS, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS, BiomeKeys.WOODED_BADLANDS
              }
      };
      this.specialNearMountainBiomes = new RegistryKey[][]{
              {BiomeKeys.ICE_SPIKES, null, null, null, null}, {
              BiomeKeys.CHERRY_GROVE, null, BiomeKeys.MEADOW, null, BiomeKeys.TAIGA
      }, {
                      BiomeKeys.CHERRY_GROVE, BiomeKeys.CHERRY_GROVE, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, null
              }, {
                      HibiscusBiomes.CARNATION_FIELDS, null, null, null, BiomeKeys.FOREST
              }, {
                      BiomeKeys.ERODED_BADLANDS, BiomeKeys.ERODED_BADLANDS, null, null, null
              }
      };
      this.windsweptBiomes = new RegistryKey[][]{
              {
                      BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_FOREST
              }, {
                      BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_FOREST
              }, {
                      BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_FOREST
              }, {null, null, null, null, null}, {null, null, null, null, null}
      };
   }

   protected void writeOverworldBiomeParameters(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters) {
      this.writeOceanBiomes(parameters);
      this.writeLandBiomes(parameters);
      this.writeCaveBiomes(parameters);
   }

   private void writeOceanBiomes(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters) {
      this.writeBiomeParameters(parameters,
              this.defaultParameter,
              this.defaultParameter,
              this.mushroomFieldsContinentalness,
              this.defaultParameter,
              this.defaultParameter,
              0.0F,
              BiomeKeys.MUSHROOM_FIELDS
      );

      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         MultiNoiseUtil.ParameterRange parameterRange = this.temperatureParameters[i];
         this.writeBiomeParameters(parameters, parameterRange, this.defaultParameter, this.deepOceanContinentalness, this.defaultParameter, this.defaultParameter, 0.0F, this.oceanBiomes[0][i]);
         this.writeBiomeParameters(parameters, parameterRange, this.defaultParameter, this.oceanContinentalness, this.defaultParameter, this.defaultParameter, 0.0F, this.oceanBiomes[1][i]);
      }

   }

   private void writeLandBiomes(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters) {
      this.writeMidBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-1.0F, -0.93333334F));
      this.writeHighBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.93333334F, -0.7666667F));
      this.writePeakBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.7666667F, -0.56666666F));
      this.writeHighBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.56666666F, -0.4F));
      this.writeMidBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.4F, -0.26666668F));
      this.writeLowBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.26666668F, -0.05F));
      this.writeValleyBiomes(parameters, MultiNoiseUtil.ParameterRange.of(-0.05F, 0.05F));
      this.writeLowBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.05F, 0.26666668F));
      this.writeMidBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.26666668F, 0.4F));
      this.writeHighBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.4F, 0.56666666F));
      this.writePeakBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.56666666F, 0.7666667F));
      this.writeHighBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.7666667F, 0.93333334F));
      this.writeMidBiomes(parameters, MultiNoiseUtil.ParameterRange.of(0.93333334F, 1.0F));
   }

   private void writePeakBiomes(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         MultiNoiseUtil.ParameterRange parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            MultiNoiseUtil.ParameterRange parameterRange2 = this.humidityParameters[j];
            RegistryKey <Biome> registryKey = this.getRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey2 = this.getBadlandsOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey3 = this.getMountainStartBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey4 = this.getNearMountainBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey5 = this.getWindsweptOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey6 = this.getBiomeOrWindsweptSavanna(i, j, weirdness, registryKey5);
            RegistryKey <Biome> registryKey7 = this.getPeakBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey8 = this.getStratifiedDesertOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey9 = this.getStratifiedDesertOrNearMountainBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey10 = this.getShoreCliffBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey11 = this.getBiomeOrChalkCliffs(i, j, weirdness, registryKey5);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.coastContinentalness,
                    MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[2]),
                    weirdness,
                    0.0F,
                    registryKey10
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[0],
                    weirdness,
                    0.0F,
                    registryKey7
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    registryKey3
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    registryKey7
            );

            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[3],
                    weirdness,
                    0.0F,
                    registryKey8
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[2],
                    weirdness,
                    0.0F,
                    registryKey8
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[2],
                    weirdness,
                    0.0F,
                    registryKey9
            );
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.midInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey8);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.farInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey9);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[4],
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[4],
                    weirdness,
                    0.0F,
                    registryKey8
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey11
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey5
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[6],
                    weirdness,
                    0.0F,
                    registryKey
            );
         }
      }

   }

   private void writeHighBiomes(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         MultiNoiseUtil.ParameterRange parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            MultiNoiseUtil.ParameterRange parameterRange2 = this.humidityParameters[j];
            RegistryKey <Biome> registryKey = this.getRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey2 = this.getBadlandsOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey3 = this.getMountainStartBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey4 = this.getNearMountainBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey5 = this.getWindsweptOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey6 = this.getBiomeOrChalkCliffs(i, j, weirdness, registryKey);
            RegistryKey <Biome> registryKey7 = this.getMountainSlopeBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey8 = this.getPeakBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey9 = this.getStratifiedDesertOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey10 = this.getStratifiedDesertOrNearMountainBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey11 = this.getShoreCliffBiome(i, j, weirdness);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.coastContinentalness,
                    MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[2]),
                    weirdness,
                    0.0F,
                    registryKey11
            );
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[0], weirdness, 0.0F, registryKey7);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[0],
                    weirdness,
                    0.0F,
                    registryKey8
            );
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[1], weirdness, 0.0F, registryKey3);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    registryKey7
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[3],
                    weirdness,
                    0.0F,
                    registryKey9
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[2],
                    weirdness,
                    0.0F,
                    registryKey9
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[2],
                    weirdness,
                    0.0F,
                    registryKey10
            );
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.midInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey9);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.farInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey10);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[4],
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[4],
                    weirdness,
                    0.0F,
                    registryKey9
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey6
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey5
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[6],
                    weirdness,
                    0.0F,
                    registryKey
            );
         }
      }

   }

   private void writeMidBiomes(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
      this.writeBiomeParameters(parameters,
              MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[2]),
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness),
              this.erosionParameters[6],
              weirdness,
              0.0F,
              BiomeKeys.SWAMP
      );
      this.writeBiomeParameters(parameters,
              MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[3], this.temperatureParameters[4]),
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness),
              this.erosionParameters[6],
              weirdness,
              0.0F,
              BiomeKeys.MANGROVE_SWAMP
      );

      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         MultiNoiseUtil.ParameterRange parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            MultiNoiseUtil.ParameterRange parameterRange2 = this.humidityParameters[j];
            RegistryKey <Biome> registryKey = this.getRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey2 = this.getBadlandsOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey3 = this.getMountainStartBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey4 = this.getWindsweptOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey5 = this.getNearMountainBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey6 = this.getShoreBiome(i, j);
            RegistryKey <Biome> registryKey7 = this.getBiomeOrChalkCliffs(i, j, weirdness, registryKey);
            RegistryKey <Biome> registryKey8 = this.getErodedShoreBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey9 = this.getMountainSlopeBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey10 = this.getStratifiedDesertOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey11 = this.getStratifiedDesertOrNearMountainBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey12 = this.getShoreCliffBiome(i, j, weirdness);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.coastContinentalness,
                    MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[2]),
                    weirdness,
                    0.0F,
                    registryKey12
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[0],
                    weirdness,
                    0.0F,
                    registryKey9
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.midInlandContinentalness),
                    this.erosionParameters[1],
                    weirdness,
                    0.0F,
                    registryKey3
            );
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.farInlandContinentalness, this.erosionParameters[1], weirdness, 0.0F, i == 0 ? registryKey9 : registryKey5);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[2], weirdness, 0.0F, registryKey10);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.midInlandContinentalness, this.erosionParameters[2], weirdness, 0.0F, registryKey10);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.farInlandContinentalness, this.erosionParameters[2], weirdness, 0.0F, registryKey11);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, registryKey10);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[3],
                    weirdness,
                    0.0F,
                    registryKey10
            );
            if(weirdness.max() < 0L) {
               this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[4], weirdness, 0.0F, registryKey6);
               this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[4], weirdness, 0.0F, registryKey);
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                       this.erosionParameters[4],
                       weirdness,
                       0.0F,
                       registryKey10
               );
            }
            else {
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness),
                       this.erosionParameters[4],
                       weirdness,
                       0.0F,
                       registryKey
               );
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                       this.erosionParameters[4],
                       weirdness,
                       0.0F,
                       registryKey10
               );
            }

            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[5], weirdness, 0.0F, registryKey8);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[5], weirdness, 0.0F, registryKey7);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey4
            );
            if(weirdness.max() < 0L) {
               this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[6], weirdness, 0.0F, registryKey6);
            }
            else {
               this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[6], weirdness, 0.0F, registryKey);
            }

            if(i == 0) {
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness),
                       this.erosionParameters[6],
                       weirdness,
                       0.0F,
                       registryKey
               );
            }
         }
      }

   }

   private void writeLowBiomes(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
      this.writeBiomeParameters(parameters,
              MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[2]),
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness),
              this.erosionParameters[6],
              weirdness,
              0.0F,
              BiomeKeys.SWAMP
      );
      this.writeBiomeParameters(parameters,
              MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[3], this.temperatureParameters[4]),
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness),
              this.erosionParameters[6],
              weirdness,
              0.0F,
              BiomeKeys.MANGROVE_SWAMP
      );

      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         MultiNoiseUtil.ParameterRange parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            MultiNoiseUtil.ParameterRange parameterRange2 = this.humidityParameters[j];
            RegistryKey <Biome> registryKey = this.getRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey2 = this.getBadlandsOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey3 = this.getMountainStartBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey4 = this.getShoreBiome(i, j);
            RegistryKey <Biome> registryKey5 = this.getBiomeOrChalkCliffs(i, j, weirdness, registryKey);
            RegistryKey <Biome> registryKey6 = this.getErodedShoreBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey7 = this.getStratifiedDesertOrRegularBiome(i, j, weirdness);
            RegistryKey <Biome> registryKey8 = this.getShoreCliffBiome(i, j, weirdness);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.coastContinentalness,
                    MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[3]),
                    weirdness,
                    0.0F,
                    registryKey8
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.nearInlandContinentalness,
                    MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
                    weirdness,
                    0.0F,
                    registryKey2
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
                    weirdness,
                    0.0F,
                    registryKey3
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    this.nearInlandContinentalness,
                    MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[2], this.erosionParameters[3]),
                    weirdness,
                    0.0F,
                    registryKey7
            );
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[2], this.erosionParameters[3]),
                    weirdness,
                    0.0F,
                    registryKey7
            );
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[4], weirdness, 0.0F, registryKey4);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[4], weirdness, 0.0F, registryKey);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[4],
                    weirdness,
                    0.0F,
                    registryKey7
            );
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[5], weirdness, 0.0F, registryKey6);
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[5], weirdness, 0.0F, registryKey5);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    this.erosionParameters[5],
                    weirdness,
                    0.0F,
                    registryKey
            );
            this.writeBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[6], weirdness, 0.0F, registryKey4);
            if(i == 0) {
               this.writeBiomeParameters(parameters,
                       parameterRange,
                       parameterRange2,
                       MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness),
                       this.erosionParameters[6],
                       weirdness,
                       0.0F,
                       registryKey
               );
            }
         }
      }

   }

   private void writeValleyBiomes(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness) {
      this.writeBiomeParameters(parameters,
              this.frozenTemperature,
              this.defaultParameter,
              this.coastContinentalness,
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              weirdness.max() < 0L ? BiomeKeys.STONY_SHORE : BiomeKeys.FROZEN_RIVER
      );
      this.writeBiomeParameters(parameters,
              this.riverTemperatureParameters,
              this.defaultParameter,
              this.coastContinentalness,
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              weirdness.max() < 0L ? BiomeKeys.STONY_SHORE : BiomeKeys.RIVER
      );
      this.writeBiomeParameters(parameters,
              this.erodedRiverTemperatureParameters,
              this.defaultParameter,
              this.coastContinentalness,
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              weirdness.max() < 0L ? BiomeKeys.STONY_SHORE : HibiscusBiomes.ERODED_RIVER
      );
      this.writeBiomeParameters(parameters,
              this.frozenTemperature,
              this.defaultParameter,
              this.nearInlandContinentalness,
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              BiomeKeys.FROZEN_RIVER
      );
      this.writeBiomeParameters(parameters,
              this.riverTemperatureParameters,
              this.defaultParameter,
              this.nearInlandContinentalness,
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              BiomeKeys.RIVER
      );
      this.writeBiomeParameters(parameters,
              this.erodedRiverTemperatureParameters,
              this.defaultParameter,
              this.nearInlandContinentalness,
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
              weirdness,
              0.0F,
              HibiscusBiomes.ERODED_RIVER
      );
      this.writeBiomeParameters(parameters,
              this.frozenTemperature,
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness),
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[2], this.erosionParameters[5]),
              weirdness,
              0.0F,
              BiomeKeys.FROZEN_RIVER
      );
      this.writeBiomeParameters(parameters,
              this.riverTemperatureParameters,
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness),
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[2], this.erosionParameters[5]),
              weirdness,
              0.0F,
              BiomeKeys.RIVER
      );
      this.writeBiomeParameters(parameters,
              this.erodedRiverTemperatureParameters,
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness),
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[2], this.erosionParameters[5]),
              weirdness,
              0.0F,
              HibiscusBiomes.ERODED_RIVER
      );
      this.writeBiomeParameters(parameters, this.frozenTemperature, this.defaultParameter, this.coastContinentalness, this.erosionParameters[6], weirdness, 0.0F, BiomeKeys.FROZEN_RIVER);
      this.writeBiomeParameters(parameters, this.riverTemperatureParameters, this.defaultParameter, this.coastContinentalness, this.erosionParameters[6], weirdness, 0.0F, BiomeKeys.RIVER);
      this.writeBiomeParameters(parameters,
              this.erodedRiverTemperatureParameters,
              this.defaultParameter,
              this.coastContinentalness,
              this.erosionParameters[6],
              weirdness,
              0.0F,
              HibiscusBiomes.ERODED_RIVER
      );
      this.writeBiomeParameters(parameters,
              MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[2]),
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.farInlandContinentalness),
              this.erosionParameters[6],
              weirdness,
              0.0F,
              BiomeKeys.SWAMP
      );
      this.writeBiomeParameters(parameters,
              MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[3], this.temperatureParameters[4]),
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.farInlandContinentalness),
              this.erosionParameters[6],
              weirdness,
              0.0F,
              BiomeKeys.MANGROVE_SWAMP
      );
      this.writeBiomeParameters(parameters,
              this.frozenTemperature,
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.farInlandContinentalness),
              this.erosionParameters[6],
              weirdness,
              0.0F,
              BiomeKeys.FROZEN_RIVER
      );

      for(int i = 0; i < this.temperatureParameters.length; ++i) {
         MultiNoiseUtil.ParameterRange parameterRange = this.temperatureParameters[i];

         for(int j = 0; j < this.humidityParameters.length; ++j) {
            MultiNoiseUtil.ParameterRange parameterRange2 = this.humidityParameters[j];
            RegistryKey <Biome> registryKey = this.getBadlandsOrRegularBiome(i, j, weirdness);
            this.writeBiomeParameters(parameters,
                    parameterRange,
                    parameterRange2,
                    MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness),
                    MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
                    weirdness,
                    0.0F,
                    registryKey
            );
         }
      }

   }

   private void writeCaveBiomes(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters) {
      this.writeCaveBiomeParameters(parameters,
              this.defaultParameter,
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.of(0.8F, 1.0F),
              this.defaultParameter,
              this.defaultParameter,
              0.0F,
              BiomeKeys.DRIPSTONE_CAVES
      );
      this.writeCaveBiomeParameters(parameters,
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.of(0.7F, 1.0F),
              this.defaultParameter,
              this.defaultParameter,
              this.defaultParameter,
              0.0F,
              BiomeKeys.LUSH_CAVES
      );
      this.writeDeepDarkParameters(parameters,
              this.defaultParameter,
              this.defaultParameter,
              this.defaultParameter,
              MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]),
              this.defaultParameter,
              0.0F,
              BiomeKeys.DEEP_DARK
      );
   }

   private RegistryKey <Biome> getRegularBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      if(weirdness.max() < 0L) {
         return this.commonBiomes[temperature][humidity];
      }
      else {
         RegistryKey <Biome> registryKey = this.uncommonBiomes[temperature][humidity];
         return registryKey == null ? this.commonBiomes[temperature][humidity] : registryKey;
      }
   }

   private RegistryKey <Biome> getBadlandsOrRegularBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      return temperature == 4 ? this.getBadlandsBiome(humidity, weirdness) : this.getRegularBiome(temperature, humidity, weirdness);
   }

   private RegistryKey <Biome> getStratifiedDesertOrRegularBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      return temperature == 4 ? this.getStratifiedDesertBiome(humidity, weirdness) : this.getRegularBiome(temperature, humidity, weirdness);
   }

   private RegistryKey <Biome> getMountainStartBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      return temperature == 0 ? this.getMountainSlopeBiome(temperature, humidity, weirdness) : this.getBadlandsOrRegularBiome(temperature, humidity, weirdness);
   }

   private RegistryKey <Biome> getBiomeOrWindsweptSavanna(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, RegistryKey <Biome> biomeKey) {
      return temperature > 1 && humidity < 4 && weirdness.max() >= 0L ? BiomeKeys.WINDSWEPT_SAVANNA : biomeKey;
   }

   private RegistryKey <Biome> getBiomeOrChalkCliffs(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness, RegistryKey <Biome> biomeKey) {
      return temperature > 1 && temperature < 4 && humidity < 4 && humidity > 0 && weirdness.max() >= 0L ? HibiscusBiomes.WHITE_CLIFFS : getBiomeOrWindsweptSavanna(temperature,
              humidity,
              weirdness,
              biomeKey
      );
   }

   private RegistryKey <Biome> getErodedShoreBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      RegistryKey <Biome> registryKey = weirdness.max() >= 0L ? this.getRegularBiome(temperature, humidity, weirdness) : this.getShoreBiome(temperature, humidity);
      return this.getBiomeOrChalkCliffs(temperature, humidity, weirdness, registryKey);
   }

   private RegistryKey <Biome> getShoreBiome(int temperature, int humidity) {
      if(temperature == 0) {
         return BiomeKeys.SNOWY_BEACH;
      }
      else {
         return temperature == 4 ? BiomeKeys.DESERT : BiomeKeys.BEACH;
      }
   }

   private RegistryKey <Biome> getBadlandsBiome(int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      if(humidity < 2) {
         return weirdness.max() < 0L ? BiomeKeys.BADLANDS : BiomeKeys.ERODED_BADLANDS;
      }
      else {
         return humidity < 3 ? BiomeKeys.BADLANDS : BiomeKeys.WOODED_BADLANDS;
      }
   }

   private RegistryKey <Biome> getStratifiedDesertBiome(int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      return HibiscusBiomes.STRATIFIED_DESERT;
   }

   private RegistryKey <Biome> getShoreCliffBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      return temperature < 4 && temperature > 0 ? HibiscusBiomes.WHITE_CLIFFS : temperature == 0 ? BiomeKeys.STONY_PEAKS : getBadlandsBiome(humidity, weirdness);
   }


   private RegistryKey <Biome> getNearMountainBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      if(weirdness.max() >= 0L) {
         RegistryKey <Biome> registryKey = this.specialNearMountainBiomes[temperature][humidity];
         if(registryKey != null) {
            return registryKey;
         }
      }

      return this.nearMountainBiomes[temperature][humidity];
   }

   private RegistryKey <Biome> getStratifiedDesertOrNearMountainBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      return temperature == 4 ? this.getStratifiedDesertBiome(humidity, weirdness) : this.getNearMountainBiome(temperature, humidity, weirdness);
   }

   private RegistryKey <Biome> getPeakBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      if(temperature <= 2) {
         return weirdness.max() < 0L ? BiomeKeys.JAGGED_PEAKS : BiomeKeys.FROZEN_PEAKS;
      }
      else {
         return temperature == 3 ? BiomeKeys.STONY_PEAKS : this.getBadlandsBiome(humidity, weirdness);
      }
   }

   private RegistryKey <Biome> getMountainSlopeBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      if(temperature >= 3) {
         return this.getNearMountainBiome(temperature, humidity, weirdness);
      }
      else {
         return humidity <= 1 ? BiomeKeys.SNOWY_SLOPES : BiomeKeys.GROVE;
      }
   }

   private RegistryKey <Biome> getWindsweptOrRegularBiome(int temperature, int humidity, MultiNoiseUtil.ParameterRange weirdness) {
      RegistryKey <Biome> registryKey = this.windsweptBiomes[temperature][humidity];
      return registryKey == null ? this.getRegularBiome(temperature, humidity, weirdness) : registryKey;
   }

   private void writeBiomeParameters(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey <Biome> biome) {
      parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(0.0F), weirdness, offset), biome));
      parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(1.0F), weirdness, offset), biome));
   }

   private void writeCaveBiomeParameters(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey <Biome> biome) {
      parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(0.2F, 0.9F), weirdness, offset), biome));
   }

   private void writeDeepDarkParameters(Consumer <Pair <MultiNoiseUtil.NoiseHypercube, RegistryKey <Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey <Biome> biome) {
      parameters.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(1.1F), weirdness, offset), biome));
   }
}
