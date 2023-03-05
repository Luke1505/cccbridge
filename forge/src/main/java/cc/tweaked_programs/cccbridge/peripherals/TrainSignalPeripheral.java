package cc.tweaked_programs.cccbridge.peripherals;

import com.simibubi.create.content.logistics.trains.management.edgePoint.signal.SignalTileEntity;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.lua.MethodResult;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TrainSignalPeripheral implements IPeripheral {
    private final SignalTileEntity signal;
    private final Level level;

    public TrainSignalPeripheral(@NotNull SignalTileEntity signal, Level level) {
        this.level = level;
        this.signal = signal;
    }

    @LuaFunction
    public final MethodResult getSignalState() {
        SignalTileEntity.SignalState state = signal.getState();
        return MethodResult.of(true, state.toString());
    }

    public final MethodResult getTrainPresent() {
        //TODO: Implement this method properly (it's not working) lul (pls kill me)
        return MethodResult.of(true, null);

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
