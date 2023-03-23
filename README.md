# todo-list

This is a fully CRUD RESTful API that allows users to manage their user account and tasks

- utilizes progreSQL as the database to store user and tasks information

## Local Development Setup

## Schema

### User

```java

```

### Task

```java

```

## Endpoints

by default base URL is `http://localhost:8080`

### User

#### Get all users (GET)

`/user`

- Returns a list of users

example return:

```json
{
  "userList": [
    {
      "id": 1,
      "name": "Bob",
      "email": "bob@example.com"
    },
    {
      "id": 2,
      "name": "Bill",
      "email": "bill@example.com"
    },
    {
      "id": 3,
      "name": "Mickey",
      "email": "bob@example.com"
    }
  ]
}
```

#### Get user by id (GET)

`/user/{user_id}`

- Returns all data associated with that user

example return:

```json
{
  "id": 4,
  "name": "Mickey Mouse",
  "email": "mickey@example.com"
}
```

#### Create new user (POST)

`/user`

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
  "email": "bruce@example.com"
}
```

#### Delete user (DELETE)

`/user/{user_id}`

example return:

```json
{
  "id": 4
}
```

### Task

#### Get all users (GET)

`/user`

- Returns a list of users

example return:

```json
{
  "userList": [
    {
      "id": 1,
      "name": "Bob",
      "email": "bob@example.com"
    },
    {
      "id": 2,
      "name": "Bill",
      "email": "bill@example.com"
    },
    {
      "id": 3,
      "name": "Mickey",
      "email": "bob@example.com"
    }
  ]
}
```

#### Get user by id (GET)

`/user/{user_id}`

- Returns all data associated with that user

example return:

```json
{
  "id": 4,
  "name": "Mickey Mouse",
  "email": "mickey@example.com"
}
```

#### Create new user (POST)

`/user`

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
  "email": "bruce@example.com"
}
```

#### Delete task (DELETE)

`/user/{task_id}`

example return:

```json
{
  "id": 4
}
```
