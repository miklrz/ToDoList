# To-Do list App

I made this project with Spring Boot, PostgreSQL, Spring Data JPA, Spring Security, Thymeleaf.

It consists of Rest Api and user's intrface.

----

## API Operations

The following is a list of supported operations:
- Retrieve all authors:`GET /api/authors`
- Retrieve author:`GET /api/authors/{authorId}`
- Save author:`POST /api/authors`
- Update author:`PUT /api/authors/{authorId}`
- Delete author:`DELETE /api/authors/{authorId}`
- Get all author's tasks:`GET /api/authors/{authorId}/tasks`
- Get author's task:`GET /api/authors/{authorId}/tasks/{taskId}`
- Save author's task:`POST /api/authors/{authorId}/tasks`
- Edit author's task:`PUT /api/authors/{authorId}/tasks/{taskId}`
- Delete author's task:`DELETE /api/authors/{authorId}/tasks/{taskId}`
