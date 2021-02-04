package com.rest.API.uri;

public final class UriMappings {
    public static final String INGREDIENT_URI = "/ingredient";
    public static final String PRODUCT_TYPOLOGY_URI = "/productTypology";
    public static final String PRODUCT_URI = "/product";
    public static final String PROMO_URI = "/promo";

    public static final String USER_URI = "/client";
    public static final String RESTAURANT_OWNER_URI = "/restaurantOwner";
    public static final String CLIENT_PAYMENY_METHOD_URI = "/clientPaymentMethod";
    public static final String CLIENT_ORDER_URI = "/clientOrder";
    public static final String RESTAURANT_OWNER_PRODUCT_URI = "/restaurantOwnerProduct";

    public static String getIngredientUri(int id) {
        return INGREDIENT_URI +"/"+ id;
    }

    public static String getProductTypologyUri(int id) {
        return PRODUCT_TYPOLOGY_URI +"/"+ id;
    }

    public static String getProductUri(int id) {
        return PRODUCT_URI +"/"+ id;
    }

    public static String getPromoUri(int id) {
        return PROMO_URI +"/"+ id;
    }

    public static String getUserUri(String id) {
        return USER_URI +"/"+ id;
    }

    public static String getRestaurantOwnerUri(int id) {
        return RESTAURANT_OWNER_URI +"/"+ id;
    }

    public static String getClientPaymenyMethodUri(int id) {
        return CLIENT_PAYMENY_METHOD_URI +"/"+ id;
    }

    public static String getClientOrderUri(int id) {
        return CLIENT_ORDER_URI +"/"+ id;
    }

    public static String getRestaurantOwnerProductUri(int id) {
        return RESTAURANT_OWNER_PRODUCT_URI +"/"+ id;
    }
}
