package dto;

public class DashboardDTO {
    int nbFlights;
    DashboardBookingsDTO nbBookings;

    public int getNbFlights() {
        return nbFlights;
    }

    public void setNbFlights(int nbFlights) {
        this.nbFlights = nbFlights;
    }

    public DashboardBookingsDTO getNbBookings() {
        return nbBookings;
    }

    public void setNbBookings(DashboardBookingsDTO nbBookings) {
        this.nbBookings = nbBookings;
    }

    // Constructor
    public DashboardDTO(int nbFlights, DashboardBookingsDTO nbBookings) {
        this.nbFlights = nbFlights;
        this.nbBookings = nbBookings;
    }
}

