package pharma.coldchain;

import pharma.model.PharmacyStorage;
import pharma.model.StorageType;
import pharma.model.TemperatureMode;

public class FrozenStorage extends PharmacyStorage implements TemperatureControlled {

    public FrozenStorage(String id,
                         String name,
                         double minTemp,
                         double maxTemp,
                         int capacity) {
        super(id, name, StorageType.FREEZER,
                TemperatureMode.FROZEN, minTemp, maxTemp, capacity);
    }

    @Override
    public double getMinTemp() {
        return super.getMinTemp();
    }

    @Override
    public double getMaxTemp() {
        return super.getMaxTemp();
    }

    @Override
    public double getCurrentTemp() {
        return getCurrentTemperature();
    }
}
