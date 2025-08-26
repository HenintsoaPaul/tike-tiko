package dto;

public class DashboardBookingsDTO {
    int nbConfirmed;
    int nbPending;
    int nbCancelled;

    public int getNbConfirmed() {
        return nbConfirmed;
    }

    public void setNbConfirmed(int nbConfirmed) {
        this.nbConfirmed = nbConfirmed;
    }

    public int getNbPending() {
        return nbPending;
    }

    public void setNbPending(int nbPending) {
        this.nbPending = nbPending;
    }

    public int getNbCancelled() {
        return nbCancelled;
    }

    public void setNbCancelled(int nbCancelled) {
        this.nbCancelled = nbCancelled;
    }

    // Constructor
    public DashboardBookingsDTO(int nbConfirmed, int nbPending, int nbCancelled) {
        this.nbConfirmed = nbConfirmed;
        this.nbPending = nbPending;
        this.nbCancelled = nbCancelled;
    }
}
