-- Create Tours table
CREATE TABLE Tours (
                       TourID INTEGER PRIMARY KEY AUTOINCREMENT,
                       Name TEXT NOT NULL,
                       Description TEXT,
                       Location TEXT NOT NULL,
                       Duration INTEGER NOT NULL,
                       MaxParticipants INTEGER NOT NULL,
                       Date DATE NOT NULL,
                       AvailableSeats INTEGER NOT NULL,
                       Price REAL NOT NULL
);

-- Create Customers table adjustment
CREATE TABLE Customers (
    CustomerID INTEGER PRIMARY KEY AUTOINCREMENT,
    Name TEXT NOT NULL,
    Email TEXT NOT NULL UNIQUE
);


-- Create Reservations table
CREATE TABLE Reservations (
                              ReservationID INTEGER PRIMARY KEY AUTOINCREMENT,
                              CustomerID INTEGER,
                              TourID INTEGER,
                              NumberOfParticipants INTEGER NOT NULL,
                              DateBooked DATE NOT NULL,
                              FOREIGN KEY(CustomerID) REFERENCES Customers(CustomerID),
                              FOREIGN KEY(TourID) REFERENCES Tours(TourID)
);

-- Insert example customers adjustment
INSERT INTO Customers (Name, Email) VALUES
    ('John Doe', 'john.doe@example.com'),
    ('Jane Smith', 'jane.smith@example.com');

-- Insert example customers
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                                     ('Historical City Tour', 'A tour around the historical landmarks of the city.', 'Reykjavík', 2, 20, '2024-04-15', 20, 50.00),
                                                                                                                     ('River Cruise', 'Enjoy a serene cruise down the river with guided commentary.', 'Akureyri', 3, 15, '2024-05-20', 15, 75.00),
                                                                                                                     ('Mountain Hike', 'A challenging hike up the local mountain range.', 'Egilsstaðir', 8, 10, '2024-06-10', 10, 100.00);

-- Insert example reservations
-- John Doe books the Historical City Tour with 3 participants.
INSERT INTO Reservations (CustomerID, TourID, NumberOfParticipants, DateBooked) VALUES
    (1, 1, 3, '2024-03-01');

-- Jane Smith books the River Cruise with 2 participants, making it fully booked.
INSERT INTO Reservations (CustomerID, TourID, NumberOfParticipants, DateBooked) VALUES
    (2, 2, 15, '2024-03-02');
