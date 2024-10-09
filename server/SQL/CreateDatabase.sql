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

-- Create table for WordLadder
CREATE TABLE WordLadder
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    FirstWord VARCHAR(MAX) NOT NULL,
    LastWord VARCHAR(MAX) NOT NULL,
    LetterCount INT NOT NULL
)
GO

-- Create table for Jumble
CREATE TABLE Jumble
(
    Id INT IDENTITY(1,1) PRIMARY KEY,
    FinalWord VARCHAR(MAX) NOT NULL
)
    GO

-- Create table for Exercise
-- NOTE: Records from this table are deleted when games are deleted.
CREATE TABLE GameConfig
(
    [Id] INT IDENTITY(1,1) PRIMARY KEY,
    [Definition] VARCHAR(MAX),
    GameId INT NOT NULL,
    FOREIGN KEY (GameId) REFERENCES WordLadder(Id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (GameId) REFERENCES Jumble(Id) ON DELETE CASCADE ON UPDATE CASCADE
)
GO

-- Inserting Data Fields
-- Difficulty Level: Easy
INSERT INTO WordLadder(FirstWord, LastWord, LetterCount)
Values('Love', 'Dish', 4);
GO

INSERT INTO WordLadder(FirstWord, LastWord, LetterCount)
Values('Hide', 'Bend', 4);
GO

INSERT INTO WordLadder(FirstWord, LastWord, LetterCount)
Values('Seed', 'Glow', 4);
GO

-- Difficulty Level: Medium
INSERT INTO WordLadder(FirstWord, LastWord, LetterCount)
Values('Brook', 'Gleam', 5);
GO

INSERT INTO WordLadder(FirstWord, LastWord, LetterCount)
Values('Focus', 'Lurid', 5);
GO

INSERT INTO WordLadder(FirstWord, LastWord, LetterCount)
Values('Plane', 'Truce', 5);
GO

-- Difficulty Level: Hard
INSERT INTO WordLadder(FirstWord, LastWord, LetterCount)
Values('Stones', 'Shores', 6);
GO

INSERT INTO WordLadder(FirstWord, LastWord, LetterCount)
Values('Cuddle', 'Caddle', 6);
GO

INSERT INTO WordLadder(FirstWord, LastWord, LetterCount)
Values('Planet', 'Brazer', 6);