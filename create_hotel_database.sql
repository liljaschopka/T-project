CREATE TABLE Hotel(
    HotelName VARCHAR(255),
    Location VARCHAR(255),
    Address VARCHAR(255),
    Picture VARCHAR(255),
    Description VARCHAR(255),
    PRIMARY KEY (HotelName, Address)
);

CREATE TABLE Room (
    HotelName VARCHAR(255),
    HotelAddress VARCHAR(255),
    Number INT NOT NULL,
    NrPerson INT NOT NULL,
    Floor INT NOT NULL,
    Price_Isk INT NOT NULL,
    PictureUrl VARCHAR(255),
    FOREIGN KEY (HotelName, HotelAddress)
    REFERENCES Hotel(HotelName, Address)
    PRIMARY KEY (HotelName, HotelAddress, Number)
);

CREATE TABLE Booking (
    BookingID INTEGER PRIMARY KEY AUTOINCREMENT,
    Arrival DATE NOT NULL,
    Departure DATE NOT NULL,
    nrPerson INT NOT NULL,
    OwnerName VARCHAR(255) NOT NULL,
    OwnerEmail VARCHAR(255) NOT NULL,
    FOREIGN KEY (OwnerName, OwnerEmail)
    REFERENCES User(Name, Email)
);

CREATE TABLE Booking_Room (
    BookingID INT NOT NULL,
    HotelName VARCHAR(255) NOT NULL,
    HotelAddress VARCHAR(255) NOT NULL,
    RoomNumber INT NOT NULL,
    PRIMARY KEY(BookingID, HotelName, RoomNumber)
    FOREIGN KEY(HotelName, HotelAddress, RoomNumber)
    REFERENCES Room(HotelName, HotelAddress, Number)
    FOREIGN KEY(BookingID)
    REFERENCES Booking(BookingID)
);

CREATE TABLE User (
    Name VARCHAR(255),
    Email VARCHAR(255),
    PRIMARY KEY(Name, Email)
);


--Akureyri
INSERT INTO Hotel VALUES(
    'AK-HOTEL',
    'Akureyri',
    'Borgarbraut 3',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );

INSERT INTO Room VALUES(
    'AK-HOTEL',
    'Borgarbraut 3',
    1,
    2,
    1,
    6000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'AK-HOTEL',
    'Borgarbraut 3',
    2,
    3,
    1,
    8000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'AK-HOTEL',
    'Borgarbraut 3',
    3,
    3,
    1,
    8500,
    'vantar/upplýsingar/um/url'
);


INSERT INTO Hotel VALUES(
    'Klett-Hotel',
    'Akureyri',
    'Klettaborg 28',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );

INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Klettaborg 28',
    1,
    3,
    1,
    9500,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Klettaborg 28',
    2,
    3,
    2,
    8500,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Klettaborg 28',
    3,
    2,
    2,
    7500,
    'vantar/upplýsingar/um/url'
);


INSERT INTO Hotel VALUES(
    'Gull Borgir',
    'Akureyri',
    'Norðurslóð 17',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );
INSERT INTO Room VALUES(
    'Gull Borgir',
    'Norðurslóð 17',
    1,
    2,
    2,
    7500,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Gull Borgir',
    'Norðurslóð 17',
    2,
    2,
    2,
    7500,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Gull Borgir',
    'Norðurslóð 17',
    3,
    1,
    3,
    5000,
    'vantar/upplýsingar/um/url'
);

--Reykjavík
INSERT INTO Hotel VALUES(
    'Klett-Hotel',
    'Reykjavík',
    'Laugavegur 103',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );
INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Laugavegur 103',
    1,
    2,
    5,
    8000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Laugavegur 103',
    2,
    2,
    5,
    8000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Laugavegur 103',
    3,
    2,
    6,
    8000,
    'vantar/upplýsingar/um/url'
);

INSERT INTO Hotel VALUES(
    'Royal Hotel',
    'Reykjavík',
    'Síðumúli 33',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );
