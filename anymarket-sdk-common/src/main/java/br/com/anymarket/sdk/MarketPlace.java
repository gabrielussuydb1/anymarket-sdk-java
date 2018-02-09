package br.com.anymarket.sdk;

public enum MarketPlace {
    CNOVA("Via Varejo"),
    MAGENTO("Magento"),
    MERCADO_LIVRE("Mercado Livre"),
    B2W("B2W"),
    ECOMMERCE("E-Commerce"),
    WALMART("Walmart"),
    VTEX("VTEX"),
    GFG("GFG - Dafiti"),
    NETSHOES("Netshoes"),
    MAGAZINE_LUIZA("Magazine Luiza"),
    CARREFOUR("Carrefour"),
    AMAZON("Amazon"),
    BUSCAPE("Buscapé");

    private String name;

    private MarketPlace(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.name;
    }
}
