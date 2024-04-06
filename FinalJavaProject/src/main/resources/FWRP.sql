DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE IF NOT EXISTS FWRP; 
USE FWRP; 
  
  CREATE TABLE users (
  UserID INT AUTO_INCREMENT PRIMARY KEY,
  name varchar(120) NOT NULL,
  username varchar(30) NOT NULL,
  email varchar(30) NOT NULL,
  password varchar(255) NOT NULL,
  userType varchar(120) NOT NULL,
  Location VARCHAR(255)
); 

  
CREATE TABLE FoodItems ( 
    FoodItemID INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(255), 
    Quantity INT, 
    ExpirationDate DATE, 
    RetailersID INT, 
    Status ENUM('Available', 'Claimed', 'Purchased') NOT NULL, 
    Price DECIMAL(10, 2), 
    DiscountRate DECIMAL(3, 2), 
    FOREIGN KEY (RetailersID) REFERENCES users(UserID) 
); 
  
CREATE TABLE SurplusFoodItems ( 
    SurplusFoodItemID INT PRIMARY KEY, 
    ListingDate DATE, 
    FOREIGN KEY (SurplusFoodItemID) REFERENCES FoodItems(FoodItemID) 
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
    FOREIGN KEY (FoodItemID) REFERENCES FoodItems(FoodItemID) 
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
