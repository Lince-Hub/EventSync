## Abstract

EventSync is an application built using Spring Boot for the backend and Vaadin for the front-end.
It allows users to create events, submit feedback, and view integrated AI sentiment analysis with 
three possible results: positive, neutral, or negative.

## Prerequisites
- Java 17+
- Maven
- (Optional) An IDE such as Intellij IDEA or Eclipse

## IMPORTANT – GOOGLE CLOUD CREDENTIALS SETUP

Before launching the project, make sure to place **service-account.json** inside:

> EventSync/app/src/main/resources

**Submitted to:** valdas.trakumas@ibm.com  
**Submitted by:** linas.puplauskas@gmail.com

After adding the file, open your terminal and execute:

> setx GOOGLE_APPLICATION_CREDENTIALS "FULL_PATH"

Replace FULL_PATH with the absolute path to your service-account.json file.

Example:

setx GOOGLE_APPLICATION_CREDENTIALS "C:\Users\YourName\EventSync\app\src\main\resources\service-account.json"

After running the command, restart IntelliJ or your terminal.

## Features
- Built-in controller
- AI analysis of feedback (Google Natural Language AI)
- Storage of events and feedback in H2 Database
- UI/UX

## Postman
Postman testing is available — simply import the EventSync.postman_collection.json file into Postman to access all API requests.

Path: EventSync/postman/EventSync.postman_collection.json

## Project Structure

```
src/main/java/lt/linas_puplauskas/event_sync/
|
├── configuration/   # DataInitializer
├── constants/       # Constants
├── controller/      # EventController
├── domain/          # Event, Feedback, FeedbackRequest, Rating
├── repository/      # EventRepository
├── services/        # EventService, FeedbackService, AIsentiment
├── ui/
│   ├── component/   # Vaadin UI components
│   ├── views/       # Vaadin views: EventForm, EventsView, FeedbackForm, SummaryView
│   └── MainLayout   # Main UI layout             
```
