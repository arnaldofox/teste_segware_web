package com.segware.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe VO Post
 *
 * @author Arnaldo
 */
public class PostVO implements Serializable {

    private static final long serialVersionUID = -8807661298523278904L;

    private Long id;

    private String descricao;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    private Date dataHora;

    private String ip;

    private Long upVote;

    private Long downVote;

    public PostVO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getUpVote() {
        return upVote;
    }

    public void setUpVote(Long upVote) {
        this.upVote = upVote;
    }

    public Long getDownVote() {
        return downVote;
    }

    public void setDownVote(Long downVote) {
        this.downVote = downVote;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
