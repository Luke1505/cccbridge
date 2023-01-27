package cc.tweaked_programs.cccbridge.peripherals;

import com.simibubi.create.content.logistics.trains.management.edgePoint.signal.SignalTileEntity;
import com.simibubi.create.content.logistics.trains.management.edgePoint.station.StationTileEntity;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.lua.MethodResult;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TrainSignalPeripheral implements IPeripheral {
    private final SignalTileEntity signal;
    private final Level level;

    public TrainSignalPeripheral(@NotNull BlockPos pos, Level level) {
        this.level = level;
        signal = (SignalTileEntity) level.getBlockEntity(pos);
    }
    
    @LuaFunction
    public final MethodResult getSignalState() {
        return MethodResult.of(true, signal.getState());
    }



    @NotNull
    @Override
    public String getType() {
        return "train_signal";
    }

    @Override
    public boolean equals(@Nullable IPeripheral iPeripheral) {
        return this == iPeripheral;
    }
}
