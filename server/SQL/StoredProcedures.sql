-- Stored Procedure for selecting all from WordLadder
CREATE PROCEDURE sp_WordLadderData
AS BEGIN
	SELECT *
	FROM WordLadder
END

-- Stored Procedure for selecting all from WordLadder
CREATE PROCEDURE sp_GameConfigData
AS BEGIN
	SELECT *
	FROM GameConfig
END

-- Stored Procedure for grabbing a random game configuration
CREATE PROCEDURE sp_GetRandomWordLadderGame
	@LetterCount INT -- letter count represents difficulty level
AS BEGIN
	DECLARE @RandomWordLadderId INT
	SELECT TOP 1 @RandomWordLadderId = Id
	FROM WordLadder
	WHERE LetterCount = @LetterCount
	ORDER BY NEWID()
	SELECT *
	FROM WordLadder
	WHERE Id = @RandomWordLadderId
END
GO

-- Stored Procedure for grabbing a game configuration by its Id
CREATE PROCEDURE sp_GetGameConfigById(@Id INT)
AS BEGIN
	SELECT [Definition]
	FROM GameConfig
	WHERE Id = @Id
END

-- Stored Procedure to join WordLadder table and GameConfig table
-- to display a list of all games
CREATE PROCEDURE sp_GetGameList
AS BEGIN
	SELECT *
	FROM WordLadder wl
	JOIN GameConfig gc ON gc.Id = wl.GameId
END

-- Example Execution Commands for Stored Procedures
EXEC sp_WordLadderData
EXEC sp_GameConfigData
EXEC sp_GetRandomWordLadderGame @LetterCount = 5
EXEC sp_GetGameConfigById @Id = 4
EXEC sp_GetGameList

-- Drop Stored Procedures
DROP PROC sp_WordLadderData
DROP PROC sp_GameConfigData
DROP PROC sp_GetRandomWordLadderGame
DROP PROC sp_GetGameConfigById
DROP PROC sp_GetGameList