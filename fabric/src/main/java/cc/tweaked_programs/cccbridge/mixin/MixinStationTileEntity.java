package cc.tweaked_programs.cccbridge.mixin;

import cc.tweaked_programs.cccbridge.blockEntity.IPeripheralBlockEntity;
import cc.tweaked_programs.cccbridge.peripherals.TrainStationPeripheral;
import com.simibubi.create.content.logistics.trains.management.edgePoint.station.StationTileEntity;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StationTileEntity.class)
public class MixinStationTileEntity implements IPeripheralBlockEntity {
    private TrainStationPeripheral peripheral;

    @Inject(at = @At(value = "HEAD"), method = "tick()V", remap = false)
    public void tick(CallbackInfo info) {
        if (peripheral != null)
            peripheral.tick();
    }

    @Override
    public IPeripheral getPeripheral(Direction side) {
        StationTileEntity station = (StationTileEntity) (Object) this;
        if (peripheral == null)
            peripheral = new TrainStationPeripheral(station, station.getLevel());
        return peripheral;
    }
}