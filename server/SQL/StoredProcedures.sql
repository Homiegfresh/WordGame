-- Stored Procedure for selecting all from WordLadder
CREATE PROCEDURE sp_WordLadderData
AS BEGIN
	SELECT *
	FROM WordLadder
END
GO

-- Stored Procedure for selecting all from Jumble
CREATE PROCEDURE sp_JumbleData
AS BEGIN
	SELECT *
	FROM Jumble
END
GO

-- Stored Procedure for selecting all from GameConfig
CREATE PROCEDURE sp_GameConfigData
AS BEGIN
	SELECT *
	FROM GameConfig
END
GO

-- Stored Procedure for grabbing a random word ladder game configuration
CREATE PROCEDURE sp_GetRandomWordLadderGame
	@Difficulty INT
AS BEGIN
    SELECT TOP 1
        wl.Id,
        wl.FirstWord,
        wl.LastWord,
        JSON_QUERY(gc.[Definition]) AS Definition
    FROM WordLadder wl
    JOIN GameConfig gc ON gc.Id = wl.GameId
    WHERE Difficulty = @Difficulty
    ORDER BY NEWID()
    FOR JSON PATH, WITHOUT_ARRAY_WRAPPER
END
GO

-- Stored Procedure for grabbing a random jumble game configuration
CREATE PROCEDURE sp_GetRandomJumbleGame
	@Difficulty INT
AS BEGIN
    SELECT TOP 1
        j.Id,
        JSON_QUERY(j.[FinalWord]) [FinalWord],
        JSON_QUERY(gc.[Definition]) [Definition]
    FROM Jumble j
             JOIN GameConfig gc ON gc.Id = j.GameId
    WHERE Difficulty = @Difficulty
    ORDER BY NEWID()
    FOR JSON PATH, WITHOUT_ARRAY_WRAPPER
END
GO

-- Stored Procedure for grabbing a game configuration by its Id
CREATE PROCEDURE sp_GetGameConfigById(@Id INT)
AS BEGIN
	SELECT [Definition]
	FROM GameConfig
	WHERE Id = @Id
END
GO

-- Stored Procedure to join WordLadder table and GameConfig table
-- to display a list of all games
CREATE PROCEDURE sp_GetWLGameList
AS BEGIN
	SELECT *
	FROM WordLadder wl
	JOIN GameConfig gc ON gc.Id = wl.GameId
END
GO

-- Stored Procedure to join Jumble table and GameConfig table
-- to display a list of all games
CREATE PROCEDURE sp_GetJumbleGameList
AS BEGIN
	SELECT *
	FROM Jumble j
	JOIN GameConfig gc ON gc.Id = j.GameId
END
GO

CREATE PROC sp_WordLadderValidateInput
(
    @GameId INT,
    @Word VARCHAR(MAX)
)
AS
BEGIN
    -- User somehow inputted a word with length 0 or NULL
    IF @Word IS NULL OR LEN(@Word) = 0
    BEGIN
        SELECT 0
        RETURN
    END

    DECLARE @wordCount INT
    SET @wordCount = (
        SELECT COUNT (*)
        FROM WordLadder wl
        JOIN GameConfig gc ON gc.Id = wl.GameId
        CROSS APPLY OPENJSON(gc.[Definition])
        WITH
        (
          Word VARCHAR(MAX) '$.Word'
        ) w
        WHERE wl.Id = @GameId AND w.Word = @Word
    )

    IF @wordCount > 0
    BEGIN
        SELECT 1 RETURN
    END
    ELSE SELECT 0
END
GO

/*
-- Example Execution Commands for Stored Procedures
EXEC sp_WordLadderData
EXEC sp_JumbleData
EXEC sp_GameConfigData
EXEC sp_GetRandomWordLadderGame @Difficulty = 3
EXEC sp_GetRandomJumbleGame @Difficulty = 2
EXEC sp_GetGameConfigById @Id = 4
EXEC sp_GetWLGameList
EXEC sp_GetJumbleGameList
EXEC sp_GetGameConfigById @Id = 20

-- Drop Stored Procedures
DROP PROC sp_WordLadderData
DROP PROC sp_JumbleData
DROP PROC sp_GameConfigData
DROP PROC sp_GetRandomWordLadderGame
DROP PROC sp_GetRandomJumbleGame
DROP PROC sp_GetGameConfigById
DROP PROC sp_GetWLGameList
DROP PROC sp_GetJumbleGameList
*/
