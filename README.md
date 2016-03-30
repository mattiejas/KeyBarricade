# KeyBarricade
Het spel “Sleutelbarricade” bestaat uit een speelveld van een vierkant vlak. De speler moet lopen naar een eindveld. Dat is het doel. In het doolhof zijn vaste muren en barricades. De barricade kan je openen met een passende sleutel. De speler moet daarom ook eerst een sleutel pakken. De sleutel kun je meerdere keren gebruiken maar je kan slechts één sleutel in je “zak” hebben. Als de speler een sleutel oppakt verdwijnt deze uit het doolhof. Hij zit dan namelijk in je “zak” en kun je dit niet meer op het speelveld neerleggen en of terugzetten. Als je een sleutel probeert die niet past, krijg je een melding. Past hij wel dan verdwijnt de barricade. Je kunt de speler verplaatsen door het gebruik van de pijltjestoetsen. 

***

###### Interesting sources
+ UML of a [GameState design](http://blog.nuclex-games.com/tutorials/cxx/game-state-management/)
+ Video about creating a [GameStateManager](https://www.youtube.com/watch?v=OCcZUO4Zf6o)
+ Video that explains [stacks](https://www.youtube.com/watch?v=8TMBjfS8wY0)

###### Questions (maar dan zijn de vragen in het Nederlands)
+ Moeten we interfaces zoals KeyListener, actionListener en JPanel implementeren in onze design diagram?
+ Hoe moeten we de Game.WIDTH en Game.HEIGHT modelleren?

###### Reminders (because we will forget)
+ Analysis Diagram: Associatie van TypeBlock aanpassen met een multiplciteit van 1.
+ ResourceLoader komt op meerdere plekken terug in diverse klassen, hiervoor moeten we nog een dependency toevoegen.
+ Random char genereren:
  import java.util.Random;

    //...

    Random r = new Random();

    String alphabet = "123xyz";
    
    for (int i = 0; i < 50; i++) {
    
        System.out.println(alphabet.charAt(r.nextInt(alphabet.length())));
        
    } // prints 50 random characters from alphabet
