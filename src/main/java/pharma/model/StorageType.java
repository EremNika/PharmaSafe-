package pharma.model;

public enum StorageType {
    TRADE_HALL (TemperatureMode.COOL),
    REFRIGERATOR_UNIT (TemperatureMode.REFRIGERATOR),
    FREEZER (TemperatureMode.FROZEN),
    SAFE (TemperatureMode.ROOM_TEMP),
    WAREHOUSE (TemperatureMode.MINUS_50);

    private TemperatureMode temperatureMode;

    StorageType(TemperatureMode temperatureMode){
        this.temperatureMode=temperatureMode;
    }

    public TemperatureMode getTemperatureMode(){
        return temperatureMode;
    }
    
    // TODO: занятие 3 - привязать тип к рекомендуемому TemperatureMode
}

