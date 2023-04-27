package cc.tweaked_programs.cccbridge;

import cc.tweaked_programs.cccbridge.block.*;
import cc.tweaked_programs.cccbridge.blockEntity.RedRouterBlockEntityI;
import cc.tweaked_programs.cccbridge.blockEntity.ScrollerBlockEntityI;
import cc.tweaked_programs.cccbridge.blockEntity.SourceBlockEntityI;
import cc.tweaked_programs.cccbridge.blockEntity.TargetBlockEntityI;
import cc.tweaked_programs.cccbridge.display.SourceBlockDisplaySource;
import cc.tweaked_programs.cccbridge.display.TargetBlockDisplayTarget;
import com.simibubi.create.content.logistics.block.display.AllDisplayBehaviours;
import dan200.computercraft.api.ComputerCraftAPI;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class CCCBridge implements ModInitializer {
    public static final String MOD_ID = "cccbridge";
    //public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // Block (-entities)
        BlockRegister.registerBlockEntity("source_block", SourceBlockEntityI::new, new SourceBlock());
        BlockRegister.registerBlockEntity("target_block", TargetBlockEntityI::new, new TargetBlock());

        BlockRegister.registerBlockEntity("redrouter_block", RedRouterBlockEntityI::new, new RedRouterBlock());
        BlockRegister.registerBlockEntity("scroller_block", ScrollerBlockEntityI::new, new ScrollerBlock());

        // Create Display Stuff
        AllDisplayBehaviours.assignTile(AllDisplayBehaviours.register(new ResourceLocation(MOD_ID, "source_block_display_source"), new SourceBlockDisplaySource()), BlockRegister.getBlockEntityType("source_block"));
        AllDisplayBehaviours.assignTile(AllDisplayBehaviours.register(new ResourceLocation(MOD_ID, "target_block_display_target"), new TargetBlockDisplayTarget()), BlockRegister.getBlockEntityType("target_block"));

        // Misc
        Registry.register(Registry.PAINTING_VARIANT, new ResourceLocation(MOD_ID, "funny_redrouters"), new PaintingVariant(32,16));
        CCCSoundEvents.init();
        ComputerCraftAPI.registerPeripheralProvider(new PeripheralProvider());
    }
}
