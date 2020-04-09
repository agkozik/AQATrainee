package lyambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartGame {

    public static void main(String[] args) {
        Player player1 = new Player("Lanselot", "Human", 100.0, 40);
        Player player2 = new Player("Aanselot", "1uman", 100.0, 40);
        Player player3 = new Player("Canselot", "2uman", 70.0, 40);
        Player player4 = new Player("Banselot", "0uman", 900.0, 40);

        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));
        //System.out.println(players);

        players.sort(
                (Player p1, Player p2) ->
                        p1.getName().compareToIgnoreCase(p2.getName())
        );
        //System.out.println(players);
        int[] array1 = new int[900];
        int[] array2 = new int[array1.length];

        for (int i = 0, j=array1.length-1; i<array1.length ; i++, j--) {
            array1[i] =i;
            array2[j]=array1[i];
            System.out.println("array1="+array1[i]+" array2="+array2[i]);

        }

    }
}