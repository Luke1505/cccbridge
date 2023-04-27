package cc.tweaked_programs.cccbridge;

import cc.tweaked_programs.cccbridge.blockEntity.IPeripheralBlockEntity;
import cc.tweaked_programs.cccbridge.peripherals.TrainSignalPeripheral;
import com.simibubi.create.content.logistics.trains.management.edgePoint.signal.SignalTileEntity;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class PeripheralProvider implements IPeripheralProvider {

    @Override
    public IPeripheral getPeripheral(@NotNull Level world, @NotNull BlockPos pos, @NotNull Direction side) {
        BlockEntity block = world.getBlockEntity(pos);
        if (block instanceof IPeripheralBlockEntity peripheralBlock) {
            return peripheralBlock.getPeripheral(side);
        }
        return null;
};

}
