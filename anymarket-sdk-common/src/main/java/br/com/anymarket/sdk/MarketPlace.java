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
    LINIO("Linio"),
    NETSHOES("Netshoes"),
    MAGAZINE_LUIZA("Magazine Luiza"),
    CARREFOUR("Carrefour"),
    AMAZON("Amazon"),
    BUSCAPE("Buscapé"),
    ORACLECOMMERCE("Oracle Commerce Cloud"),
    MADEIRA_MADEIRA("Madeira Madeira"),
    CISSA_MAGAZINE("Cissa Magazine"),
    B2W_NEW_API("B2W Nova API"),
    SARAIVA("Saraiva"),
    HUB_SALES("Hub Sales"),
    ZOOM("Zoom"),
    WEB_CONTINENTAL("Web Continental"),
    RICARDO_ELETRO("Ricardo Eletro"),
    CENTAURO("Centauro"),
    PORTAL_DO_MEDICO("Portal do Médico"),
    TRAY("Tray"),
    CAMICADO("Camicado"),
    GALITHX("Go Core Marketplace (Galithx e Gorila Z)"),
    COLOMBO("Lojas Colombo"),
    DIA_GROUP("Dia Group"),
    ELETRUM("Eletrum"),
    OPTEMAIS("Optemais"),
    EFACIL("eFácil"),
    LEROY_MERLIN("Leroy Merlin"),
    COBASI("Cobasi"),
    CATWALK("Catwalk"),
    WISH("Wish"),
    HOME_TO_GO("Home To Go"),
    CONNECT_PARTS("Connect Parts"),
    GARBARINO("Garbarino"),
    NOVO_MUNDO("Novo Mundo"),
    POSTHAUS("Posthaus"),
    MARABRAZ("Marabraz"),
    LOJA_VIVO("Loja Vivo"),
    ESTANTE_VIRTUAL("Estante Virtual"),
    NUVEMSHOP("Nuvemshop"),
    RI_HAPPY("Ri Happy"),
    FAST_SHOP("Fast Shop");
    private String name;

    private MarketPlace(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.name;
    }
}
