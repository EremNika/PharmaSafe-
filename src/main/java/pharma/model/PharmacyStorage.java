package pharma.model;

import java.util.logging.Logger;
import java.util.logging.Level;

public class PharmacyStorage extends BaseEntity {
    private static final Logger LOGGER = Logger.getLogger(PharmacyStorage.class.getName());
    private String name;
    private StorageType type;
    private TemperatureMode supportedMode;
    private double currentTemperature;
    private double minTemp;
    private double maxTemp;
    private int capacity;

    public PharmacyStorage(String id,
                           String name,
                           StorageType type,
                           TemperatureMode supportedMode,
                           double minTemp,
                           double maxTemp,
                           int capacity) {
        super(id);
        this.name = name;
        this.type = type;
        this.supportedMode = supportedMode;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.capacity = capacity;
        this.currentTemperature = minTemp;
    }

    public boolean canStore(Medication medication) {
        
        // TODO: занятие 1 - проверить что supportedMode подходит для medication.getTemperatureMode()
        
        return this.supportedMode == medication.getTemperatureMode();
    }

    public void checkTemperature() {
        // TODO: занятие 1 - проверить currentTemperature в диапазоне [minTemp, maxTemp]
        if(currentTemperature<minTemp || currentTemperature>maxTemp){
            throw new IllegalArgumentException("Текущий уровень температуры не входит в необходимый диапазон");
        }        



        // TODO: занятие 4 - бросить TemperatureViolationException при нарушении
    }

    public void setCurrentTemperature(double currentTemperature) {
        // TODO: занятие 1 - добавить валидацию и логирование
        LOGGER.info(String.format("Температура сейчас - {}", currentTemperature));
        this.currentTemperature = currentTemperature;
    }

    public boolean isTemperatureViolation() {
        // TODO: занятие 1 - вернуть true если текущая температура вне диапазона
        return currentTemperature < minTemp || currentTemperature > maxTemp;
    }

    public String getName() {
        return name;
    }

    public StorageType getType() {
        return type;
    }

    public TemperatureMode getSupportedMode() {
        return supportedMode;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        // TODO: занятие 1 - улучшить формат
        // return "PharmacyStorage[" + id + "] " + name + " (" + type + ")";
        String res = String.format("PharmacyStorage [ %s ] %s ( %s )",id,name,type);
        return res;
    }
}
