package com.dutra.food_api.api.model;


/*
 * @JacksonXmlRootElement é uma alternativa à @JsonRootName e
 * @JacksonXmlProperty à @JsonProperty.
 *
 * A diferença é que as anotações iniciadas com @JacksonXml só afetam
 * a serialização em XML. Já as anotações iniciadas com @Json
 * afetam tanto a serialização JSON como também XML.
 */

import com.dutra.food_api.entities.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;


//@JacksonXmlRootElement(localName = "cozinhas")
@JsonRootName("cozinhas")
public class CozinhasXmlWrapper {


    //	@JacksonXmlProperty(localName = "cozinha")
    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Cozinha> cozinhas;

    public CozinhasXmlWrapper() {}
    public CozinhasXmlWrapper(List<Cozinha> cozinhas) {
        this.cozinhas = cozinhas;
    }

    public List<Cozinha> getCozinhas() {
        return cozinhas;
    }

    public void setCozinhas(List<Cozinha> cozinhas) {
        this.cozinhas = cozinhas;
    }


}