<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<%=application.getContextPath()%>/">Web programiranje</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<%=application.getContextPath()%>/">Posts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=application.getContextPath()%>/new-post.jsp">New Post</a>
            </li>
        </ul>

    </div>
</nav>
