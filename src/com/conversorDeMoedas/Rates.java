package com.conversorDeMoedas;

public record Rates
        (double USD, double EUR, double CAD, double BRL, double MXN, double ARS, double GBP) {


    public double getRateFor(String currency) throws NoSuchFieldException, IllegalAccessException {
        return this.getClass().getDeclaredField(currency).getDouble(this);
    }
}
