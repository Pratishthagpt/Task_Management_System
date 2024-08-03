# Task Management System

## Instructions to Run the Application Locally

Follow these steps to get the application up and running on your local machine:

#### 1. Clone the Repository

Clone the repository to your local machine using Git:

```
git clone <https://github.com/Pratishthagpt/Task_Management_System.git>
```
#### 2. Set Up the Environment
Java: Ensure you have Java 17 or later installed.  
Docker: Make sure Docker is installed if you plan to use Docker for running the application.

#### 3. Build the Project
   Build the project using Maven.

```
./mvnw clean install
```
#### 4. Run the Application
   Using Maven:
```
./mvnw spring-boot:run
```
#### 5. Using Docker
   Build the Docker Image:
```
docker build -t task-management-system .
   ```
   Run the Docker Container:
```  
docker run -p 8080:8080 task-management-system
``` 

## API documentation (Postman collection)

### 1. User Authentication 

* **Register User** 
    #### - Request details

    Method: `POST`  
    URL: `/api/auth/register`

    #### - Request Body
    Json format - 
    ```
    {
      "username": "string",
      "password": "string"
    }  
    ```
  #### -  Response codes

  `201 CREATED`  
    * Body:  
      ```
      {
      "username": "string",
      "userId": "string",
      "roles": ["string"]
      }
      ```

* **Login User**
  #### - Request details

  Method: `POST`  
  URL: `/api/auth/login`

  #### - Request Body
  Json format -
    ```
    {
      "username": "string",
      "password": "string"
    }  
    ```
  #### -  Response codes

  `200 CREATED`
  * Body:
    ```
    {
    "username": "string",
    "userId": "string",
    "roles": ["string"]
    }
    ```  
  `400 Bad Request`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```  

* **Logout User**
  #### - Request details

  Method: `POST`  
  URL: `/api/auth/logout`

  #### - Request Body
  Json format -
    ```
    {
      "userId": "string",
      "token": "string"
    }  
    ```
  #### -  Response codes

  `200 CREATED`
  * Body:
    ```
    {
    "message": "User has successfully logged out."
    }
    ```  
  `404 Bad Request`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```

### 2. Task Management

* **Add new Task**
  #### - Request details

  Method: `POST`  
  URL: `/api/tasks`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.

  #### - Request Body
  Json format -
    ```
    {
      "title" : "task4",
      "description" : "This is task 4.",
      "status" : "CREATED",
      "dueDate" : "2024-09-03",
      "priority" : "LOW"
  }
    ```
  #### -  Response codes

  `201 CREATED`
  * Body:
    ```
    {
      "id": "7ecabe94-112d-44c7-83bc-a465374ddf76",
      "title": "task4",
      "description": "This is task 4.",
      "status": "Todo",
      "dueDate": "2024-09-03T00:00:00.000+00:00",
      "createdAt": "2024-08-03T10:15:09.042+00:00",
      "updatedAt": "2024-08-03T10:15:09.042+00:00",
      "userId": "0087c984-b8f1-43b0-95df-19183dd3b2e5",
      "priority": "LOW"
    }
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```

* **Get All Tasks**
  #### - Request details

  Method: `GET`  
  URL: `/api/tasks`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.

  #### -  Response codes

  `200 OK`
  * Body:
    ```
    [
      {
        "id": "7ecabe94-112d-44c7-83bc-a465374ddf76",
        "title": "task4",
        "description": "This is task 4.",
        "status": "Todo",
        "dueDate": "2024-09-03T00:00:00.000+00:00",
        "createdAt": "2024-08-03T10:15:09.042+00:00",
        "updatedAt": "2024-08-03T10:15:09.042+00:00",
        "userId": "0087c984-b8f1-43b0-95df-19183dd3b2e5",
        "priority": "LOW"
      }
    ]
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
  
* **Get Task By Id**
  #### - Request details

  Method: `GET`  
  URL: `/api/tasks/{id}`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.

  #### - URL Parameters

  `id` (`String`) **required**: ID for task.
  #### -  Response codes

  `200 OK`
  * Body:
    ```
    
      {
        "id": "7ecabe94-112d-44c7-83bc-a465374ddf76",
        "title": "task4",
        "description": "This is task 4.",
        "status": "Todo",
        "dueDate": "2024-09-03T00:00:00.000+00:00",
        "createdAt": "2024-08-03T10:15:09.042+00:00",
        "updatedAt": "2024-08-03T10:15:09.042+00:00",
        "userId": "0087c984-b8f1-43b0-95df-19183dd3b2e5",
        "priority": "LOW"
      }
    
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }

* **Get All Tasks By User**
  #### - Request details

  Method: `GET`  
  URL: `/api/tasks/user`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.

  #### -  Response codes

  `200 OK`
  * Body:
    ```
    [
      {
        "id": "7ecabe94-112d-44c7-83bc-a465374ddf76",
        "title": "task4",
        "description": "This is task 4.",
        "status": "Todo",
        "dueDate": "2024-09-03T00:00:00.000+00:00",
        "createdAt": "2024-08-03T10:15:09.042+00:00",
        "updatedAt": "2024-08-03T10:15:09.042+00:00",
        "userId": "0087c984-b8f1-43b0-95df-19183dd3b2e5",
        "priority": "LOW"
      }
    ]
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```  
  `404 NOT FOUND`  
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }

