[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2001/gr2001/-/tree/utviklingsbranch) 

# Training Logger

Dette er mappen som ligger i rot-nivået til applikasjonen Training Logger. Mer informasjon om
selve applikasjonen finnes i [README](trainingLogger/README.md) i applikasjonsmappen. 

## Bygging og kjøring av applikasjon

Prosjektet vårt bruker Maven til bygging og kjøring. For å bygge, kjør `mvn install` i rot-mappen (traininglogger). Dette kjører alle testene og bygger prosjektet slik det skal. 
Etter å ha bygget prosjektet, kjør `mvn javafx:run` i fxui-mappen.

SKRIV DETTE ORDENTLIG SENERE!!!!

Fra rotmappe (trainingLogger)...

Starte server: mvn -pl integrationtests jetty:run -D"jetty.port=8999"
Starte remote-app: mvn -Premoteapp -pl fxui javafx:run

!!!!

**Merk**: For å kjøre applikasjonen i GitPod må man vente til dependencies har blitt lastet inn. Dette kan man se nede i venstre hjørne av skjermen.  
Dette skjer mye raskere dersom man trykker inne i en java-fil, for da gjenkjenner GitPod Java-språket. Etter at man har lastet inn dependencies så kan man installere  
ved hjelp av `mvn clean install`. GitPod har noen ganger problemer med installeringen. Dette skyldes trolig problemer med GitPod eller VM-en som brukes i GitPod, da 
det virker å være tilfeldig om det funker eller ikke. Dessuten funker installeringen hver gang i Intellij. Dersom install ikke fungerer så kan dere prøve å laste inn et nytt workspace eller kjøre `mvn clean install` på nytt. 

## Forklaring av mappestruktur
Prosjektet er bygd med mappestruktur i henhold til Maven. Strukturen vår ser slik ut:
- [**traininglogger**](traininglogger): Overordnet modul (parent):
    - [**core**](traininglogger/core): Core-modul
        - [**core**](traininglogger/core/src/main/java/traininglogger/core): core-mappe. Her ligger alle kjerne-klassene som ligger bak applikasjonen
        - [**json**](traininglogger/core/src/main/java/traininglogger/json): json-mappe. Her ligger alle klassene som blir brukt til lagring
        - [**resources**](traininglogger/core/src/main/resources): resource-mappe. Her ligger ressursene som blir brukt i core 
        - [**test**](traininglogger/core/src/test): Her ligger alt av tester og ressurser som hører til testene
    - [**fxui**](traininglogger/fxui): fxui-modul
        - [**ui**](traininglogger/fxui/src/main/java/traininglogger/ui): ui-mappe. Her ligger alt av kontrollere og hjelpeklasser for ui
        - [**resources**](traininglogger/fxui/src/main/resources): resource-mappe. Her ligger alt av .fxml-filer og eventuelle andre ressurser som trengs
        - [**test**](traininglogger/fxui/src/test): Her ligger alt av tester og ressurser som hører til testene

### Build-filer
Build-filene vi bruker ligger på rotnivå (utenom pom.xml som ligger under prosjektmappen). 
Filer for Gitpod-konfigurasjon
- .gitpod.yml
- .gitpod.Dockerfile

Brukerhistorier.md inneholder fire brukerhistorier.
Mappen trainingLogger inneholder selve kodingsprosjektet.
