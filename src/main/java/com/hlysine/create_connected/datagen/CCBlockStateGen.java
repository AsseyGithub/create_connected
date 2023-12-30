package com.hlysine.create_connected.datagen;

import com.hlysine.create_connected.content.brassgearbox.BrassGearboxBlock;
import com.hlysine.create_connected.content.linkedmodule.LinkedButtonBlock;
import com.hlysine.create_connected.content.sequencedpulsegenerator.SequencedPulseGeneratorBlock;
import com.mojang.datafixers.util.Function4;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.utility.Iterate;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import org.apache.commons.lang3.function.TriFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.function.Function;

public class CCBlockStateGen {
    public static <B extends LinkedButtonBlock> NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> linkedButton(ResourceLocation buttonOff, ResourceLocation buttonOn) {
        return (c, p) -> {
            ModelFile modelOff = p.models().withExistingParent(c.getName(), new ResourceLocation("block/block")).customLoader(CompositeModelBuilder::begin)
                    .child("button", p.models().nested().parent(p.models().getExistingFile(buttonOff)))
                    .child("link", p.models().nested().parent(p.models().getExistingFile(p.modLoc("block/linked_module/block"))))
                    .end()
                    .texture("particle", new ResourceLocation(Create.ID, "block/redstone_bridge"));
            ModelFile modelOn = p.models().withExistingParent(c.getName() + "_powered", new ResourceLocation("block/block")).customLoader(CompositeModelBuilder::begin)
                    .child("button", p.models().nested().parent(p.models().getExistingFile(buttonOn)))
                    .child("link", p.models().nested().parent(p.models().getExistingFile(p.modLoc("block/linked_module/block_powered"))))
                    .end()
                    .texture("particle", new ResourceLocation(Create.ID, "block/redstone_bridge_powered"));
            buttonBlock(p, c.get(), modelOff, modelOn);
        };
    }

    public static <B extends SequencedPulseGeneratorBlock> NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> sequencedPulseGenerator() {
        return (c, p) -> {
            Map<Boolean, ResourceLocation> baseOff = new HashMap<>();
            baseOff.put(false, p.modLoc("block/" + c.getName() + "_off"));
            baseOff.put(true, p.modLoc("block/" + c.getName() + "_off_reset"));
            Map<Boolean, ResourceLocation> baseOn = new HashMap<>();
            baseOn.put(false, p.modLoc("block/" + c.getName() + "_on"));
            baseOn.put(true, p.modLoc("block/" + c.getName() + "_on_reset"));
            ResourceLocation torchOff = new ResourceLocation("block/redstone_torch_off");
            ResourceLocation torchOn = new ResourceLocation("block/redstone_torch");

            Vector<ModelFile> models = new Vector<>(4);
            for (boolean isPowered : Iterate.falseAndTrue)
                for (boolean isPowering : Iterate.falseAndTrue)
                    for (boolean isSidePowered : Iterate.falseAndTrue)
                        models.add(p.models()
                                .withExistingParent(
                                        c.getName()
                                                + (isPowered ? "_powered" : "")
                                                + (isPowering ? "_powering" : "")
                                                + (isSidePowered ? "_reset" : ""),
                                        p.modLoc("block/" + c.getName())
                                )
                                .texture("1_top", isPowered ? baseOn.get(isSidePowered) : baseOff.get(isSidePowered))
                                .texture("torch", isPowering ? torchOn : torchOff)
                        );
            TriFunction<Boolean, Boolean, Boolean, ModelFile> modelFunc = (f1, f2, f3) -> models.get((f1 ? 4 : 0) + (f2 ? 2 : 0) + (f3 ? 1 : 0));

            p.horizontalBlock(c.get(), state -> modelFunc.apply(
                    state.getValue(SequencedPulseGeneratorBlock.POWERED),
                    state.getValue(SequencedPulseGeneratorBlock.POWERING),
                    state.getValue(SequencedPulseGeneratorBlock.POWERED_SIDE)
            ));
        };
    }

    public static <B extends BrassGearboxBlock> NonNullBiConsumer<DataGenContext<Block, B>, RegistrateBlockstateProvider> brassGearbox() {
        return (c, p) -> {
            ResourceLocation sideTop = p.modLoc("block/" + c.getName() + "_top");
            ResourceLocation sideBottom = p.modLoc("block/" + c.getName() + "_bottom");

            Vector<ModelFile> models = new Vector<>(16);
            for (boolean isFace1Flipped : Iterate.falseAndTrue)
                for (boolean isFace2Flipped : Iterate.falseAndTrue)
                    for (boolean isFace3Flipped : Iterate.falseAndTrue)
                        for (boolean isFace4Flipped : Iterate.falseAndTrue)
                            models.add(p.models()
                                    .withExistingParent(
                                            c.getName()
                                                    + (isFace1Flipped ? "_1" : "")
                                                    + (isFace2Flipped ? "_2" : "")
                                                    + (isFace3Flipped ? "_3" : "")
                                                    + (isFace4Flipped ? "_4" : ""),
                                            p.modLoc("block/brass_gearbox/block")
                                    )
                                    .texture("1", isFace1Flipped ? sideTop : sideBottom)
                                    .texture("2", isFace2Flipped ? sideTop : sideBottom)
                                    .texture("3", isFace3Flipped ? sideTop : sideBottom)
                                    .texture("4", isFace4Flipped ? sideTop : sideBottom)
                            );
            Function4<Boolean, Boolean, Boolean, Boolean, ModelFile> modelFunc = (f1, f2, f3, f4) -> models.get((f1 ? 8 : 0) + (f2 ? 4 : 0) + (f3 ? 2 : 0) + (f4 ? 1 : 0));

            noZRotationAxisBlock(c, p, state -> modelFunc.apply(
                    state.getValue(BrassGearboxBlock.FACE_1_FLIPPED),
                    state.getValue(BrassGearboxBlock.FACE_2_FLIPPED),
                    state.getValue(BrassGearboxBlock.FACE_3_FLIPPED),
                    state.getValue(BrassGearboxBlock.FACE_4_FLIPPED)
            ));
        };
    }

    public static <T extends Block> void noZRotationAxisBlock(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,
                                                              Function<BlockState, ModelFile> modelFunc) {
        noZRotationAxisBlock(ctx, prov, modelFunc, false);
    }

    public static <T extends Block> void noZRotationAxisBlock(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,
                                                              Function<BlockState, ModelFile> modelFunc, boolean uvLock) {
        prov.getVariantBuilder(ctx.getEntry())
                .forAllStatesExcept(state -> {
                    Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .uvLock(uvLock)
                            .rotationX(axis == Direction.Axis.Y ? 0 : 90)
                            .rotationY(axis == Direction.Axis.X ? 90 : 0)
                            .build();
                }, BlockStateProperties.WATERLOGGED);
    }

    public static void buttonBlock(RegistrateBlockstateProvider prov, ButtonBlock block, ModelFile button, ModelFile buttonPressed) {
        prov.getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(ButtonBlock.FACING);
            AttachFace face = state.getValue(ButtonBlock.FACE);
            boolean powered = state.getValue(ButtonBlock.POWERED);

            return ConfiguredModel.builder()
                    .modelFile(powered ? buttonPressed : button)
                    .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? -90 : 180))
                    .rotationY((int) (face == AttachFace.CEILING || face == AttachFace.WALL ? facing : facing.getOpposite()).toYRot())
                    .uvLock(false)
                    .build();
        });
    }
}
