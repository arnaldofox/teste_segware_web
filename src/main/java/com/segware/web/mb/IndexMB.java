package com.segware.web.mb;

import com.segware.web.bo.IndexBO;
import com.segware.web.vo.PostVO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Arnaldo
 */
@ManagedBean
@ViewScoped
public class IndexMB implements Serializable {

    private static final long serialVersionUID = -998923916058375288L;

    @Autowired
    private IndexBO indexBO;

    private List<PostVO> posts;

    private PostVO post;

    @PostConstruct
    public void init() {

    }

    public void createPost() {

    }

    public void upVote() {

    }

    public void downVote() {

    }

    public IndexBO getIndexBO() {
        return indexBO;
    }

    public void setIndexBO(IndexBO indexBO) {
        this.indexBO = indexBO;
    }

    public List<PostVO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostVO> posts) {
        this.posts = posts;
    }

    public PostVO getPost() {
        return post;
    }

    public void setPost(PostVO post) {
        this.post = post;
    }

}
