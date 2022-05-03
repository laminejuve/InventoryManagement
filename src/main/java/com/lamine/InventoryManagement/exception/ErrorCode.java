package com.lamine.InventoryManagement.exception;

public enum ErrorCode {

    ARTICLE_NOT_FOUND(1000),
    CATEGORY_NOT_FOUND(2000),
    CLIENT_NOT_FOUND(3000),
    COMMANDE_CLIENT_NOT_FOUND(4000),
    COMMANDE_FORNISSEUR_NOT_FOUND(5000),
    ENTREPRISE_NOT_FOUND(6000),
    FORNISSEUR_NOT_FOUND(7000),
    LIGNE_COMMANDE_CLIENT_NOT_FOUND(8000),
    LIGNE_COMMANDE_FORNISSEUR_NOT_FOUND(9000),
    LIGNE_VENTE_NOT_FOUND(10000),
    MVM_STOCK_NOT_FOUND(11000),
    USER_NOT_FOUND(12000),
    VENTE_NOT_FOUND(13000);
    private int code;
    ErrorCode (int code){
        this.code = code;
    }
    public int getCode(){
        return code ;
    }
}
