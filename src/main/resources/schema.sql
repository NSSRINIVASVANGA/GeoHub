CREATE TABLE if not exists country(
    countryId INTEGER PRIMARY KEY AUTO_INCREMENT,
    countryName TEXT,
    currency TEXT,
    population INTEGER,
    latitude TEXT,
    longitude TEXT
);


CREATE TABLE if not exists city(
    cityId INTEGER PRIMARY KEY AUTO_INCREMENT,
    cityName TEXT,
    population INTEGER,
    latitude TEXT,
    longitude TEXT,
    countryId INTEGER,
    FOREIGN KEY (countryId) REFERENCES country(countryId)
);