* **Update Task By Id**
  #### - Request details

  Method: `Put`  
  URL: `/api/tasks/{id}`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.

  #### - URL Parameters

  `id` (`String`) **required**: ID for task.

  #### - Request Body
  Json format -
    ```
    {
      "title" : "task4",
      "description" : "This is task 4.",
      "status" : "CREATED",
      "dueDate" : "2024-09-03",
      "priority" : "LOW"
  }
    ```

  #### -  Response codes

  `200 OK`
  * Body:
    ```
    
      {
        "id": "7ecabe94-112d-44c7-83bc-a465374ddf76",
        "title": "task4",
        "description": "This is task 4.",
        "status": "Todo",
        "dueDate": "2024-09-03T00:00:00.000+00:00",
        "createdAt": "2024-08-03T10:15:09.042+00:00",
        "updatedAt": "2024-08-03T10:15:09.042+00:00",
        "userId": "0087c984-b8f1-43b0-95df-19183dd3b2e5",
        "priority": "LOW"
      }
    
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```  
  `404 NOT FOUND`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
  
* **Delete Task By Id**
  #### - Request details

  Method: `Delete`  
  URL: `/api/tasks/{id}`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.

  #### - URL Parameters

  `id` (`String`) **required**: ID for task.

  #### -  Response codes

  `200 OK`
  * Body:
    ```
      {
    "message": "string",
    "status": "string"
      }
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```  
  `404 NOT FOUND`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    } 
* **Get All Tasks By Status**
  #### - Request details

  Method: `GET`  
  URL: `/api/tasks/status/{status}`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.
  #### - URL Parameters

  `status` (`String`) **required**: status of task.

  #### -  Response codes

  `200 OK`
  * Body:
    ```
    [
      {
        "id": "7ecabe94-112d-44c7-83bc-a465374ddf76",
        "title": "task4",
        "description": "This is task 4.",
        "status": "Todo",
        "dueDate": "2024-09-03T00:00:00.000+00:00",
        "createdAt": "2024-08-03T10:15:09.042+00:00",
        "updatedAt": "2024-08-03T10:15:09.042+00:00",
        "userId": "0087c984-b8f1-43b0-95df-19183dd3b2e5",
        "priority": "LOW"
      }
    ]
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```  
  `404 NOT FOUND`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }

* **Get All Tasks By Priority**
  #### - Request details

  Method: `GET`  
  URL: `/api/tasks/priority/{priority}`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.
  #### - URL Parameters

  `priority` (`String`) **required**: priority of task.

  #### -  Response codes

  `200 OK`
  * Body:
    ```
    [
      {
        "id": "7ecabe94-112d-44c7-83bc-a465374ddf76",
        "title": "task4",
        "description": "This is task 4.",
        "status": "Todo",
        "dueDate": "2024-09-03T00:00:00.000+00:00",
        "createdAt": "2024-08-03T10:15:09.042+00:00",
        "updatedAt": "2024-08-03T10:15:09.042+00:00",
        "userId": "0087c984-b8f1-43b0-95df-19183dd3b2e5",
        "priority": "LOW"
      }
    ]
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```  
  `404 NOT FOUND`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }

* **Get All Tasks By Title**
  #### - Request details

  Method: `GET`  
  URL: `/api/tasks/title/{title}`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.
  #### - URL Parameters

  `title` (`String`) **required**: title of task.

  #### -  Response codes

  `200 OK`
  * Body:
    ```
    [
      {
        "id": "7ecabe94-112d-44c7-83bc-a465374ddf76",
        "title": "task4",
        "description": "This is task 4.",
        "status": "Todo",
        "dueDate": "2024-09-03T00:00:00.000+00:00",
        "createdAt": "2024-08-03T10:15:09.042+00:00",
        "updatedAt": "2024-08-03T10:15:09.042+00:00",
        "userId": "0087c984-b8f1-43b0-95df-19183dd3b2e5",
        "priority": "LOW"
      }
    ]
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```  
  `404 NOT FOUND`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }

* **Get All Tasks By DueDate**
  #### - Request details

  Method: `GET`  
  URL: `/api/tasks/due_date`

  #### - Headers

  `Authorization` (`String`) **required**: Access token.

  #### - Request Body
  Json format -
    ```
    {
    "dueDate": "2024-08-03"
  }
    ```
  #### -  Response codes

  `200 OK`
  * Body:
    ```
    [
      {
        "id": "7ecabe94-112d-44c7-83bc-a465374ddf76",
        "title": "task4",
        "description": "This is task 4.",
        "status": "Todo",
        "dueDate": "2024-09-03T00:00:00.000+00:00",
        "createdAt": "2024-08-03T10:15:09.042+00:00",
        "updatedAt": "2024-08-03T10:15:09.042+00:00",
        "userId": "0087c984-b8f1-43b0-95df-19183dd3b2e5",
        "priority": "LOW"
      }
    ]
    ```  
  `401 UNAUTHORIZED`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
    ```  
  `404 NOT FOUND`
  * Body:
    ```
    {
    "message": "string",
    "status": "string"
    }
