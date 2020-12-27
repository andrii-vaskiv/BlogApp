# BlogApp

App that lets you create user account and post articles.

## REST API

    GET /users
    GET /users/{userId}
    POST /users/
    PUT /users/{userId}
    DELETE /users/{userId}
    GET /users/{userId}/articles
    GET /users/all/articles
    POST /users/{userId}/articles
    PUT /users/{userId}/articles/{articleId}
    DELETE /users/{userId}/articles/{articleId}
    
### Additional query parameters

    GET /users?minAge=25
    GET /users?withArticleColor=GREEN
    GET /users/name?minArticleCount=2