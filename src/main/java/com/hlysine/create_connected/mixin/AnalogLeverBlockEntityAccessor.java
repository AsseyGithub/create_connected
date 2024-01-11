package com.hlysine.create_connected.mixin;

import com.simibubi.create.content.redstone.analogLever.AnalogLeverBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = AnalogLeverBlockEntity.class, remap = false)
public interface AnalogLeverBlockEntityAccessor {
    @Accessor
    int getLastChange();
}
