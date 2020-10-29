# Smidige metoder
Dette prosjektet gjøres av gruppe 1 i faget IT1901. Her bruker vi smidige metoder og 
deler arbeidsflyten inn i sprints. Disse smidige metodene blir beskrevet i dette dokumentet.

## Retningslinjer for brukerhistorier
Funksjonaliteten i applikasjonen vår skal basere seg på brukerhistorier som blir beskrevet i filen
[brukerhistorier](gr2001/Brukerhistorier.md). Disse brukerhistoriene skal være skrevet på følgende form:

`Som en __ ønsker jeg __ slik at`

I tillegg skal det tas med feltene `viktig å se` og `viktig å kunne gjøre`. Her skal det forklares hva brukeren
skal kunne se på skjermen og hva brukeren skal kunne gjøre med applikasjonen. Brukerhistoriene skrives på 
denne måten fordi dette gjøre det enklere for å oss å jobbe målrettet med prosjektet. 

## Retningslinjer for utvikleroppgaver (issues)
Alt av bunter og arbeid som gjøres på applikasjonen skal kunne knyttes opp mot en spesifikk utvikleroppgave.
Disse utvikleroppgavene skal beskrive hva som trenger å bli gjort og hvordan dette skal bli gjort. 
I tillegg skal utvikleroppgaven kort forklare hvorfor dette gjøres. Ettersom at alt av arbeid som gjøres
skal kunne knyttes opp mot en utvikleroppgave så er det svært viktig å alltid tilføye nye oppgaver når noe må gjøres. 

### Form på utvikleroppgaver
utvikleroppgavene skal være på følgende form:
- Tittel: Det skal kort beskrives hva som må gjøres. Eksempel: 

`Lage toString()-metode i klassen "Person"`
- Beskrivelse: Det skal utvetydig beskrives hva som forventes oppfylt før ferdig oppgave. I tillegg til 
dette skal det begrunnes hvorfor dette skal gjøres. Eksempel:
 
`toString()-metoden skal returnere en tekststreng som inneholder navn, alder og antall kjæledyr 
til "Person"-objektet på en oversiktlig måte. Dette skal gjøres slik at man kan printe en 
liste med objekter på en effektiv måte`

### Bruk av tavle for utivkleroppgaver
Inne på 'board' på GitLab så skal utvikleroppgavene sorteres etter ...

## Retningslinjer for grener (branches)
Dersom en utvikleroppgave påvirker annet arbeid skal det lages en egen gren til utvikleroppgaven. Hvis flere 
utvikleroppgaver er relaterte til hverandre kan disse høre til samme gren, dersom dette er passende. Dette vurderes
fortløpende. 

### Navn på grener
Prosjeket skal ha en hovedgren som heter `master`. Versjonen som finnes i denne grenen skal til enhver tid være 
kjørbar. Før man fletter (merger) en gren inn i master-grenen så er det viktig at applikasjonen kan bygges uten problem og
at den fungerer som planlagt. Ved oppretting av nye grener skal navnet være på følgende form: 

`utviklingsbranch/{issues i tall}/{kortfattet beskrivelse}` 

Et eksempel på en branch kan være:

`utvikllingsbranch/47/48/49/rest-api`

## Retningslinjer for bunter (commits)
Når man bunter arbeid man har gjort skal ...

## Retningslinjer for dytting av kode (git push)
Når man dytter kode opp mot ... checkstyle ... 

