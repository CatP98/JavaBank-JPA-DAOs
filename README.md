# JavaBank - JPA and DAOs


## Overview

The Java Bank Application is a banking system implemented in Java. It provides functionalities for managing customers, accounts, transactions, and recipients. The application uses Spring Framework for dependency injection and transaction management, and JPA for data persistence.

## Languages
- **Java**
- **HTML**: Used for structuring the content of the web pages.
- **CSS**: Used for styling the web pages and ensuring a consistent look and feel.

## Frameworks and Libraries

- **Flexbox**: CSS layout module used for creating responsive web layouts.
- **Google Fonts**: Used for custom fonts (e.g., Roboto).

## Tools
- **Spring Framework**
- **Hibernate**
- **MySQL**
- **Apache HTTP Server**: Used for hosting the HTML pages.
- **VS Code**: A code editor used for editing HTML and CSS files.
- **Developer Tools**: Built-in browser tools for debugging and testing web pages.

## Features

- **Customer Management:** Add, update, retrieve, and list customers.
- **Account Management:** Open, close, and manage accounts associated with customers.
- **Transaction Management:** View account statements and manage transactions.
- **Recipient Management:** Retrieve and manage recipients associated with customers.

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven (for build and dependency management)
- A relational database (e.g., MySQL)

### Installation

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/yourusername/java-bank-app.git
    cd java-bank-app
    ```

2. **Configure the Database:**

    - Update the `application.properties` file with your database connection details.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/java_bank
    spring.datasource.username=root
    spring.datasource.password=password
    ```

3. **Build the Project:**

    ```bash
    mvn clean install
    ```

4. **Run the Application:**

    ```bash
    mvn spring-boot:run
    ```

## Exception Handling

- **CustomerNotFoundException** - Thrown when a customer is not found.
- **AccountNotFoundException** - Thrown when an account is not found.
- **TransactionInvalidException** - Thrown for invalid transactions.

## Contributing

If you'd like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or feedback, please reach out to [your-email@example.com](mailto:your-email@example.com).

