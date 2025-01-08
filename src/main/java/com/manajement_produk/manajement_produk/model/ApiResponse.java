package com.manajement_produk.manajement_produk.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {
    // Getter dan Setter
    private int responseCode;
    private String responseMessage;
    private T body;

    // Constructor yang diubah menjadi public
    public ApiResponse(int responseCode, String responseMessage, T body) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.body = body;
    }

}
