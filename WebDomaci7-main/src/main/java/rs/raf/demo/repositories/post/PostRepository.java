package rs.raf.demo.repositories.post;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.entities.Post;

import java.util.List;

public interface PostRepository {
    public Post addPost(Post post);
    public List<Post> allPosts();
    public Post findPost(Integer id);
    public Comment addComment(Comment comment, Integer id);

    void delete(Integer id);
}
