***WORKOUT APP***
----------------------------------------------------------------------------------------------------------------------------------------
Urheb:innen:	Tim Friedrich 31293
		Max Wolf 31287
----------------------------------------------------------------------------------------------------------------------------------------
Auflistung der verwendeten fremden Codebestandteile:
- Für timerTask und BooleanBindings wurden die Vorlesungsfolien angeschaut

- Eingabevalidierung von sets und reps per changeListener:
	https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx

- Um das Icon der Anwendung zu verändern:
	https://stackoverflow.com/questions/10121991/javafx-application-icon
	Das verwendete Icon von: https://www.freepik.com https://www.flaticon.com/

- CSS Klassen wurden Mithilfe erstellt mit Hilfe von:
	https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html
	https://stackoverflow.com/questions/6092500/how-do-i-remove-the-default-border-glow-of-a-javafx-button-when-selected
----------------------------------------------------------------------------------------------------------------------------------------
Kurze Beschreibung und Begründung der Architektur:
MVC, Model-View-Control
Jeder unserer Screens ist eine View die die Benutzeroberfläche beschreibt und anzeigt.
Jede unserer Views hat einen Controller um auf die Eingaben des Benutzers reagieren zu können.
Wir haben ein Model, das die Daten hält und verwaltet.

Hierfür wird Model einmal instanziert
Der Controller kriegt das Model und die PrimaryStage, damit er Aussehen und Daten verändern kann
Die View kriegt das Model damit Daten angezeigt werden können, die Primary Stage damit Bedienelemente angezeigt werden können und schließlich den 
Controller damit Veränderungen an der Bedienoberfläche getätigt werden können.

Wir haben uns für MVC entschieden, da wir mehrere Screens (Views) haben die zwar unterschiedliche Controller benötigen aber nur ein Model
benötigen das die Daten verwaltet.
----------------------------------------------------------------------------------------------------------------------------------------
Kurze Beschreibung, auf welche Weise Aspekte der Software-Ergonomie in die Gestaltung der Oberfläche eingeflossen sind:
	Einige Gestaltungsgesetze die von uns beachtet wurden:
	- Das Gesetz der Erfahrung haben wir umgesetzt, indem die Buttons, die in der Anwendung auf den vorherigen Screen führen immer links sind,
	und die die auf den nächsten Screen führen immer rechts sind.
	- Das Gesetz der Nähe wurde bei dem Hinzufügen der Übungen beachtet, als auch bei der Darstellung des Timers und seinen zugehörigen Bedienelementen.
	

Alle Screens sind gleich strukturiert damit der User sich schnell zurechfindet.
----------------------------------------------------------------------------------------------------------------------------------------