INSERT INTO Room VALUES(
    'Royal Hotel',
    'Síðumúli 33',
    1,
    2,
    3,
    10000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Royal Hotel',
    'Síðumúli 33',
    2,
    2,
    3,
    10000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Royal Hotel',
    'Síðumúli 33',
    3,
    2,
    3,
    10000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Royal Hotel',
    'Síðumúli 33',
    4,
    2,
    2,
    11000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Royal Hotel',
    'Síðumúli 33',
    5,
    2,
    2,
    11000,
    'vantar/upplýsingar/um/url'
);

INSERT INTO Hotel VALUES(
    'Rvk Living',
    'Reykjavík',
    'Baldursgata 3',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );

INSERT INTO Room VALUES(
    'Rvk Living',
    'Baldursgata 3',
    1,
    2,
    1,
    7000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Rvk Living',
    'Baldursgata 3',
    2,
    1,
    1,
    5000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Rvk Living',
    'Baldursgata 3',
    3,
    3,
    1,
    9000,
    'vantar/upplýsingar/um/url'
);

--Ísafjörður
INSERT INTO Hotel VALUES(
    'Nature-Hotel',
    'Ísafjörður',
    'Tungubraut 12',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );

INSERT INTO Room VALUES(
    'Nature-Hotel',
    'Tungubraut 12',
    1,
    2,
    1,
    8000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Nature-Hotel',
    'Tungubraut 12',
    2,
    2,
    1,
    8000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Nature-Hotel',
    'Tungubraut 12',
    3,
    3,
    1,
    10000,
    'vantar/upplýsingar/um/url'
);

INSERT INTO Hotel VALUES(
    'Fjörður Sleephouse',
    'Ísafjörður',
    'Seljaland 1',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );

INSERT INTO Room VALUES(
    'Fjörður Sleephouse',
    'Seljaland 1',
    1,
    1,
    1,
    3000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Fjörður Sleephouse',
    'Seljaland 1',
    2,
    1,
    1,
    3000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Fjörður Sleephouse',
    'Seljaland 1',
    3,
    1,
    1,
    3000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Fjörður Sleephouse',
    'Seljaland 1',
    4,
    1,
    1,
    3000,
    'vantar/upplýsingar/um/url'
);

INSERT INTO Hotel VALUES(
    'Iceland Experience',
    'Ísafjörður',
    'Holtabraut 5',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );

INSERT INTO Room VALUES(
    'Iceland Experience',
    'Holtabraut 5',
    1,
    3,
    1,
    8000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Iceland Experience',
    'Holtabraut 5',
    2,
    3,
    2,
    8000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Iceland Experience',
    'Holtabraut 5',
    3,
    3,
    2,
    8000,
    'vantar/upplýsingar/um/url'
);

--Egilsstaðir
INSERT INTO Hotel VALUES(
    'Klett-Hotel',
    'Egilsstaðir',
    'Selás 9',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );

INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Selás 9',
    1,
    4,
    1,
    10000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Selás 9',
    2,
    4,
    1,
    10000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Selás 9',
    3,
    4,
    2,
    10000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Klett-Hotel',
    'Selás 9',
    4,
    4,
    2,
    10000,
    'vantar/upplýsingar/um/url'
);

INSERT INTO Hotel VALUES(
    'Jötun Guesthouse',
    'Egilsstaðir',
    'Tjarnarbraut 15',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );
INSERT INTO Room VALUES(
    'Jötun Guesthouse',
    'Tjarnarbraut 15',
    1,
    3,
    1,
    7000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Jötun Guesthouse',
    'Tjarnarbraut 15',
    2,
    3,
    1,
    7000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Jötun Guesthouse',
    'Tjarnarbraut 15',
    3,
    2,
    2,
    6000,
    'vantar/upplýsingar/um/url'
);

INSERT INTO Hotel VALUES(
    'Ice Cabins',
    'Egilsstaðir',
    'Kaupvangur 10',
    'vantar/upplýsingar/um/url',
    'Frábært hótel á flottum stað'
    );

INSERT INTO Room VALUES(
    'Ice Cabins',
    'Kaupvangur 10',
    1,
    2,
    1,
    8000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Ice Cabins',
    'Kaupvangur 10',
    2,
    1,
    1,
    5000,
    'vantar/upplýsingar/um/url'
);
INSERT INTO Room VALUES(
    'Ice Cabins',
    'Kaupvangur 10',
    3,
    3,
    1,
    10000,
    'vantar/upplýsingar/um/url'
);