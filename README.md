# Parking Management

Yksinkertainen Spring Boot -sovellus pysäköintihallin hallintaan.

## Toteutetut ominaisuudet

- Auton sisäänajo pysäköintihalliin
- Auton ulosajo pysäköintihallista
- Pysäköintihallin kapasiteetin ja vapaan tilan tarkastelu
- Yksittäisen pysäköintipaikan saatavuuden tarkastelu
- Aktiivisten pysäköintitapahtumien / varattujen paikkojen listaus
- Pysäköinnin hinnanlaskenta laskenta
- In-memory-tallennus
- Keskitetty virheenkäsittely
- Yksikkötestejä hinnanlaskennalle ja palvelukerrokselle

## Teknologiat

- Java 21
- Spring Boot
- Maven
- JUnit 5
- Mockito

## Käynnistäminen

```bash
mvn spring-boot:run
```

Sovellus käynnistyy oletuksena osoitteeseen:

```text
http://localhost:8080
```

## REST-rajapinta

### Hallin tila

```http
GET /parking/lot
```

Palauttaa hallin kapasiteetin, vapaiden paikkojen määrän, varattujen paikkojen määrän sekä tiedon siitä, onko halli täynnä.

### Yksittäisen paikan saatavuus

```http
GET /parking/space/{spaceNumber}
```

Palauttaa `true`, jos paikka on vapaa, ja `false`, jos paikka on varattu.

### Varatut pysäköintipaikat / aktiiviset pysäköinnit

```http
GET /parking/space
```

Palauttaa tällä hetkellä varatut pysäköintipaikat ja niihin liittyvät rekisterinumerot.

Huom. tämä endpoint palauttaa nykyisessä toteutuksessa aktiiviset pysäköintitapahtumat, ei kaikkien hallin paikkojen tilaa.

### Auto sisään

```http
POST /parking/event
```

Esimerkkipyyntö:

```json
{
  "registrationNumber": "ABC-123",
  "parkingSpaceNumber": 1
}
```

### Auto ulos

```http
DELETE /parking/event/{registrationNumber}
```

Palauttaa pysäköinnin alkamisajan, päättymisajan, keston sekunneissa ja hinnan senteissä.

## Huomioita toteutuksesta

Sovellus käyttää in-memory-tallennusta, joten tiedot häviävät sovelluksen sammuttamisen yhteydessä. Toteutuksessa on keskitytty backend-logiikkaan, REST-rajapintaan, hinnanlaskentaan ja perusrakenteeseen.
