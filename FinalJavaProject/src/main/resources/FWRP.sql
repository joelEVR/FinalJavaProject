DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE IF NOT EXISTS FWRP; 
USE FWRP; 
  
CREATE TABLE users (
  userID INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  email VARCHAR(30) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  userType varchar(120) NOT NULL,
  Location VARCHAR(255),
  notification BOOLEAN
);

INSERT INTO users (name, email, password, userType, Location) 
VALUES ('Juan Pérez', 'juan@example.com', 'password123', 'Regular', 'Ciudad de México');

INSERT INTO users (name, email, password, userType, Location) 
VALUES ('María García' , 'maria@example.com', 'securepass', 'Regular', 'Madrid, España');

CREATE TABLE food (
    foodid INT AUTO_INCREMENT PRIMARY KEY,
    foodname VARCHAR(255) NOT NULL,
    amount INT NOT NULL,
    expiration_date DATE NOT NULL,
    userid INT,
    status ENUM('AVAILABLE', 'CLAIMED', 'SOLD', 'SURPLUS') NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(4, 2) NOT NULL,
    foodLocation VARCHAR(255) NOT NULL,
    subscription BOOLEAN DEFAULT FALSE, 
    FOREIGN KEY (userid) REFERENCES users(userID) -- Changed to match the referenced column name
);

INSERT INTO food (foodname, amount, expiration_date, userid, status, price, discount, foodLocation, subscription) 
VALUES 
('Apples', 50, '2024-04-30', 1, 'AVAILABLE', 10.99, 0.00, 'Supermarket A', FALSE),
('Bananas', 100, '2024-05-10', 1, 'AVAILABLE', 5.99, 0.00, 'Farmers Market', FALSE),
('Oranges', 75, '2024-04-25', 1, 'CLAIMED', 8.49, 0.10, 'Local Store', FALSE),
('Carrots', 40, '2024-04-20', 1, 'SOLD', 6.79, 0.05, 'Grocery Store', FALSE),
('Tomatoes', 60, '2024-05-05', 2, 'AVAILABLE', 12.99, 0.00, 'Farmers Market', FALSE),
('Apples', 50, '2024-04-30', 2, 'SURPLUS', 10.99, 0.00, 'Supermarket A', FALSE),
('Bananas', 100, '2024-05-10', 2, 'CLAIMED', 5.99, 0.00, 'Farmers Market', FALSE);

CREATE TABLE FoodItemsForExchange (
    ItemId INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL, 
    Title VARCHAR(255) NOT NULL,
    Description TEXT,
    Quantity INT NOT NULL,
    PickupLocation VARCHAR(255) NOT NULL,
    ExpirationDate DATE NOT NULL,
    Status ENUM('available', 'reserved', 'exchanged') NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserId) 
);

INSERT INTO FoodItemsForExchange (UserId, Title, Description, Quantity, PickupLocation, ExpirationDate, Status)
VALUES 
(1, 'Whole Wheat Bread', 'A batch of fresh whole wheat bread', 10, '123 Fake Street, City', '2024-05-30', 'available'),
(1, 'Apples', 'Box of red apples', 5, '742 Evergreen Terrace, City', '2024-06-15', 'available'),
(1, 'Carrots', 'Large bag of carrots', 20, '123 Fake Street, City', '2024-05-25', 'available'),
(1, 'Tomatoes', 'Box of cherry tomatoes', 15, '456 New Street, City', '2024-06-01', 'available'),
(1, 'Romaine Lettuce', 'Organic romaine lettuce heads', 8, '742 Evergreen Terrace, City', '2024-05-20', 'available'),
(1, 'Garlic Bread', 'Homemade garlic bread', 10, '123 Fake Street, City', '2024-06-10', 'available'),
(2, 'Bananas', 'Bunch of ripe bananas', 6, '789 Central Avenue, City', '2024-05-18', 'available'),
(2, 'Chicken Breasts', 'Frozen chicken breasts', 5, '456 New Street, City', '2024-06-20', 'available'),
(2, 'Quinoa', 'Bags of quinoa', 12, '789 Central Avenue, City', '2024-06-05', 'available'),
(2, 'Brown Rice', 'Bags of brown rice', 7, '123 Fake Street, City', '2024-05-28', 'available');