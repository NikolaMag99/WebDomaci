package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Post {
    private Integer blogId;

    @NotNull(message = "Author field is required")
    @NotEmpty(message = "Author field is required")
    private String author;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String title;

    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String content;

    private Date createdAt;

    private List<Comment> comments;

    public Post(){}
    public Post(Integer blogId, @NotNull(message = "Author field is required") @NotEmpty(message = "Author field is required") String author, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String title, @NotNull(message = "Content field is required") @NotEmpty(message = "Content field is required") String content, Date createdAt) {
        this.blogId = blogId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        comments = new CopyOnWriteArrayList<>();
    }

    public Post(@NotNull(message = "Author field is required") @NotEmpty(message = "Author field is required") String author, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String title, @NotNull(message = "Content field is required") @NotEmpty(message = "Content field is required") String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        comments = new CopyOnWriteArrayList<>();
    }

    public void setBlogId(int id) {
        this.blogId = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getBlogId() {
        return blogId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + blogId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", comments=" + comments +
                '}';
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
