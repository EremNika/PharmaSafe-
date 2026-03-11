package pharma.model;

public class Medication extends BaseEntity {
    private String name;
    private String inn;
    private String dosage;
    private String form;
    private boolean isPrescription;
    private boolean isNarcotic;
    private boolean isPsychotropic;
    private TemperatureMode temperatureMode;
    private int shelfLifeDays;

    public Medication(String id,
                      String name,
                      String inn,
                      String dosage,
                      String form,
                      boolean isPrescription,
                      boolean isNarcotic,
                      boolean isPsychotropic,
                      TemperatureMode temperatureMode,
                      int shelfLifeDays) {
        super(id);
        if (shelfLifeDays <= 0) {
            throw new IllegalArgumentException("Срок годности должен быть положительным числом");
        }
        
        this.name = name;
        this.inn = inn;
        this.dosage = dosage;
        this.form = form;
        this.isPrescription = isPrescription;
        this.isNarcotic = isNarcotic;
        this.isPsychotropic = isPsychotropic;
        this.temperatureMode = temperatureMode;
        this.shelfLifeDays = shelfLifeDays;
    }

    // TODO: занятие 1 - добавить валидацию (shelfLifeDays > 0)
  
    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }

    public String getDosage() {
        return dosage;
    }

    public String getForm() {
        return form;
    }

    public boolean isPrescription() {
        return isPrescription;
    }

    public boolean isNarcotic() {
        return isNarcotic;
    }

    public boolean isPsychotropic() {
        return isPsychotropic;
    }

    public TemperatureMode getTemperatureMode() {
        return temperatureMode;
    }

    public int getShelfLifeDays() {         
            return shelfLifeDays;        
    }

    @Override
    public String toString() {
        // TODO: занятие 1 - улучшить формат через String.format()
        // return "Medication[" + id + "] " + name + " " + dosage + " (" + form + ")";

        String res= String.format("Medication [ %s ] %s %s ( %s )",id,name,dosage,form);
       
        return res;
    }
}

