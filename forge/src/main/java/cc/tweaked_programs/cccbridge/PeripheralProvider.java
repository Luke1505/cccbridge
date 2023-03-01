package cc.tweaked_programs.cccbridge;

import cc.tweaked_programs.cccbridge.peripherals.SpeedControllerPeripheral;
import cc.tweaked_programs.cccbridge.peripherals.TrainPeripheral;
import cc.tweaked_programs.cccbridge.peripherals.TrainSignalPeripheral;
import com.simibubi.create.content.contraptions.relays.advanced.SpeedControllerTileEntity;
import com.simibubi.create.content.logistics.trains.management.edgePoint.signal.SignalTileEntity;
import com.simibubi.create.content.logistics.trains.management.edgePoint.station.StationTileEntity;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class PeripheralProvider implements IPeripheralProvider {

    @NotNull
    @Override
    public LazyOptional<IPeripheral> getPeripheral(@NotNull Level world, @NotNull BlockPos pos, @NotNull Direction side) {
        BlockEntity block = world.getBlockEntity(pos);

        if (block instanceof StationTileEntity)
            return LazyOptional.of(() -> new TrainPeripheral(pos, world));

        else if (block instanceof PeripheralBlockEntity peripheralBlock) {
            IPeripheral peripheral = peripheralBlock.getPeripheral(side);
            if (peripheral != null)
                LazyOptional.of(() -> peripheral);
        } else if (world.getBlockEntity(pos) instanceof SpeedControllerTileEntity) {
            return LazyOptional.of(() -> new SpeedControllerPeripheral(pos, world));
        } else if (world.getBlockEntity(pos) instanceof SignalTileEntity) {
            return LazyOptional.of(() -> new TrainSignalPeripheral(pos, world));
        }

        return LazyOptional.empty();
    }
}

