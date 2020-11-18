[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2001/gr2001/-/tree/utviklingsgren) 

# Training Logger

Dette er mappen som ligger i rot-nivået til applikasjonen Training Logger. Mer informasjon om
selve applikasjonen finnes i [README](trainingLogger/README.md) i applikasjonsmappen. 

## Bygging og kjøring av applikasjonen

Applikasjonen bygges og kjøres ved hjelp av byggeverktøyet Maven. Applikasjonen fungerer både lokalt og med tilkobling til server. 
Uansett hvilken måte man velger å bygge på så må man først kjøre kommandoen 
```
mvn install
```

### Bygging og kjøring lokalt
Etter at man har installert prosjektet må man gå inn i mappen fxui ved hjelp av kommandoen 
```
cd fxui
```
Herfra kan man starte applikasjonen med kommandoen 
```
mvn javafx:run
``` 

### Bygging og kjøring med server
Etter at man har installert prosjektet må man starte serveren ved hjelp av kommandoen  
```
mvn -pl integrationtests jetty:run -D"jetty.port=8999"
```  
Dette starter serveren på port 8999. Deretter må man starte applikasjonen gjennom profilen "remoteapp". Dette gjøres med kommandoen  
```
mvn -Premoteapp -pl fxui javafx:run
``` 

### Troubleshooting
Dersom det skulle oppstå problemer under installasjon kan det hjelpe å rydde prosjektet med kommandoen 
```
mvn clean
``` 
før installasjon.

**Merk:** Ettersom at GitPod er en VM så kan det oppstå problemer ved kjøring av tester, da noen av testene baserer seg på 
bytte av vindu. Det er fordi VM-en ofte kjører tregere enn en vanlig personlig datamaskin og dermed bruker den noen ganger for lang 
tid på å bytte vinduer før testen forsøker å finne neste element i brukergrensesnittet. Dette kan oppstå ved tilfeldige tider 
(basert på hvor mange som bruker GitPod og internetthastigheter o.l.) så dersom testene skulle feile så er det bare å kjøre installasjonen
på nytt. 

## Forklaring av mappestruktur
Prosjektet er bygd med mappestruktur i henhold til Maven. Strukturen vår ser slik ut:

- [**traininglogger**](traininglogger): Overordnet modul (parent):
    - [**core**](traininglogger/core): Core-modul
        - [**core**](trainingLogger/core/src/main/java/traininglogger/core): core-mappe. Her ligger alle kjerne-klassene som ligger bak applikasjonen
        - [**json**](traininglogger/core/src/main/java/traininglogger/json): json-mappe. Her ligger alle klassene som blir brukt til lagring
        - [**resources**](traininglogger/core/src/main/resources): resource-mappe. Her ligger ressursene som blir brukt i core 
        - [**test**](traininglogger/core/src/test): Her ligger alt av tester og ressurser som hører til testene
    - [**fxui**](traininglogger/fxui): fxui-modul
        - [**ui**](traininglogger/fxui/src/main/java/traininglogger/ui): ui-mappe. Her ligger alt av kontrollere og hjelpeklasser for ui
        - [**resources**](traininglogger/fxui/src/main/resources): resource-mappe. Her ligger alt av .fxml-filer og eventuelle andre ressurser som trengs
        - [**test**](traininglogger/fxui/src/test): Her ligger alt av tester og ressurser som hører til testene
    - [**integrationtests**](trainingLogger/integrationtests): integrationtests-modul: Inneholder tester for integrasjonen mellom server og kjernelogikk
    - [**restapi**](trainingLogger/restapi): restapi-modul:
        - [**restapi**](trainingLogger/restapi/src/main/java/traininglogger/restapi): Inneholder klasser for implementasjon av REST-api
    - [**restserver**](trainingLogger/restserver): restserver-modul: 
        - [**restserver**](trainingLogger/restserver/src/main/java/traininglogger/restserver): Inneholder klasser for bruk av lokal server
        - [**resources**](trianingLogger/restserver/src/main/resources): Inneholder default-filer for lagringen
        - [**test**](trainingLogger/restserver/src/test): Inneholder tester av REST-serveren samt ressurser for testing

For mer informasjon om strukturen til applikasjonen, se [README](trainingLogger/README.md) i prosjektmappen. Her finnes diverse diagrammer som forklarer strukturen ytterligere. 

### Build-filer
Build-filene vi bruker ligger på rotnivå (utenom pom.xml som ligger under prosjektmappen). 
Filer for Gitpod-konfigurasjon
- .gitpod.yml
- .gitpod.Dockerfile

