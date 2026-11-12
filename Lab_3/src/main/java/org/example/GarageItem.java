package org.example;

// COMPOSITE
// Component: both a single car and a whole section are "garage items"
interface GarageItem {
    void show(String indent);

    double getValue();
}
