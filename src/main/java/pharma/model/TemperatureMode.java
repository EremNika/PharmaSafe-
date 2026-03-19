package pharma.model;

import pharma.coldchain.TemperatureControlled;

public enum TemperatureMode implements TemperatureControlled {
    ROOM_TEMP("Комнатная температура", 18.0, 24.0),
    COOL("Охлаждение", 8.0, 15.0),
    REFRIGERATOR("Холодильник", 2.0, 8.0),
    FROZEN("Заморозка", -25.0, -15.0),
    MINUS_50("Сильная заморозка", -50.0, -40.0);

    String description;
    double minTemp;
    double maxTemp;
    double currentTemp;

    TemperatureMode(String description,double minTemp,double maxTemp){
        this.description=description;
        this.minTemp=minTemp;
        this.maxTemp=maxTemp;
        this.currentTemp=minTemp;
    }
    

    @Override
    public double getMinTemp(){
        return minTemp;
    }

    @Override
    public double getMaxTemp(){
        return maxTemp;
    }

    @Override
    public double getCurrentTemp(){
        return currentTemp;
    }




    
    
    // TODO: занятие 3 - добавить описание режимов и допустимые диапазоны
}

