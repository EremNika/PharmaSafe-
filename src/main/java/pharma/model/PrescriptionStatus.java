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
        return false;
    }
}
