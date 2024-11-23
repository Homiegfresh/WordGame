-- This script will insert data into the Jumble game table
-- along with the corresponding data for the GameConfig table

-- Game Config holds all correct lines for user input without
-- storing the final word of each game

-- Jumbles Game 1, Easy
INSERT INTO GameConfig ([Definition])
VALUES ('[
    {
        "Word": "stOres",
        "Description": "Place where you buy groceries.",
        "SignificantChars": "001000"
    },
    {
        "Word": "cudDle",
        "Description": "To sleep close to another person.",
        "SignificantChars": "000100"
    },
    {
        "Word": "Slay",
        "Description": "The knight is off to ____ the dragon.",
        "SignificantChars": "1000"
    },
    {
        "Word": "bEnd",
        "Description": "The strongman was able to easily ____ the metal pole.",
        "SignificantChars": "0100"
    }
]')

INSERT INTO Jumble([FinalWord], Difficulty, GameId)
Values( '{"Word": "Dose", "Description": "A quantity of medicine to be taken at a time."}', 1, @@IDENTITY);

-- Jumbles Game 2, Easy
INSERT INTO GameConfig ([Definition])
VALUES ('[
    {
        "Word": "blne",
        "Description": "An abbreviation or shortened form of the word blind.",
        "SignificantChars": "0000"
    },
    {
        "Word": "sHorts",
        "Description": "Clothing item worn in warm weather.",
        "SignificantChars": "010000"
    },
    {
        "Word": "paddlE",
        "Description": "Used to propel a canoe or kayak.",
        "SignificantChars": "000001"
    },
    {
        "Word": "Dosh",
        "Description": "Slang for money or cash.",
        "SignificantChars": "1000"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Hide", "Description": "Conceal from sight."}', 1, @@IDENTITY);

-- Jumbles Game 3, Easy
INSERT INTO GameConfig ([Definition])
VALUES ('[
    {
        "Word": "Lucis",
        "Description": "A name or a variation of Lucas.",
        "SignificantChars": "10000"
    },
    {
        "Word": "Apple",
        "Description": "A popular fruit or a tech company.",
        "SignificantChars": "10000"
    },
    {
        "Word": "gaLlop",
        "Description": "How a horse runs at full speed.",
        "SignificantChars": "001000"
    },
    {
        "Word": "samPle",
        "Description": "A small part or piece taken as a representation of the whole.",
        "SignificantChars": "000100"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Play", "Description": "Engage in activity for enjoyment."}', 1, @@IDENTITY);

-- Jumbles Game 4, Easy
INSERT INTO GameConfig
VALUES ('[
    {
        "Word": "hEar",
        "Description": "To perceive with the ear the sound made by someone or something.",
        "SignificantChars": "0100"
    },
    {
        "Word": "hoWI",
        "Description": "An uncommon or poetic expression for how.",
        "SignificantChars": "0011"
    },
    {
        "Word": "greAt",
        "Description": "Something that is better than good.",
        "SignificantChars": "00010"
    },
    {
        "Word": "boRed",
        "Description": "Feeling weary and uninterested.",
        "SignificantChars": "00100"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Ware", "Description": "Goods for sale."}', 1, @@IDENTITY);

------------------------------------------------------------

-- Jumbles Game 1, Medium
INSERT INTO GameConfig
VALUES ('[
    {
        "Word": "fOcus",
        "Description": "To concentrate attention or effort.",
        "SignificantChars": "01000"
    },
    {
        "Word": "Matter",
        "Description": "Physical substance or importance.",
        "SignificantChars": "100000"
    },
    {
        "Word": "scOres",
        "Description": "The result or outcome of a game or test.",
        "SignificantChars": "001000"
    },
    {
        "Word": "Goat",
        "Description": "A farm animal known for climbing and its horns.",
        "SignificantChars": "1000"
    },
    {
        "Word": "bLoom",
        "Description": "A flower in the stage of flowering.",
        "SignificantChars": "01000"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Gloom", "Description": "Partial or total darkness."}', 2, @@IDENTITY);

-- Jumbles Game 2, Medium
INSERT INTO GameConfig
VALUES ('[
    {
        "Word": "lUrid",
        "Description": "Very vivid, especially in a shocking way.",
        "SignificantChars": "01000"
    },
    {
        "Word": "cudDle",
        "Description": "To sleep close to another person.",
        "SignificantChars": "000100"
    },
    {
        "Word": "Dove",
        "Description": "A type of bird symbolizing peace.",
        "SignificantChars": "1000"
    },
    {
        "Word": "Clap",
        "Description": "To strike hands together to show appreciation.",
        "SignificantChars": "1000"
    },
    {
        "Word": "dInner",
        "Description": "The main meal of the day, usually in the evening.",
        "SignificantChars": "010000"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Lucid", "Description": "Clear and easy to understand."}', 2, @@IDENTITY);

-- Jumbles Game 3, Medium
INSERT INTO GameConfig
VALUES ('[
    {
        "Word": "Hectic",
        "Description": "Full of activity, very busy.",
        "SignificantChars": "100000"
    },
    {
        "Word": "Dark",
        "Description": "Having little or no light.",
        "SignificantChars": "1000"
    },
    {
        "Word": "snacK",
        "Description": "A small amount of food eaten between meals.",
        "SignificantChars": "00001"
    },
    {
        "Word": "hArd",
        "Description": "Not soft; firm and solid.",
        "SignificantChars": "0100"
    },
    {
        "Word": "Sample",
        "Description": "A small part or piece taken as a representation of the whole.",
        "SignificantChars": "100000"
    }
]
')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Shark", "Description": "A large, predatory fish."}', 2, @@IDENTITY);

-- Jumbles Game 4, Medium
INSERT INTO GameConfig
VALUES ('[
    {
        "Word": "jeweL",
        "Description": "A precious stone or gem.",
        "SignificantChars": "00001"
    },
    {
        "Word": "cuMber",
        "Description": "To burden or hinder.",
        "SignificantChars": "001000"
    },
    {
        "Word": "riGht",
        "Description": "Correct or morally good.",
        "SignificantChars": "00100"
    },
    {
        "Word": "liFe",
        "Description": "The condition of being alive.",
        "SignificantChars": "0010"
    },
    {
        "Word": "Amber",
        "Description": "A yellowish-brown fossil resin.",
        "SignificantChars": "10000"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Gleam", "Description": "A faint or brief light."}', 2, @@IDENTITY);

------------------------------------------------------------

-- Jumbles Game 1, Hard
INSERT INTO GameConfig
VALUES ('[
    {
        "Word": "Saddle",
        "Description": "A seat fastened on the back of a horse.",
        "SignificantChars": "100000"
    },
    {
        "Word": "sportS",
        "Description": "Physical activities or games played competitively.",
        "SignificantChars": "000001"
    },
    {
        "Word": "chOres",
        "Description": "Routine household tasks.",
        "SignificantChars": "001000"
    },
    {
        "Word": "Horse",
        "Description": "A large domesticated animal used for riding.",
        "SignificantChars": "10000"
    },
    {
        "Word": "bEar",
        "Description": "A large mammal known for its strength.",
        "SignificantChars": "0100"
    },
    {
        "Word": "Roast",
        "Description": "To cook food, usually meat, in an oven.",
        "SignificantChars": "10000"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Shores", "Description": "Land along the edge of a sea, lake, or ocean."}', 3, @@IDENTITY);

-- Jumbles Game 2, Hard
INSERT INTO GameConfig
VALUES ('[
    {
        "Word": "shUn",
        "Description": "To avoid deliberately.",
        "SignificantChars": "0010"
    },
    {
        "Word": "dotE",
        "Description": "To be extremely fond of someone.",
        "SignificantChars": "0001"
    },
    {
        "Word": "Dollar",
        "Description": "The currency of the United States.",
        "SignificantChars": "100000"
    },
    {
        "Word": "Fable",
        "Description": "A short story, typically with animals, conveying a moral.",
        "SignificantChars": "10000"
    },
    {
        "Word": "Donate",
        "Description": "To give money or goods to charity.",
        "SignificantChars": "100000"
    },
    {
        "Word": "Luck",
        "Description": "Success or failure apparently brought by chance.",
        "SignificantChars": "1000"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Fuddle", "Description": "Confuse or make muddled."}', 3, @@IDENTITY);
GO

-- Jumbles Game 3, Hard
INSERT INTO GameConfig
VALUES ('[
    {
        "Word": "Igloo",
        "Description": "A dome-shaped Eskimo house.",
        "SignificantChars": "10000"
    },
    {
        "Word": "hiNder",
        "Description": "To obstruct or delay progress.",
        "SignificantChars": "001000"
    },
    {
        "Word": "waGer",
        "Description": "To bet or stake on the outcome of an event.",
        "SignificantChars": "00100"
    },
    {
        "Word": "Ethic",
        "Description": "A set of moral principles.",
        "SignificantChars": "10000"
    },
    {
        "Word": "dooR",
        "Description": "A hinged barrier used to close an opening.",
        "SignificantChars": "0001"
    },
    {
        "Word": "loaFer",
        "Description": "A type of slip-on shoe.",
        "SignificantChars": "000100"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Finger", "Description": "One of the five digits of the hand."}', 3, @@IDENTITY);
GO

-- Jumbles Game 4, Hard
INSERT INTO GameConfig
VALUES ('[
    {
        "Word": "dArt",
        "Description": "A small pointed missile used in a game.",
        "SignificantChars": "0100"
    },
    {
        "Word": "Loner",
        "Description": "A person who prefers to be alone.",
        "SignificantChars": "10000"
    },
    {
        "Word": "oCtal",
        "Description": "A base-8 number system.",
        "SignificantChars": "01000"
    },
    {
        "Word": "officE",
        "Description": "A room where business or professional work is done.",
        "SignificantChars": "000001"
    },
    {
        "Word": "Dunk",
        "Description": "To dip something into a liquid.",
        "SignificantChars": "1000"
    },
    {
        "Word": "cuDdle",
        "Description": "To sleep close to another person.",
        "SignificantChars": "001000"
    }
]')

INSERT INTO Jumble(FinalWord, Difficulty, GameId)
VALUES('{"Word": "Caddle", "Description": "Act in a silly or unproductive manner."}', 3, @@IDENTITY);
GO