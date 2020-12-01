package com.segware.web.bo;

import com.segware.web.vo.PostVO;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
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

    public void createPost(PostVO post) {

    }

    public void upVote(PostVO post) {

    }

    public void downVote(PostVO post) {

    }

    public PostVO getPost(Long id) {

        RestTemplate restTemplate = new RestTemplate();

        PostVO post = restTemplate.getForObject("http://" + url + ":" + porta + SERVICE_GET_POST.replace("{1}", id.toString()), PostVO.class);

        return post;
    }

    public List<PostVO> getPosts() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<PostVO>> posts = restTemplate.exchange("http://" + url + ":" + porta + SERVICE_LIST_ALL, HttpMethod.GET, null, new ParameterizedTypeReference<List<PostVO>>() {
        });

        return null;
    }

}
