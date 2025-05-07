# TaskSync API - Task Management System  

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)  
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)  
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)  
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)  

A **Spring Boot** REST API for managing tasks with **subtasks, attachments, and categories**, secured with **JWT, OAuth2, and email verification**.  

## ✨ Features  
✅ **Task** - Create, read, update, and delete tasks  
✅ **Subtask Management** – Break down tasks into manageable subtasks <br>
📎 **File Attachments** – Add documents to tasks or subtasks  
🗂 **Custom Categories** – Group tasks by user-defined labels  
🔐 **Secure Auth** – JWT, Refresh Tokens, OAuth2 (Google, etc.)  
✉️ **Email Verification & Password Reset**  

## 🛠 Tech Stack  
- **Backend**: Spring Boot, Spring Security, Spring Data JPA  
- **Database**: MySQL  
- **Auth**: JWT, OAuth2  
- **Tools**: Lombok, Maven  

## 🗃 Database Schema
Here is the visual representation of the database schema:
![Database Schema](DATABASE_SCHEMEA)

## 🧪 Endpoints
Interactive API documentation powered by Swagger:  
![Swagger UI](ENDPOINTS)

## 🚀 Quick Start  
1. Clone & configure `application.properties`  
2. Run:  
```bash 
mvn clean install  
mvn spring-boot:run  
