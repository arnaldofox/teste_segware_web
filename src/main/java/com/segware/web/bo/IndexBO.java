package com.segware.web.bo;

import com.segware.web.mb.exception.BusinessException;
import com.segware.web.mb.exception.ErrorDetails;
import com.segware.web.mb.exception.HttpException;
import com.segware.web.vo.PostVO;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Classe para as chamadas dos serviços RESTful
 *
 * @author Arnaldo
 */
@Service
public class IndexBO implements Serializable {

    private static final long serialVersionUID = -8656842396194759795L;

    private final String SERVICE_API = "/api/v1";
    private final String SERVICE_LIST_ALL = SERVICE_API + "/posts";
    private final String SERVICE_GET_POST = SERVICE_API + "/post/{id}";
    private final String SERVICE_CREATE = SERVICE_API + "/createpost";
    private final String SERVICE_UP_VOTE = SERVICE_API + "/upVote/{id}";
    private final String SERVICE_DOWN_VOTE = SERVICE_API + "/downVote/{id}";

    @Value("${services.post.url}")
    private String url;

    @Value("${services.post.port}")
    private String porta;

    public void createPost(PostVO post) throws HttpException, BusinessException {

        if (post == null) {
            throw new BusinessException("Objeto Post é obrigatório");
        }

        if (post.getDescricao() == null || "".equals(post.getDescricao().trim())) {
            throw new BusinessException("Postagem é obrigatório");
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PostVO> entity = new HttpEntity<>(post, headers);

        ResponseEntity<PostVO> out = null;
        try {
            out = restTemplate.exchange(url + ":" + porta + SERVICE_CREATE, HttpMethod.POST, entity, PostVO.class);
        } catch (HttpClientErrorException ex) {
            throw new HttpException(ex.getStatusCode().toString());
        } catch (RestClientException cex) {
            throw new HttpException("Erro de conexão com os serviços WS");
        }

        tratarHttpCode(out);
    }

    public void upVote(PostVO post) throws HttpException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PostVO> out = null;
        try {
            out = restTemplate.exchange(url + ":" + porta + SERVICE_UP_VOTE.replace("{id}", post.getId().toString()), HttpMethod.GET, null, PostVO.class);
        } catch (HttpClientErrorException ex) {
            throw new HttpException(ex.getStatusCode().toString());
        } catch (RestClientException cex) {
            throw new HttpException("Erro de conexão com os serviços WS");
        }

        tratarHttpCode(out);

    }

    public void downVote(PostVO post) throws HttpException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PostVO> out = null;
        try {
            out = restTemplate.exchange(url + ":" + porta + SERVICE_DOWN_VOTE.replace("{id}", post.getId().toString()), HttpMethod.GET, null, PostVO.class);
        } catch (HttpClientErrorException ex) {
            throw new HttpException(ex.getStatusCode().toString());
        } catch (RestClientException cex) {
            throw new HttpException("Erro de conexão com os serviços WS");
        }

        tratarHttpCode(out);
    }

    public void tratarHttpCode(ResponseEntity responseEntity) throws HttpException {

        if (responseEntity.getStatusCodeValue() != 200) {
            ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
            throw new HttpException(errorDetails.toString());
        }

    }

    public PostVO getPost(Long id) {

        RestTemplate restTemplate = new RestTemplate();

        PostVO post = restTemplate.getForObject(url + ":" + porta + SERVICE_GET_POST.replace("{1}", id.toString()), PostVO.class);

        return post;
    }

    public List<PostVO> getPosts() throws HttpException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<PostVO>> out = null;
        try {
            out = restTemplate.exchange(url + ":" + porta + SERVICE_LIST_ALL, HttpMethod.GET, null, new ParameterizedTypeReference<List<PostVO>>() {
            });
        } catch (HttpClientErrorException ex) {
            throw new HttpException(ex.getStatusCode().toString());
        } catch (RestClientException cex) {
            throw new HttpException("Erro de conexão com os serviços WS");
        }

        tratarHttpCode(out);

        return out.getBody();
    }

}
