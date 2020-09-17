# Training Logger

Training Logger er en applikasjon som lar deg loggføre gjennomførte treninger i tillegg til å planlegge fremtidige treninger.

## Beskrivelse

### Grunnidè

Grunnidèen til applikasjonen er en applikasjon som kan brukes både før trening, under trening og etter trening; man skal kunne planlegge en økt, 
endre en økt og i ettertid bruke informasjonen til økten for å forbedre neste trening. Det langsiktige bildet av grunnidèen er som 
følger: 

I applikasjonen skal man komme til et vindu hvor man kan se tidligere økter. Her kan man utføre følgende handlinger:
- Man kan trykke på en økt. Da vil økten utvides og vise mer informasjon. Her vil man da se hva som ble gjort på økten,
og man kan velge å slette eller endre på økten om man ønsker det.
- Man kan velge å lage en ny økt. Her kan man velge hvilke øvelser man skal ta med, og man kan legge til så mange 
sett man vil. På hvert sett så kan man velge antall repetisjoner og vekt uavhengig av de andre settene. 

### Utvidelser fra grunnidèen
Applikasjonen skal etterhvert utvides med funksjonalitet som forbedrer planleggingen og gir brukeren
en detaljert oversikt over egen trening. Grunnidèen er i hovedsak beskrevet med en baktanke om styrketrening. Applikasjonen
skal utvides med funksjonalitet som støtter andre former for trening, slik som kondisjonstrening. 
Det er tenkt at applikasjonen skal inneholde følgende funksjoner: 
- Progresjonsmåler: Man skal som bruker kunne se en oversikt over egen progresjon. Denne oversikten vil vise
estimert maksløft basert på hva brukeren har gjort på trening. Denne estimeringen regnes enkelt ut ved hjelp av
en formel basert på hva som ble gjort og hvor mange ganger det ble gjort. I tillegg skal applikasjonen holde styr på
personlige rekorder. 
- Kommentarer tilknyttet hvordan brukeren opplevde økten som gjør analyse av lengre
treningsperioder mulig. Brukeren skal da kunne skrive en tekstkommentar og i tillegg velge hvor hard økten
opplevdes (hard, middels, enkel) hvis dette er ønskelig. 
- Mulighet for å loggføre andre typer økter enn styrketrening, slik som kondisjonstrening. Innenfor løping vil man blant 
annet kunne holde styr på rundetider, ukentlige kilometer, tempo og personlige rekorder. 

## Logisk struktur

Applikasjonen har et SessionLogger-objekt som tar vare på alle øktene (Sessions) som er lagret. Hver Session
har en streng som inneholder beskrivelsen av økten og et LocalDateTime-objekt som tar vare på når
økten ble opprettet. 

![KlassediagramCore](resources/KlasseDiagramCore.png)

![KlassediagramJson](resources/KlasseDiagramJson.png)

## Nåværende utseende av applikasjonen
Dette er et bilde av hvordan applikasjonen ser ut ved gruppeinnlevering 1. 

![Eksempel](resources/LoggEksempel.png)

## Tenkt utseende til sluttprodukt

[bilde her]


## Brukerhistorie 1: Loggføre treningsøkten

Vi har brukt følgende brukerhistore som utgangspunkt for hvilken funksjonalitet vi i første omgang har implementert:

"Jarl er på trening og ønsker å loggføre økten sin slik at han vet hva han har gjort.  I tillegg ønsker han å skrive ned hva som gikk bra og hva som gikk dårlig, 
slik at han kan planlegge og forbedre neste trening. Han vil også kunne hente fram loggen ved et senere tidspunkt."


