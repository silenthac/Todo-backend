# 📝 Todo App – Spring Boot + JWT Authentication

This is a secure Todo Application built with **Spring Boot** where each user can register, log in, and manage their personal list of todos. The backend uses **Spring Security** and **JWT** (JSON Web Token) to handle authentication and authorization.

---

## 🔧 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA (Hibernate)
- MySQL (or H2 for in-memory testing)
- Maven
- Postman (for testing API)

---

## 📂 Project Structure

src/
└── main/
├── java/
│ └── com/example/todo/
│ ├── controller/
│ ├── model/
│ ├── repository/
│ ├── security/
│ ├── service/
│ └── TodoApplication.java
└── resources/
├── application.properties
└── data.sql (optional)



---

## 🔐 Features

- ✅ User Registration
- ✅ User Login with JWT Token
- ✅ JWT Authentication and Authorization
- ✅ Create, Read, Update, Delete Todos
- ✅ Users can only access their own todos
- ✅ Passwords hashed with BCrypt

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/todo-app.git
cd todo-app


. Configure Application Properties
Update src/main/resources/application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_jwt_secret_key
jwt.expiration=3600000

3. Run the Application
bash
Copy
Edit
mvn clean install
mvn spring-boot:run


📮 API Endpoints
🔐 Authentication
Method	Endpoint	Description
POST	/auth/register	Register new user
POST	/auth/login	Login, returns JWT token

📝 Todo (requires Authorization: Bearer <token>)
Method	Endpoint	Description
GET	/todos	Get all user's todos
POST	/todos	Create a new todo
PUT	/todos/{id}	Update a todo
DELETE	/todos/{id}	Delete a todo


The Problem That Sparked It
"Like most developers, I manage a ton of tasks — whether it's DSA practice, reading tech blogs, or side projects. I was using a simple notes app, but it wasn’t secure, didn’t support multiple users, and I thought — why not build a full-featured Todo app myself?"

"Initially, I built a basic CRUD app where anyone could create todos. But then I asked myself — what if two users logged in and saw each other’s tasks? That’s when I decided to take it seriously and add proper authentication using JWT and Spring Security."

🛠️ How I Built It
"I used Spring Boot for the backend and built a RESTful API. The project follows a clean architecture — with layers for controllers, services, repositories, and security."

"For data persistence, I used Spring Data JPA with Hibernate and connected it to a MySQL database (though it also works with H2 for local testing)."

🔐 The Security Story
"The fun part was learning and implementing JWT (JSON Web Token) based authentication. When a user logs in, a JWT token is generated and sent back to the client. This token must be sent in every subsequent request. It looks like a random string, but inside it contains the user's identity — and it’s signed, so no one can tamper with it."

"I built a custom JWT filter that intercepts every request, checks the token, and if it's valid, it loads the user’s details. This ensures that users can only access their own todos — even if they try to hit the endpoints manually using Postman."

🧪 Real-Life Simulation with Postman
"To test the app, I used Postman. I created a flow where I first register a new user, then log in, copy the token, and add it in the headers to create or get todos. Without the token, none of the endpoints work — just like in real-world secure apps."

🎯 Key Features
✅ User Registration & Login

✅ JWT token generation & validation

✅ Password encryption with BCrypt

✅ Role-based access control (optional extension)

✅ Full CRUD on Todos

✅ Each user has isolated data access

📚 What I Learned
"Through this project, I gained a deep understanding of how JWT works, how Spring Security filters the request pipeline, and how to structure a secure REST API. More than that, it taught me how to think about real-world problems like session management, token expiration, and user data isolation."

💡 What’s Next?
"I’m currently working on adding a React frontend so users can manage their todos with a nice UI. I also plan to deploy the app using Docker and possibly host it on Railway or Render."

✅ Wrap-Up Line
"In short, this project wasn’t just about building an app — it was about solving a real problem securely and scalably. It taught me to think like a backend engineer — not just writing code, but building systems that are safe, fast, and user-focused."
