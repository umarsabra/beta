package com.marketapp.beta.Utils;


import com.marketapp.beta.Exception.InvalidBarcodeException;

public class BarcodeAnalyser {
    private final String barcodeWeightItemPrefix = "22";
    private final String barcode;
    private final String priceBarcode;
    private final Boolean isWeightItem;
    private final Integer weightInGrams;




    public BarcodeAnalyser(String priceBarcode) throws InvalidBarcodeException {
        this.priceBarcode = priceBarcode;
        String prefix = priceBarcode.substring(0, 2);
        if(prefix.equals(barcodeWeightItemPrefix)){
            try{
                this.isWeightItem = true;
                this.barcode = priceBarcode.substring(2, 8);
                this.weightInGrams = Integer.parseInt(priceBarcode.substring(8, 12));
            } catch (Exception e){
                throw new InvalidBarcodeException();
            }
        } else {
            this.isWeightItem = false;
            this.weightInGrams= 0;
            this.barcode = priceBarcode;

        }
    }

    public String getBarcodeWeightItemPrefix() {
        return barcodeWeightItemPrefix;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getPriceBarcode() {
        return priceBarcode;
    }

    public Boolean getWeightItem() {
        return isWeightItem;
    }

    public Integer getWeightInGrams() {
        return weightInGrams;
    }


}
