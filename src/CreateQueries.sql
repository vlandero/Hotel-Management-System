CREATE TABLE Hotel (
       Nume VARCHAR(255) PRIMARY KEY,
       Adresa VARCHAR(255)
);

CREATE TABLE Camera (
        Numar VARCHAR(255) PRIMARY KEY,
        Etaj INT,
        PretNoapte INT,
        NumarLocuri INT,
        DescriereExtra VARCHAR(255),
        HotelNume VARCHAR(255),
        FOREIGN KEY (HotelNume) REFERENCES Hotel(Nume)
);


CREATE TABLE Serviciu (
          Nume VARCHAR(255) PRIMARY KEY,
          Descriere VARCHAR(255),
          Pret INT,
          Durata VARCHAR(255),
          HotelNume VARCHAR(255),
          FOREIGN KEY (HotelNume) REFERENCES Hotel(Nume)
);



CREATE TABLE Angajat (
         Username VARCHAR(255) PRIMARY KEY,
         Nume VARCHAR(255),
         Prenume VARCHAR(255),
         CNP VARCHAR(255),
         Salariu INT,
         UnitateSalariu VARCHAR(255),
         Job VARCHAR(255),
         StartDate DATE,
         EndDate DATE,
);

CREATE TABLE Client (
        Username VARCHAR(255) PRIMARY KEY,
        Nume VARCHAR(255),
        Prenume VARCHAR(255),
        CNP VARCHAR(255),
        Telefon VARCHAR(255)
);


CREATE TABLE Angajare (
      PersoanaUsername VARCHAR(255),
      HotelNume VARCHAR(255),
      DataAngajare DATE,
      PRIMARY KEY (PersoanaUsername, HotelNume),
      FOREIGN KEY (PersoanaUsername) REFERENCES Angajat(Username),
      FOREIGN KEY (HotelNume) REFERENCES Hotel(Nume)
);

CREATE TABLE Rezervare (
       PersoanaUsername VARCHAR(255),
       CameraNumar VARCHAR(255),
       HotelNume VARCHAR(255),
       CheckInDate DATE,
       CheckOutDate DATE,
       CheckedIn BOOLEAN,
       PRIMARY KEY (PersoanaUsername, CameraNumar, HotelNume),
       FOREIGN KEY (PersoanaUsername) REFERENCES Client(Username),
       FOREIGN KEY (CameraNumar) REFERENCES Camera(Numar),
       FOREIGN KEY (HotelNume) REFERENCES Hotel(Nume)
);

CREATE TABLE Disponibilitate (
         CameraNumar VARCHAR(255),
         HotelNume VARCHAR(255),
         Date VARCHAR(10),
         Disponibil BOOLEAN,
         PRIMARY KEY (CameraNumar, Date, HotelNume),
         FOREIGN KEY (CameraNumar) REFERENCES Camera(Numar)
         FOREIGN KEY (HotelNume) REFERENCES Hotel(Nume)
);

