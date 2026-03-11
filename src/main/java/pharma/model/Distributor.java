package pharma.model;

import java.util.Arrays;

public class Distributor extends BaseEntity {
    private String companyName;
    private String licenseNumber;
    private String contact;
    private String email;
    private boolean hasColdChain;
    private TemperatureMode[] suppliedModes;

    public Distributor(String id,
                       String companyName,
                       String licenseNumber,
                       String contact,
                       String email,
                       boolean hasColdChain,
                       TemperatureMode[] suppliedModes) {
        super(id);
        this.companyName = companyName;
        this.licenseNumber = licenseNumber;
        this.contact = contact;
        this.email = email;
        this.hasColdChain = hasColdChain;
        this.suppliedModes = suppliedModes;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public boolean isHasColdChain() {
        return hasColdChain;
    }

    public TemperatureMode[] getSuppliedModes() {
        return suppliedModes;
    }

    @Override
    public String toString() {
        // TODO: занятие 1 - сделать более читаемый формат
        // return "Distributor[" + id + "] " + companyName +
        //         ", modes=" + Arrays.toString(suppliedModes);
        String res=String.format("Distributor [ %s ] %s, modes= %s",id, companyName,Arrays.toString(suppliedModes));
        return res;
    }

}
