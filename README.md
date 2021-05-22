# Parser für Operationen auf Fuzzy-Mengen (und scharfe Mengen)

Das Programm erlaubt die Definition von Mengen und die Anwendung von Operationen auf diese Mengen. Die Kommandos werden entweder direkt von der Standardeingabe gelesen und sofort ausgeführt oder zeilenweise aus einer Textdatei entnommen, deren Dateiname beim Programmstart als Parameter angegeben wird. Jede Eingabe schließt mit einem Semikolon und dem Zeilenende ab. Die Ausgaben des Programms erfolgen über die Standardausgabe.

**Benutzung des Programms**
Start des Programms:
````
java -jar FuzzySetInterpreter.jar
````
Start des Programms mit einem Skriptfile:
````
java -jar FuzzySetInterpreter.jar test.set
````
Wenn keine Datei angegeben wird, verarbeitet das Programm Eingaben aus der Standardeingabe. Jede vollständig eingegebene Anweisung wird verarbeitet, sobald die Return-Taste betätigt wird. Mit der Anweisung exit; wird das Programm verlassen. Es sind drei Beispiel-Dateien vorhanden: *examples.set*, *test.set* und *supermarkt.set*.

**Die Kommandos der Skriptsprache**
Die Skriptsprache ist im Dokument *Dokumentation.docx* beschrieben.