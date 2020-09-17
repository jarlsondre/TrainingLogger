[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2001/gr2001.git) 

# Training Logger

Dette er mappen som ligger i rot-nivået til applikasjonen Training Logger. Mer informasjone om
selve applikasjonen finnes i [README](trainingLogger/README.md) i applikasjonsmappen. 

## Forklaring av mappestruktur
Prosjektet er bygd med mappestruktur i henhold til Maven. Strukturen vår ser slik ut:
- **src/main/java**: Her ligger mappene med alle java-klassene våre
    - **core**: her ligger alle filene som har med øktene og lagring å gjøre
    - **json**: her ligger alle filene som har med json å gjøre
    - **ui**: her ligger alle kontrollerne og filer for kjøring av prosjektet
- **src/main/resources**: Her ligger alle ressursene som brukes av applikasjonen vår 
(fxml-filer, bilder o.l.)
- **src/java/java**: Her skal alle tester til koden ligge

### Build-filer
Build-filene vi bruker ligger på rotnivå (utenom pom.xml som ligger under prosjektmappen). 
Filer for Gitpod-konfigurasjon
- .gitpod.yml
- .gitpod.Dockerfile

Brukerhistorier.md inneholder to brukerhistorier
Mappen trainingLogger der selve kodingsprosjektet ligger