package com.segware.web.mb;

import com.segware.web.bo.IndexBO;
import com.segware.web.mb.exception.BusinessException;
import com.segware.web.mb.exception.HttpException;
import com.segware.web.utils.FacesMessageUtil;
import com.segware.web.vo.PostVO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Controller da view
 *
 * @author Arnaldo
 */
@Controller("indexMB")
@Scope("view")
public class IndexMB implements Serializable {

    private static final long serialVersionUID = -998923916058375288L;

    @Autowired
    private IndexBO indexBO;

    private List<PostVO> posts;

    private PostVO post;

    @PostConstruct
    public void init() {
        post = new PostVO();
    }

    public void createPost() {
        try {
            indexBO.createPost(post);
            posts = indexBO.getPosts();
            post = new PostVO();
            FacesMessageUtil.info("Postagem publicada com sucesso!");
        } catch (HttpException | BusinessException ex) {
            FacesMessageUtil.error(ex.getMessage());
        }
    }

    public void upVotes(PostVO postVO) {
        try {
            indexBO.upVote(postVO);
            posts = indexBO.getPosts();
        } catch (HttpException ex) {
            FacesMessageUtil.error(ex.getMessage());
        }
    }

    public void downVotes(PostVO postVO) {
        try {
            indexBO.downVote(postVO);
            posts = indexBO.getPosts();
        } catch (HttpException ex) {
            FacesMessageUtil.error(ex.getMessage());
        }
    }

    public void listAllPost() {
        try {
            posts = indexBO.getPosts();
        } catch (HttpException ex) {
            FacesMessageUtil.error(ex.getMessage());
        }
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
