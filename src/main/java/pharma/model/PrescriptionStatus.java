package pharma.model;

public enum PrescriptionStatus {
    CREATED,
    VERIFIED,
    ASSEMBLED,
    CHECKED,
    ISSUED,
    REJECTED;

    public boolean canTransitionTo(PrescriptionStatus newStatus) {
        // TODO: занятие 4 - реализовать логику переходов:
        // CREATED -> VERIFIED -> ASSEMBLED -> CHECKED -> ISSUED
        // REJECTED из любого статуса до ISSUED
        switch(this){
            case CREATED:
                return newStatus==VERIFIED || newStatus==REJECTED;
            case VERIFIED:
                return newStatus==ASSEMBLED || newStatus==REJECTED;
            case ASSEMBLED:
                return newStatus==CHECKED || newStatus==REJECTED;
            case CHECKED:
                return newStatus==ISSUED || newStatus==REJECTED;
            default:
                return false;
        }
        
    }
}
