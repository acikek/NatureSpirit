package net.hibiscus.naturespirit.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TrunkPlacerType.class) public interface TrunkPlacerTypeMixin {

   @Invoker static <P extends TrunkPlacer> TrunkPlacerType <P> callRegister(String id, Codec <P> codec) {
      throw new IllegalStateException();
   }
}
