# ExamServer
A fairly simple backend springboot application, developed to learn the basics and intermediate level of web api development via springboot. It is part of a fullstack application which provides two types of users, ability to add categories, quizzes and questions.

## Technologies
* Java 17
* SpringBoot 3.1.1
* Spring starter security
* PostgreSQL
* JSON web token
* Lombok

## Launch
Import project in Spring Tool Suite (Eclipse)
Right click on project -> Run as -> Spring boot App

## Features
### Basic
Signup as both - ADMIN & NORMAL User
Login as both - ADMIN & NORMAL User

### Login Auth
All APIs post login require jwt token authentication implemented through Spring security v3.1.1.
JWT Token is generated on login, used as header in all subsequent APIs as > Authorization : Bearer *token*

### Category
#### Entity
* cid
* title
* description
* <b>quiz[ ]</b>

#### Controllers
Provides the following operations for category Entity:
* add
* get all categories
* get one category by id
* update category
* delete category

### Quiz
#### Entity
* qid
* title
* description
* maxMarks
* numberOfQuestions
* active
* <b>category</b>
* <b>question[ ]</b>

#### Controllers
Provides the following operations for Quiz Entity:
* add
* get All
* get All by Category
* get one by id
* update
* delete by id

### Question
#### Entity
* questionId
* content
* option1
* option2
* option3
* option4
* answer
* choice
* <b>quiz</b>

#### Controllers
Provides the following operations for Quiz Entity:
* add
* add list of questions
* get All
* get All by quiz
* get All by quiz for User (encrypted answers)
* get one by id
* update
* delete by id
* evaluate answers
