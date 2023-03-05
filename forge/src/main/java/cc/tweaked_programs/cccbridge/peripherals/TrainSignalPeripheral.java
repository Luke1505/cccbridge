package cc.tweaked_programs.cccbridge.peripherals;

import com.simibubi.create.content.logistics.trains.management.edgePoint.signal.SignalTileEntity;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.lua.MethodResult;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TrainSignalPeripheral implements IPeripheral {
    private final SignalTileEntity signal;
    private final Level level;
    private final List<IComputerAccess> pcs = new ArrayList<>();
    private SignalTileEntity.SignalState oldstate;

    public TrainSignalPeripheral(@NotNull SignalTileEntity signal, Level level) {
        this.level = level;
        this.signal = signal;
    }

    @LuaFunction
    public final MethodResult getSignalState() {
        SignalTileEntity.SignalState state = signal.getState();
        return MethodResult.of(true, state.toString());
    }

    @LuaFunction
    public final MethodResult getTrainPresent() {
        SignalTileEntity.SignalState state = signal.getState();
        if (state == SignalTileEntity.SignalState.RED) return MethodResult.of(true);
        else
            return MethodResult.of(false);

    }

    //on train enter track path (signal)
    private void enterEvent() {
        for (IComputerAccess pc : pcs) {
            pc.queueEvent("enter", signal.getSignal().position);
        }
    }

    //on train leave track path (signal)
    private void leaveEvent() {
        for (IComputerAccess pc : pcs) {
            pc.queueEvent("departure", signal.getSignal().position);
        }
    }

    public void tick() {
        if (signal.getState() == SignalTileEntity.SignalState.RED && oldstate != SignalTileEntity.SignalState.RED) {
            enterEvent();
        } else if (signal.getState() == SignalTileEntity.SignalState.GREEN && oldstate != SignalTileEntity.SignalState.GREEN) {
            leaveEvent();
        }
        oldstate = signal.getState();
    }

    @NotNull
    @Override
    public String getType() {
        return "train_signal";
    }

    @Override
    public void attach(@NotNull IComputerAccess iComputerAccess) {
        pcs.add(iComputerAccess);
    }

    @Override
    public void detach(@NotNull IComputerAccess iComputerAccess) {
        pcs.removeIf(p -> (p.getID() == iComputerAccess.getID()));
    }

    @Override
    public boolean equals(@Nullable IPeripheral iPeripheral) {
        return this == iPeripheral;
    }
}
