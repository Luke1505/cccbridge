package cc.tweaked_programs.cccbridge;

import cc.tweaked_programs.cccbridge.blockEntity.IPeripheralBlockEntity;
import cc.tweaked_programs.cccbridge.peripherals.TrainSignalPeripheral;
import com.simibubi.create.content.logistics.trains.management.edgePoint.signal.SignalTileEntity;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class PeripheralProvider implements IPeripheralProvider {

    @NotNull
    @Override
    public LazyOptional<IPeripheral> getPeripheral(@NotNull Level world, @NotNull BlockPos pos, @NotNull Direction side) {
        BlockEntity block = world.getBlockEntity(pos);
        if (block instanceof SignalTileEntity) {
            return LazyOptional.of(() -> new TrainSignalPeripheral((SignalTileEntity) block, world));
        } else if (block instanceof IPeripheralBlockEntity peripheralBlock) {
            IPeripheral peripheral = peripheralBlock.getPeripheral(side);
            if (peripheral != null)
                return LazyOptional.of(() -> peripheral);
        }
        return LazyOptional.empty();
    }
}

