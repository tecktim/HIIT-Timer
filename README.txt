***WORKOUT APP***
----------------------------------------------------------------------------------------------------------------------------------------
Urheber:	Tim Friedrich 31293
		Max Wolf 31287
----------------------------------------------------------------------------------------------------------------------------------------
Auflistung der verwendeten fremden Codebestandteile:

----------------------------------------------------------------------------------------------------------------------------------------
Kurze Beschreibung und Begründung der Architektur:
MVC, Model-View-Control
Jeder unserer Screens ist eine View die die Benutzeroberfläche beschreibt und anzeigt.
Jede unserer Views hat einen Controller in dem die Auswirkungen der Benutzerelemente ausgeführt werden.
Wir haben ein Model, das die Daten hält und verwaltet.

Hierfür wird Model einmal instanziert
Der Controller kriegt das Model und die PrimaryStage, damit er Aussehen und Daten verändern kann
Die View kriegt das Model damit Daten angezeigt werden können, die Primary Stage damit Bedienelemente angezeigt werden können und schließlich den 
Controller damit Veränderungen an der Bedienoberfläche getätigt werden können.

Wir haben uns für MVC entschieden, da wir mehrere Screens (Views) haben die zwar unterschiedliche Controller benötigen aber nur ein Model
benötigen das die Daten verwaltet.

----------------------------------------------------------------------------------------------------------------------------------------
Kurze Beschreibung, auf welche Weise Aspekte der Software-Ergonomie in die Gestaltung der Oberfläche eingeflossen sind:

Alle Screens sind gleich strukturiert damit der User sich schnell zurechfindet.
----------------------------------------------------------------------------------------------------------------------------------------



