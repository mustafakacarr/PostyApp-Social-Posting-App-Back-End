

# PostyApp Social Posting App

Hi! PostyApp is an essential social media application. I aim to develop it as containing ordinary features for now but I can add new features in time. Below things used;

**Back End**

 - **Spring Boot**
 - Swagger (and also you can see API Endpoint Schema via [this link](https://postyapp-04194dc6a480.herokuapp.com/swagger-ui/index.html#))
 - Spring Security for auth
 - JWT for auth
 
**Front End**

For going to the  front end repository, [click here](https://github.com/mustafakacarr/PostyApp-Social-Posting-App-Front-End).
 - **ReactJS**
 - Redux

  It will store user datas with token in localStorage. 
 
**DevOps**
 - Heroku (For BE deployment)
 - Vercel (For FE deployment)
 - Docker
 - Kubernetes

 And thats all for now. 
 
# Features
- Login and Registration,
- Add, Edit and Delete Post,
- Add, Edit and Delete Comment,
- Add, Edit and Delete Like
- Change avatar and See last activities about you.
Maybe more in time... :)
  

# **API Schema**

## Users
| **HTTP Method** | **Endpoint** | **Description** |
|-----------------|--------------|-----------------|
| GET             | `/users`     | Lists all users. |
| POST            | `/users`     | Creates a new user. |
| GET             | `/users/{userId}` | Retrieves information about a specific user. |
| PUT             | `/users/{userId}` | Updates information about a specific user. |
| DELETE          | `/users/{userId}` | Deletes a specific user. |


## Posts
| **HTTP Method** | **Endpoint** | **Description** |
|-----------------|--------------|-----------------|
| GET             | `/posts`     | Lists all posts. |
| POST            | `/posts`     | Creates a new post. |
| GET             | `/posts?userId={userId}` | Lists posts by a specific user. |
| GET             | `/posts/{postId}` | Retrieves details of a specific post. |
| PUT             | `/posts/{postId}` | Updates a specific post. |
| DELETE          | `/posts/{postId}` | Deletes a specific post. |





## Comments
| **HTTP Method** | **Endpoint** | **Description** |
|-----------------|--------------|-----------------|
| POST            | `/comments`  | Creates a new comment. |
| GET             | `/comments`  | Lists all comments. |
| GET             | `/comments?postId={postId}` | Lists comments for a specific post. |
| GET             | `/comments?userId={userId}` | Lists comments made by a specific user. |
| GET             | `/comments?postId={postId}&userId={userId}` | Lists comments for a specific post and user. |
| GET             | `/comments/{commentId}` | Retrieves details of a specific comment. |
| PUT             | `/comments/{commentId}` | Updates a specific comment. |
| DELETE          | `/comments/{commentId}` | Deletes a specific comment. |

## Likes

| **HTTP Method** | **Endpoint** | **Description** |
|-----------------|--------------|-----------------|
| GET             | `/likes`     | Lists all likes. |
| POST            | `/likes`     | Likes a post. |
| GET             | `/likes?postId={postId}` | Lists likes for a specific post. |
| GET             | `/likes?user={userId}` | Lists likes made by a specific user. |
| GET             | `/likes/{likeId}` | Retrieves details of a specific like. |
| DELETE          | `/likes/{likeId}` | Removes a specific like. |


**Author**

> [`Mustafa Kaçar`](https://mustafakacar.com.tr)
