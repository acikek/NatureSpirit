//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hibiscus.naturespirit.world.feature.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.util.HibiscusWorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import java.util.List;
import java.util.function.BiConsumer;

public class SugiTrunkPlacer extends TrunkPlacer {
   public static final Codec <SugiTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
      return trunkPlacerParts(instance).and(instance.group(IntProvider.POSITIVE_CODEC.fieldOf("extra_branch_steps")
              .forGetter((trunkPlacer) -> {
                 return trunkPlacer.extraBranchSteps;
              }), Codec.floatRange(0.0F, 1.0F).fieldOf("place_branch_per_log_probability").forGetter((trunkPlacer) -> {
         return trunkPlacer.placeBranchPerLogProbability;
      }), IntProvider.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter((trunkPlacer) -> {
         return trunkPlacer.extraBranchLength;
      }), RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_grow_through").forGetter((trunkPlacer) -> {
         return trunkPlacer.canGrowThrough;
      }))).apply(instance, SugiTrunkPlacer::new);
   });
   private final IntProvider extraBranchSteps;
   private final float placeBranchPerLogProbability;
   private final IntProvider extraBranchLength;
   private final HolderSet <Block> canGrowThrough;

   public SugiTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, IntProvider extraBranchSteps, float placeBranchPerLogProbability, IntProvider extraBranchLength, HolderSet <Block> canGrowThrough) {
      super(baseHeight, firstRandomHeight, secondRandomHeight);
      this.extraBranchSteps = extraBranchSteps;
      this.placeBranchPerLogProbability = placeBranchPerLogProbability;
      this.extraBranchLength = extraBranchLength;
      this.canGrowThrough = canGrowThrough;
   }

   protected TrunkPlacerType <?> type() {
      return HibiscusWorldGen.SUGI_TRUNK_PLACER;
   }

   public List <FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer <BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
      List <FoliageAttachment> list = Lists.newArrayList();
      MutableBlockPos mutable = new MutableBlockPos();

      for(int i = 0; i < height; ++i) {
         int j = startPos.getY() + i;
         Direction direction = Plane.HORIZONTAL.getRandomDirection(random);
         Direction direction2 = direction.getClockWise();
         Direction direction3 = direction2.getClockWise();
         if(this.placeLog(world,
                 replacer,
                 random,
                 mutable.set(startPos.getX(), j, startPos.getZ()),
                 config
         ) && i < height - 1) {
            int k = this.extraBranchLength.sample(random);
            int l = Math.max(0, k - this.extraBranchLength.sample(random) - 1);
            int l2 = Math.max(random.nextIntBetweenInclusive(1, 2), k - this.extraBranchLength.sample(random) - 1);
            int m = height - i - 2;
            if(i > 2) {
               this.generateExtraBranch(world, replacer, random, height, config, list, mutable, j, direction, i >= height - 2 ? l2 : l,
                       Math.min(m, 5), i);
               if(random.nextFloat() < this.placeBranchPerLogProbability - 0.35) {
                  this.generateExtraBranch(world,
                          replacer,
                          random,
                          height,
                          config,
                          list,
                          mutable,
                          j,
                          direction2,
                          l,
                          Math.round(m / 1.5F),
                          i
                  );
               }
               if(random.nextFloat() < this.placeBranchPerLogProbability - 0.45) {
                  this.generateExtraBranch(world,
                          replacer,
                          random,
                          height,
                          config,
                          list,
                          mutable,
                          j,
                          direction3,
                          l,
                          Math.round(m / 1.5F),
                          i
                  );
               }
            }
         }
         if(i == height - 1) {
            list.add(new FoliageAttachment(mutable.set(startPos.getX(), j + 1, startPos.getZ()), 0, false));
         }
      }

      return list;
   }

   private void generateExtraBranch(LevelSimulatedReader world, BiConsumer <BlockPos, BlockState> replacer, RandomSource random, int height, TreeConfiguration config, List <FoliageAttachment> nodes, MutableBlockPos pos, int yOffset, Direction direction, int length, int steps, int b) {
      int i = yOffset + length;
      int j = pos.getX();
      int k = pos.getZ();
      boolean bl = random.nextFloat() < .5F;
      Direction direction2 = bl ? direction.getClockWise() : direction.getCounterClockWise();

      for(int l = length; l < height && steps > 0 && (steps < (bl ? 7 : 10)); --steps) {
         if(l >= 1) {
            j += direction.getStepX();
            if(bl) {
               k += Math.max(0, direction2.getStepZ() - random.nextInt(2));
            }
            else {
               k += direction.getStepZ();
            }
            i = yOffset;
            if(this.placeLog(world, replacer, random, pos.set(j, yOffset, k), config, blockState -> {
               return blockState.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
            })) {
               i = yOffset + 1;
            }
         }
         ++l;
      }

      if(i - yOffset >= 1) {
         BlockPos blockPos = new BlockPos(j, i, k);
         nodes.add(new FoliageAttachment(blockPos, b > 4 ? 0 : 1, false));
      }

   }

   protected boolean validTreePos(LevelSimulatedReader world, BlockPos pos) {
      return super.validTreePos(world, pos) || world.isStateAtPosition(pos, (state) -> {
         return state.is(this.canGrowThrough);
      });
   }
}
