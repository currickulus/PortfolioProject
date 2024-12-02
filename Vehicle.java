class Vehicle {
    private String make;
    private String model;
    private double milesPerGallon;


    public Vehicle(String make, String model, double milesPerGallon) {
        this.make = make;
        this.model = model;
        this.milesPerGallon = milesPerGallon;
    }

    // Getters
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getMilesPerGallon() {
        return milesPerGallon;
    }

    // toString method
    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", milesPerGallon=" + milesPerGallon +
                '}';
    }
}