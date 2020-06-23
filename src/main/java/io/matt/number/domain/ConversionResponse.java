package io.matt.number.domain;

public class ConversionResponse {

    private String status;
    private String num_in_english;

    public ConversionResponse(){ }

    public ConversionResponse(String status, String num_in_english){
        this.status = status;
        this.num_in_english = num_in_english;
    }

    public String getStatus() {
        return status;
    }

    public String getNum_in_english() {
        return num_in_english;
    }

}
