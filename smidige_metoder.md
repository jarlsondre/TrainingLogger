# Smidige metoder
Dette prosjektet gjøres av gruppe 1 i faget IT1901. Her bruker vi smidige metoder og 
deler arbeidsflyten inn i sprints. Disse smidige metodene blir beskrevet i dette dokumentet. Metodene 
tas i bruk for fullt fra og med Sprint 4 (02.11.20)

## Retningslinjer for brukerhistorier
Funksjonaliteten i applikasjonen vår skal basere seg på brukerhistorier som blir beskrevet i filen
[brukerhistorier](gr2001/Brukerhistorier.md). Disse brukerhistoriene skal være skrevet på følgende form:

`Som en __ ønsker jeg __ slik at __`

I tillegg skal det tas med feltene `viktig å se` og `viktig å kunne gjøre`. Her skal det forklares hva brukeren
skal kunne se på skjermen og hva brukeren skal kunne gjøre med applikasjonen. Brukerhistoriene skrives på 
denne måten fordi dette gjør det enklere for å oss å jobbe målrettet med prosjektet. 

## Retningslinjer for utvikleroppgaver (issues)
Alt av bunter og arbeid som gjøres på applikasjonen skal kunne knyttes opp mot en spesifikk utvikleroppgave.
Disse utvikleroppgavene skal beskrive hva som trenger å bli gjort og hvordan dette skal bli gjort. 
I tillegg skal utvikleroppgaven kort forklare hvorfor dette gjøres. Ettersom at alt av arbeid som gjøres
skal kunne knyttes opp mot en utvikleroppgave så er det svært viktig å alltid tilføye nye oppgaver når noe må gjøres. 

### Form på utvikleroppgaver
utvikleroppgavene skal være på følgende form:
- Tittel: Det skal kort beskrives hva som må gjøres. Eksempel: 

>Lage toString()-metode i klassen "Person"
- Beskrivelse: Det skal utvetydig beskrives hva som forventes oppfylt før ferdig oppgave. I tillegg til 
dette skal det begrunnes hvorfor dette skal gjøres. Eksempel:
 
>toString()-metoden skal returnere en tekststreng som inneholder navn, alder og antall kjæledyr 
til "Person"-objektet på en oversiktlig måte. Dette skal gjøres slik at man kan printe en 
liste med objekter på en effektiv måte

### Bruk av tavle for utivkleroppgaver
Når en utvikleroppgave blir laget inne på GitLab, så skal denne plasseres i en av tre kategorier: `Må gjøres`, 
`Burde gjøres` og `Kan gjøres` 

Når en utvikler skal velge (eller få tildelt) en utvikleroppgave så skal alltid de oppgavene som står under
`Må gjøres` prioriteres. Dersom alle disse utvikleroppgavene har blitt gjort så skal oppgavene som står under
`Burde gjøres` prioriteres. Dersom begge disse kategoriene er tomme så kan man en utvikler velge eller få 
tildelt oppgaver under `kan gjøres`. Dette gjøres for å sikre at utviklerne ikke bruker mye tid på utvikleroppgaver
som ikke er nødvendige når andre utvikleroppgaver heller burde prioriteres.  

I tillegg til disse tre kategoriene, inneholder også tavlen vår kategoriene `Doing` og `Needs review`.
Etter at en utvikleroppgave har blitt påbegynt så skal den flyttes over i kolonnen `Doing`. Her skal oppgaven forbli
til utvikleren(e) som jobbet med oppgaven mener at den er ferdig. Da skal den flyttes over i kolonnen `Needs Review`. 
Deretter må en annen utvikler se over utvikleroppgaven og buntene som har blitt assosiert med oppgaven. Når buntene 
har blitt sett over skal utvikleren som så over kommentere på utvikleroppgaven. Dersom denne utvikleren mener 
at oppgaven er ferdig skal utvikleren skrive at arbeidet ser bra ut og at de lukker oppgaven. Så skal oppgaven lukkes. 
Dersom utvikleren som ser over mener at oppgaven ikke er ferdig skal dette kommenteres og forklares på en utvetydig 
måte. Deretter skal oppgaven flyttes over til `Doing` igjen.  

## Retningslinjer for grener (branches)
Dersom en utvikleroppgave påvirker annet arbeid skal det lages en egen gren til utvikleroppgaven. Hvis flere 
utvikleroppgaver er relaterte til hverandre kan disse høre til samme gren, dersom dette er passende. Dette vurderes
fortløpende. 

### Navn på grener
Prosjeket skal ha en hovedgren som heter `master`. Versjonen som finnes i denne grenen skal til enhver tid være 
kjørbar. Før man fletter (merger) en gren inn i master-grenen så er det viktig at applikasjonen kan bygges uten problem og
at den fungerer som planlagt. Ved oppretting av nye grener skal navnet være på følgende form: 

`utviklingsgren/{utviklingsoppgaver i tall}/{kortfattet beskrivelse}` 

Et eksempel på en gren kan være:

>utvikllingsgren/47/48/49/rest-api

## Retningslinjer for bunter (commits)
Når man bunter arbeid som har blitt gjort så skal disse buntene inneholde en buntmelding på 
følgende form: 

> {#utvikleroppgavenummer} <br/> <br/> {Forklaring av hva som har blitt endret og hva disse endringene innebærer} 

## Retningslinjer for dytting av kode (git push)
Før man dytter buntene sine opp til GitLab skal man alltid sjekke at det ikke finnes noen 
checkstyle problemer eller advarsler. Dette sjekkes ved kommandoen `mvn checkstyle:checkstyle`.
Dersom man får problemer eller advarsler så må disse fikses før buntene dyttes opp til GitLab.

## Forklaring av språk
I dette dokumentet blir mange begreper fornorsket fra Engelsk. Under følger en liste med gloser:

Fornorskede ord | Engelske ord
----------|-------------
Utvikleroppgaver | Issues
Bunt | Commit
Tavle | Board
Gren | Branch
Dytting av kode | Git Push 


