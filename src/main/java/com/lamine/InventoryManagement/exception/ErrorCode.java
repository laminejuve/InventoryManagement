package com.lamine.InventoryManagement.exception;

public enum ErrorCode {

    ARTICLE_NOT_FOUND(1000),
    ARTICLE_NOT_VALID(1001),
    CATEGORY_NOT_FOUND(2000),
    CATEGORY_NOT_VALID(2001),
    CLIENT_NOT_FOUND(3000),
    CLIENT_NOT_VALID(3001),
    COMMANDE_CLIENT_NOT_FOUND(4000),

    COMMANDE_CLIENT_NOT_VALID(40001),
    COMMANDE_FORNISSEUR_NOT_FOUND(5000),
    COMMANDE_FORNISSEUR_NOT_VALID(5001),
    COMMANDE_FORNISSEUR_NOT_MODIFIABLE(5002),
    ENTREPRISE_NOT_FOUND(6000),
    ENTREPRISE_NOT_VALID(6001),
    FORNISSEUR_NOT_FOUND(7000),
    FORNISSEUR_NOT_VALID(7001),
    LIGNE_COMMANDE_CLIENT_NOT_FOUND(8000),
    LIGNE_COMMANDE_CLIENT_NOT_VALID(8001),
    LIGNE_COMMANDE_FORNISSEUR_NOT_FOUND(9000),
    LIGNE_COMMANDE_FORNISSEUR_NOT_VALID(9001),
    LIGNE_VENTE_NOT_FOUND(10000),
    LIGNE_VENTE_NOT_VALID(10001),
    MVM_STOCK_NOT_FOUND(11000),
    MVM_STOCK_NOT_VALID(11001),
    UTILISATEUR_NOT_FOUND(12000),
    UTILISATEUR_NOT_VALID(12001),
    UTILISATEUR_PASSWORD_NOT_GIVEN(12003),
    VENTE_NOT_FOUND(13000),
    VENTE_NOT_VALID(13001), COMMANDE_CLIENT_NOT_MODIFIABLE(40002),
    UPDATE_PHOTO_EXCEPTION(14000), UNKOWN_CONTEXT(14001);
    private int code;
    ErrorCode (int code){
        this.code = code;
    }
    public int getCode(){
        return code ;
    }
}
