
---

# 📝 Financial Control System

Project developed in **pure Java** + **JPA/Hibernate**, with database persistence and support for importing/exporting data in **CSV** using `FileReader` and `FileWriter`. This project is created for **educational purposes** to practice Java concepts, persistence, and file manipulation.

---

## 🚀 Objective

Create a simple **financial control system** via console, applying good programming practices and Java concepts:

* Layered architecture (**Model, DAO, Util**)
* Persistence with **JPA/Hibernate**
* **CSV** file manipulation
* Use of **Enums** for categorization
* Separation of responsibilities
* Practice basic CRUD operations

---

## 🛠 Technologies Used

* **Java 17+**
* **JPA**
* **Hibernate**
* **MySQL database**
* **FileReader / FileWriter**
* **Maven**

---

## 📂 Project Structure

```
src/
└── main/
    └── java/
        └── br/
            └── com/
                └── gabrielwederson/
                    └── financial/
                        ├── model/
                        │   ├── LaunchData.java
                        │   └── Type.java
                        ├── dao/
                        │   └──ReportDAO.java
                        │   └── LaunchDataDAO.java
                        ├── util/
                        │   └── JPAUtil.java
                        │   
                        └── Main.java
```

---

## 📌 Features

* ✅ Create financial entries
* ✅ List entries
* ✅ Update entries
* ✅ Filter by id
* ✅ Export entries to CSV

---

## 📄 Entity Structure

**LaunchData**:

| Field | Type    | Description       |
| ----- | ------- | ----------------- |
| id    | Integer | Unique identifier |
| name  | String  | Entry name        |
| value | double  | Entry amount      |
| type  | Enum    | INCOME or EXPENSE |

---

## 📥 CSV Import

**Expected format**:

```
name,value,type
Salary,5000,INCOME
Rent,1500,EXPENSE
```

---

## ▶ How to Run

1. Configure the database in `persistence.xml` (username, password, and MySQL URL).
2. Run the `Main.java` class via IDE or terminal.

---

## 👨‍💻 Author

**Gabriel Wederson**
Java Backend Developer

---
