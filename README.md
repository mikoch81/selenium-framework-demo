# Selenium Java Framework Demo

Portfolio demo pokazujace, jak zbudowac klasyczny, utrzymywalny framework UI automation w Javie dla zespolu, ktory potrzebuje stabilnosci, czytelnej architektury i prostego wejscia do CI.

## Co dostaje klient

- framework oparty o Page Object,
- lokalne, w pelni powtarzalne uruchomienie bez zaleznosci od zewnetrznych stron,
- screenshoty przy failach,
- prosta baza do rozbudowy o wieksza regresje i execution w pipeline.

## Co pokazuje to repo

- logowanie z walidacja blednych danych,
- poprawny przeplyw zakupu,
- blokade finalizacji przy pustym koszyku,
- podejscie frameworkowe zamiast pojedynczych skryptow testowych.

## Stack

- Java 21
- Selenium 4
- JUnit 5
- Maven

## Uruchomienie lokalne

```bash
mvn test
```

Tryb z widoczna przegladarka:

```bash
mvn -Dheadless=false test
```

## Dlaczego to jest wartosciowe portfolio

To repo dobrze sprzedaje usluge typu "Enterprise UI Automation Starter", gdy klient potrzebuje:

- czytelnej architektury testow pod dluzsze utrzymanie,
- ustandaryzowanego podejscia do driverow, Page Objectow i artefaktow,
- szybkiego startu do testow regresji w projektach Java i Selenium.

## Struktura

- `src/test/resources/demo-app/index.html` - lokalna aplikacja demo,
- `src/test/java/dev/michalkochaniak/portfolio/selenium/config` - konfiguracja srodowiska i WebDrivera,
- `src/test/java/dev/michalkochaniak/portfolio/selenium/pages` - Page Objecty,
- `src/test/java/dev/michalkochaniak/portfolio/selenium/support` - serwer lokalny, base test, artefakty,
- `src/test/java/dev/michalkochaniak/portfolio/selenium/tests` - testy regresji,
- `.github/workflows/maven.yml` - workflow CI.

## Bezpieczenstwo i etyka

Suite dziala wylacznie na lokalnej aplikacji demo spakowanej w repozytorium. Nie jest przeznaczony do testowania systemow firm trzecich bez jawnej autoryzacji.
