# todo-list

This is a fully CRUD RESTful API that allows users to manage their user account and tasks

- utilizes progreSQL as the database to store user and tasks information

## Local Development Setup

### Prerequisites

- ## Docker
  - [Install Docker](https://docs.docker.com/get-docker/)
- ## JDK 8+
  - [Install JDK](https://docs.oracle.com/en/java/javase/15/install/installation-jdk-macos.html)
- ## mvn
  - run `brew install mvn`
- PgAdmin4 (Optional)
  - [Install PgAdmin](https://www.pgadmin.org/download/)

### To Run application

```bash
./spin_up_postgres.sh
./mvnw spring-boot:run
```

### Connect with PgAdmin4 (Optional)

- right click on Servers on left side.
- Register -> Server
- Name: todo_list
- click on Connection tab
- Host name/address: localhost
- port: 5455
- Username: postgrewuser
- Password: postgrespw

## Schema

### User

```java
private Long id; // Auto generated
private String name;
private String email;
@OneToMany
private List<Task> tasks = new ArrayList<>();
private Timestamp createdAt; // Auto generated
private Timestamp updatedAt; // Auto generated
```

### Task

```java
private Long id; // Auto generated
private String title;

@ManyToOne
private User user;
private OffsetDateTime dueDate;

private Timestamp createdAt; // Auto generated on creation
private Timestamp updatedAt; // Auto generated on update
```

## Endpoints

by default base URL is `http://localhost:8080`

### User

#### Get all users (GET)

`/user`

- Returns a list of users and all their associated data.

example return:

```json
[
  {
    "id": 1,
    "name": "Bob",
    "email": "bob@example.com",
    "tasks": [
      {
        "id": 1,
        "title": "Take out trash",
        "dueDate": "2023-04-10T19:00:00-05:00",
        "createdAt": "2023-03-23T19:25:55.170+00:00",
        "updatedAt": "2023-03-23T19:25:55.170+00:00"
      }
    ],
    "createdAt": "2023-03-23T19:29:05.770+00:00",
    "updatedAt": "2023-03-23T19:29:05.770+00:00"
  },
  {
    "id": 2,
    "name": "Bill",
    "email": "bill@example.com",
    "tasks": [],
    "createdAt": "2023-03-23T19:29:05.770+00:00",
    "updatedAt": "2023-03-23T19:29:05.770+00:00"
  },
  {
    "id": 3,
    "name": "Mickey",
    "email": "mickey@example.com",
    "tasks": [],
    "createdAt": "2023-03-23T19:29:05.770+00:00",
    "updatedAt": "2023-03-23T19:29:05.770+00:00"
  }
]
```

#### Get user by id (GET)

`/user/{user_id}`

- Returns all data associated with that user

example return:

```json
{
  "id": 4,
  "name": "Mickey Mouse",
  "email": "mickey@example.com",
  "tasks": [
    {
      "id": 1,
      "title": "Take out trash",
      "dueDate": "2023-04-10T19:00:00-05:00",
      "createdAt": "2023-03-23T19:25:55.170+00:00",
      "updatedAt": "2023-03-23T19:25:55.170+00:00"
    }
  ],
  "createdAt": "2023-03-23T19:29:05.770+00:00",
  "updatedAt": "2023-03-23T19:29:05.770+00:00"
}
```

#### Create new user (POST)

`/user`

- Returns: the created user

example Body:

```json
{
  "name": "Mickey Mouse",
  "email": "mickey@example.com"
}
```

example return:

```json
{
  "id": 4,
  "name": "Bruce",
  "email": "bruce@example.com",
  "tasks": [],
  "createdAt": "2023-03-23T19:29:05.770+00:00",
  "updatedAt": "2023-03-23T19:29:05.770+00:00"
}
```

#### Edit user (POST)

`/user/{id}`

- Returns: the edited user object

example Body:

```json
{
  "name": "Mickey Mouse",
  "email": "mickey@example.com"
}
```

example return:

```json
{
  "id": 4,
  "name": "Mickey Mouse",
  "email": "mickey@example.com",
  "tasks": [],
  "createdAt": "2023-03-23T19:29:05.770+00:00",
  "updatedAt": "2023-03-23T19:29:05.770+00:00"
}
```

#### Add Task for User (POST)

`/user/{user_id}/task/add`

- Returns: User task got added to.

example Body:

```json
{
  "title": "Take out trash",
  "dueDate": "2023-04-10T19:00:00-05:00"
}
```

example Return:

```json
{
  "id": 1,
  "name": "Bob",
  "email": "bob@example.com",
  "tasks": [
    {
      "id": 1,
      "title": "Take out trash",
      "dueDate": "2023-04-10T19:00:00-05:00",
      "createdAt": "2023-03-23T19:25:55.170+00:00",
      "updatedAt": "2023-03-23T19:25:55.170+00:00"
    }
  ],
  "createdAt": "2023-03-23T19:29:05.770+00:00",
  "updatedAt": "2023-03-23T19:29:05.770+00:00"
}
```

#### Delete user (DELETE)

`/user/{user_id}`

example return:

```
User with id 4 successfully deleted
```

### Task

#### Get all tasks (GET)

`/task`

- Returns a list of tasks and all their associated data.

example return:

```json
[
  {
    "id": 1,
    "title": "Take out trash",
    "dueDate": "2023-04-10T19:00:00-05:00",
    "createdAt": "2023-03-23T19:41:46.675+00:00",
    "updatedAt": "2023-03-23T19:41:46.675+00:00"
  },
  {
    "id": 2,
    "title": "Read 2 hours",
    "dueDate": "2023-05-10T19:00:00-05:00",
    "createdAt": "2023-03-23T19:41:46.678+00:00",
    "updatedAt": "2023-03-23T19:41:46.678+00:00"
  },
  {
    "id": 3,
    "title": "Complete homework 1-5",
    "dueDate": "2023-04-01T19:00:00-05:00",
    "createdAt": "2023-03-23T19:41:46.679+00:00",
    "updatedAt": "2023-03-23T19:41:46.679+00:00"
  },
  {
    "id": 4,
    "title": "Do dishes",
    "dueDate": "2023-06-17T19:00:00-05:00",
    "createdAt": "2023-03-23T19:41:46.681+00:00",
    "updatedAt": "2023-03-23T19:41:46.681+00:00"
  }
]
```

#### Get task by id (GET)

`/task/{task_id}`

- Returns all data associated with the task

example return:

```json
{
  "id": 4,
  "title": "Do dishes",
  "dueDate": "2023-06-17T19:00:00-05:00",
  "createdAt": "2023-03-23T19:41:46.681+00:00",
  "updatedAt": "2023-03-23T19:41:46.681+00:00"
}
```

#### Edit task (POST)

`/task/{id}`

- Returns: the edited task object

example Body:

```json
{
  "title": "Pick up groceries",
  "dueDate": "2023-03-23T00:00:00+00:00"
}
```

example return:

```json
{
  "id": 2,
  "title": "Pick up groceries",
  "dueDate": "2023-03-23T00:00:00Z",
  "createdAt": "2023-03-23T19:41:46.678+00:00",
  "updatedAt": "2023-03-23T19:42:42.950+00:00"
}
```

#### Delete task (DELETE)

`/task/{id}`

example return:

```
Task with id 4 successfully deleted
```
