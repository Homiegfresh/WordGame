Use Master --set master database to the current database in use
GO

--Check to see if database exists, if it does drop it
IF EXISTS(SELECT Name FROM sys.databases WHERE Name = 'WordGames')
DROP DATABASE WordGames
GO

--Creating new database
CREATE DATABASE WordGames
GO

-- Setting the new database as the current database
USE WordGames
GO

-- Create table for Exercise
-- NOTE: Records from this table are deleted when games are deleted.
CREATE TABLE GameConfig
(
    [Id] INT IDENTITY(1,1) PRIMARY KEY,
    [Definition] VARCHAR(MAX)
)
GO

-- Create table for WordLadder
CREATE TABLE WordLadder
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    FirstWord VARCHAR(MAX) NOT NULL,
    LastWord VARCHAR(MAX) NOT NULL,
    Difficulty INT NOT NULL,
	GameId INT NOT NULL,
	FOREIGN KEY (GameId) REFERENCES GameConfig(Id)
)
GO

-- Create table for Jumble
CREATE TABLE Jumble
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    FinalWord VARCHAR(MAX) NOT NULL,
    Difficulty INT NOT NULL,
	GameId INT NOT NULL,
	FOREIGN KEY (GameId) REFERENCES GameConfig(Id)
)
GO
