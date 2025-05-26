# Change Management Backend Service

This is a Spring Boot backend application, implements auditing, role management and email notification as a part of the MC4003 (Task 8)

## 📦 Project Metadata
- **Group**: `com.policy.function`
- **Artifact**: `changemanagement`
- **Name**: `changemanagement`
- **Package**: `com.policy.function.changemanagement`
- **Java Version**: 24
- **Packaging**: Jar

## 🛠 Features
- Change request submission and tracking
- Role-based access (Submitter and Approver)
- Approval/rejection workflow
- Status updates and audit logs
- Optional: file attachment, notification system

## 🚀 Getting Started

### Prerequisites
- Java 24
- Maven or Gradle
- Spring Boot CLI (optional)

### Running the Application
```bash
./mvnw spring-boot:run
```
or
```bash
./gradlew bootRun
```

### API Endpoints (sample)
- `POST /api/changes` - Submit a new change request
- `GET /api/changes` - View all change requests
- `PUT /api/changes/{id}/approve` - Approve a change
- `PUT /api/changes/{id}/reject` - Reject a change

## 🧾 Folder Structure
```
src/main/java/com/policy/function/changemgmt
├── controller
├── dto
├── model
├── repository
├── service
├── config
```

## 📄 License
This project is licensed under the MIT License.