DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE IF NOT EXISTS FWRP; 
USE FWRP; 
  
CREATE TABLE users (
  userID INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  username VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  password VARCHAR(255) NOT NULL,
  userType varchar(120) NOT NULL,
  Location VARCHAR(255)
);

INSERT INTO users (name, username, email, password, userType, Location) 
VALUES ('Juan Pérez', 'juanperez', 'juan@example.com', 'password123', 'Regular', 'Ciudad de México');

INSERT INTO users (name, username, email, password, userType, Location) 
VALUES ('María García', 'mariagarcia', 'maria@example.com', 'securepass', 'Regular', 'Madrid, España');

CREATE TABLE food_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    expiration_date DATE NOT NULL,
    retailer_id INT,
    status ENUM('AVAILABLE', 'CLAIMED', 'SOLD', 'SURPLUS') NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    average_daily_sales INT NOT NULL,
    FOREIGN KEY (retailer_id) REFERENCES users(UserID)
); 

CREATE TABLE SurplusFoodItems ( 
    SurplusFoodItemID INT PRIMARY KEY, 
    ListingDate DATE, 
    FOREIGN KEY (SurplusFoodItemID) REFERENCES food_Items(id) 
); 
  
CREATE TABLE Claims ( 
    ClaimID INT AUTO_INCREMENT PRIMARY KEY, 
    CharityID INT, 
    SurplusFoodItemID INT, 
    ClaimDate DATE, 
    FOREIGN KEY (CharityID) REFERENCES users(UserID), 
    FOREIGN KEY (SurplusFoodItemID) REFERENCES SurplusFoodItems(SurplusFoodItemID) 
); 
  
CREATE TABLE Purchases ( 
    PurchaseID INT AUTO_INCREMENT PRIMARY KEY, 
    ConsumerID INT, 
    FoodItemID INT, 
    PurchaseDate DATE, 
    PurchasePrice DECIMAL(10, 2), 
    FOREIGN KEY (ConsumerID) REFERENCES users(UserId), 
    FOREIGN KEY (FoodItemID) REFERENCES food_Items(id) 
); 
  
CREATE TABLE Subscriptions ( 
    SubscriptionID INT AUTO_INCREMENT PRIMARY KEY, 
    UserID INT, 
    Location VARCHAR(255), 
    Preferences TEXT, 
    CommunicationMethod ENUM('Email', 'Phone'), 
    FOREIGN KEY (UserID) REFERENCES Users(UserId) 
); 
  
CREATE TABLE Notifications ( 
    NotificationID INT AUTO_INCREMENT PRIMARY KEY, 
    SubscriptionID INT, 
    Message TEXT, 
    NotificationDate DATE, 
    FOREIGN KEY (SubscriptionID) REFERENCES Subscriptions(SubscriptionID) 
); 

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



