package br.com.anymarket.sdk.product.dto;


public enum Origin {

    NACIONAL(0),
    ESTRANGEIRA_IMPORTACAO_DIRETA(1),
    ESTRANGEIRA_ADQUIRIDA_NO_MERCADO_INTERNO(2),
    NACIONAL_IMPORTACAO_MAIS_40_PORCENTO(3), // Nacional, mercadoria ou bem com Conteúdo de Importação superior a 40%
    NACIONAL_CONFORMIDADE_AJUSTES(4), //cuja produção tenha sido feita em conformidade com os processos produtivos básicos de que tratam as legislações citadas nos Ajustes
    NACIONAL_IMPORTACAO_MENOS_40_PORCENTO(5), //Nacional, mercadoria ou bem com Conteúdo de Importação inferior ou igual a 40%
    ESTRANGEIRA_SEM_SIMILAR_NACIONAL(6), //Importação direta, sem similar nacional, constante em lista da CAMEX
    ESTRANGEIRA_ADQUIRIDA_NO_MERCADO_INTERNO_SEM_SIMILAR(7), // Estrangeira - Adquirida no mercado interno, sem similar nacional, constante em lista da CAMEX
    NACIONAL_IMPORTACAO_MAIS_70_PORCENTO(8);  //Nacional, mercadoria ou bem com Conteúdo de Importação

    private Integer id;

    Origin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
