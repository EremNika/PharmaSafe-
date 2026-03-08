package pharma.coldchain;

import pharma.model.PharmacyStorage;
import pharma.model.StorageType;
import pharma.model.TemperatureMode;

public class RefrigeratedStorage extends PharmacyStorage implements TemperatureControlled {

    public RefrigeratedStorage(String id,
                               String name,
                               double minTemp,
                               double maxTemp,
                               int capacity) {
        super(id, name, StorageType.REFRIGERATOR_UNIT,
                TemperatureMode.REFRIGERATOR, minTemp, maxTemp, capacity);
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
