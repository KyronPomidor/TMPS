package org.example;

// Builder pattern for class

public class ServiceOrder {

    private final String customerName;
    private final String carModel;
    private final String plateNumber;
    private final boolean urgent;
    private final String notes;

    private ServiceOrder(Builder builder) {
        this.customerName = builder.customerName;
        this.carModel = builder.carModel;
        this.plateNumber = builder.plateNumber;
        this.urgent = builder.urgent;
        this.notes = builder.notes;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Order[customer=" + customerName
                + ",\n car=" + carModel
                + ",\n plate=" + plateNumber
                + ",\n urgent=" + urgent
                + ",\n notes=" + (notes.isEmpty() ? "-" : notes) + "]";
    }

    public static class Builder {
        private String customerName;
        private String carModel;
        private String plateNumber = "UNKNOWN";
        private boolean urgent = false;
        private String notes = "";

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder carModel(String carModel) {
            this.carModel = carModel;
            return this;
        }

        public Builder plateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
            return this;
        }

        public Builder urgent(boolean urgent) {
            this.urgent = urgent;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public ServiceOrder build() {
            return new ServiceOrder(this);
        }
    }
}
