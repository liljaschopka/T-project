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

-- Insert example Tours
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                                     ('Historical City Tour', 'A tour around the historical landmarks of the city.', 'Reykjavík', 2, 20, '2024-04-15', 20, 50.00),
                                                                                                                     ('River Cruise', 'Enjoy a serene cruise down the river with guided commentary.', 'Akureyri', 3, 15, '2024-05-20', 15, 75.00),
                                                                                                                     ('Mountain Hike', 'A challenging hike up the local mountain range.', 'Egilsstaðir', 8, 10, '2024-06-10', 10, 100.00);

-- More tours
-- Reykjavík Tours
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                            ('Reykjavík Art Walk', 'Discover the vibrant art scene of Reykjavík through its street art and galleries.', 'Reykjavík', 3, 15, '2024-07-05', 15, 55.00),
                                                                                                            ('Reykjavík Food Tasting', 'Savor the flavors of Iceland with a guided food tasting tour in Reykjavík.', 'Reykjavík', 4, 12, '2024-08-12', 12, 85.00),
                                                                                                            ('Reykjavík Nightlife Tour', 'Experience the famous nightlife of Reykjavík with an expert guide.', 'Reykjavík', 5, 18, '2024-09-10', 18, 90.00);

-- Akureyri Tours
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                            ('Akureyri Botanical Garden', 'Visit the northernmost botanical garden in the world, located in Akureyri.', 'Akureyri', 2, 20, '2024-05-15', 20, 40.00),
                                                                                                            ('Akureyri Whale Watching', 'Embark on a whale watching adventure in the fjords near Akureyri.', 'Akureyri', 4, 10, '2024-06-20', 10, 120.00),
                                                                                                            ('Akureyri Cultural Walk', 'Explore Akureyris culture and history with a guided walking tour.', 'Akureyri', 3, 15, '2024-07-22', 15, 60.00);

-- Egilsstaðir Tours
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                            ('East Fjords Exploration', 'Discover the stunning landscapes of the East Fjords, starting from Egilsstaðir.', 'Egilsstaðir', 6, 8, '2024-06-15', 8, 130.00),
                                                                                                            ('Lagarfljót Lake Monster Hunt', 'Explore the mystery of the Lagarfljót worm with a boat tour on the lake.', 'Egilsstaðir', 3, 20, '2024-07-18', 20, 70.00),
                                                                                                            ('Egilsstaðir Heritage Tour', 'Learn about the history and heritage of Egilsstaðir and its surroundings.', 'Egilsstaðir', 4, 15, '2024-08-25', 15, 65.00);

-- Ísafjörður Tours
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                            ('Westfjords Panorama', 'Enjoy breathtaking views of the Westfjords from Ísafjörður.', 'Ísafjörður', 5, 10, '2024-06-05', 10, 110.00),
                                                                                                            ('Ísafjörður Kayaking Adventure', 'Kayak around the beautiful fjords and bays near Ísafjörður.', 'Ísafjörður', 7, 8, '2024-07-10', 8, 150.00),
                                                                                                            ('Ísafjörður Historical Walk', 'Take a guided walk through the historical town of Ísafjörður.', 'Ísafjörður', 3, 20, '2024-08-15', 20, 55.00);


-- Even more tours
-- More tours
-- Reykjavík Tours
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                            ('Reykjavík Museums Tour', 'Explore the rich history and culture of Reykjavík by visiting its top museums.', 'Reykjavík', 4, 20, '2024-04-10', 20, 60.00),
                                                                                                            ('Reykjavík Coastal Walk', 'Enjoy a guided walk along the scenic coast of Reykjavík, featuring iconic landmarks.', 'Reykjavík', 2, 25, '2024-05-20', 25, 30.00),
                                                                                                            ('Golden Circle Express', 'Experience the famous Golden Circle, with stops at Gullfoss, Geysir, and Þingvellir National Park.', 'Reykjavík', 8, 30, '2024-06-15', 30, 100.00);

-- Akureyri Tours
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                            ('Akureyri Northern Lights', 'Chase the Northern Lights with an expert guide in the areas around Akureyri.', 'Akureyri', 3, 15, '2024-04-05', 15, 95.00),
                                                                                                            ('Akureyri Hiking Tour', 'Explore the natural beauty surrounding Akureyri on a guided hiking tour.', 'Akureyri', 5, 12, '2024-05-10', 12, 80.00),
                                                                                                            ('Akureyri Horse Riding', 'Experience the unique Icelandic horse with a riding tour through the Akureyri countryside.', 'Akureyri', 4, 10, '2024-06-25', 10, 110.00);

-- Egilsstaðir Tours
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                            ('Egilsstaðir Waterfall Hike', 'Hike to some of the most beautiful waterfalls near Egilsstaðir.', 'Egilsstaðir', 6, 10, '2024-04-20', 10, 90.00),
                                                                                                            ('Egilsstaðir Local Farms Tour', 'Visit local farms to learn about rural life and traditional farming in Iceland.', 'Egilsstaðir', 4, 15, '2024-05-25', 15, 70.00),
                                                                                                            ('Egilsstaðir Bird Watching', 'Discover the diverse birdlife of the region with a guided bird watching tour.', 'Egilsstaðir', 3, 20, '2024-06-10', 20, 50.00);

-- Ísafjörður Tours
INSERT INTO Tours (Name, Description, Location, Duration, MaxParticipants, Date, AvailableSeats, Price) VALUES
                                                                                                            ('Ísafjörður Fishing Experience', 'Join a fishing trip and experience the rich marine life of the Westfjords.', 'Ísafjörður', 4, 12, '2024-04-15', 12, 130.00),
                                                                                                            ('Ísafjörður Glacier Tour', 'Explore the majestic glaciers near Ísafjörður with an expert guide.', 'Ísafjörður', 7, 8, '2024-05-05', 8, 160.00),
                                                                                                            ('Ísafjörður Viking History', 'Dive into the Viking history of Ísafjörður and visit historical sites.', 'Ísafjörður', 3, 20, '2024-06-20', 20, 80.00);



-- Insert example reservations
-- John Doe books the Historical City Tour with 3 participants
INSERT INTO Reservations (CustomerID, TourID, NumberOfParticipants, DateBooked) VALUES
    (1, 1, 3, '2024-03-01');

-- Jane Smith books the River Cruise with 2 participants
INSERT INTO Reservations (CustomerID, TourID, NumberOfParticipants, DateBooked) VALUES
    (2, 2, 15, '2024-03-02');
