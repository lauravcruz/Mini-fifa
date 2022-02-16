package fifa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class actividad6 {
    static String equipo1, equipo2;
    static String [] jugadores1 = new String [3], jugadores2 = new String [3];
    static int [][] puntos1 = new int [3][4], puntos2 = new int [3][4];
    static String [] caracteristicas = {"AT", "DF", "TR", "PA"};
    static int [] marcador = {0, 0};
    static int goles = 2;
    static int [] estadisticas = new int [3];
    static String [] estadisticasString = {"Número de ataques: ", "Número de tiros: ", "Número de paradas: "};
    static int mejorJugador1 [] = new int [3];
    static int mejorJugador2 [] = new int [3];

    //Github



    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int option;
        boolean salir = false;

        while(!salir){
            System.out.println("I EI SPORTS CHINDEGEIN MINI FIFA STREET");
            System.out.println("1. Crear equipo 1");
            System.out.println("2. Crear equipo 2");
            System.out.println("3. Mostrar equipos");
            System.out.println("4. Jugar partido");
            System.out.println("5. Tanda de penaltis");
            System.out.println("6. Salir del juego");

            try{
                System.out.println("Elija una opción");
                option = leer.nextInt();
                switch (option){
                    case 1:
                        equipo1 = crearEquipo(jugadores1, puntos1);
                        break;

                    case 2:
                        equipo2 = crearEquipo(jugadores2, puntos2);
                        break;

                    case 3:
                        mostrarEquipos(equipo1, jugadores1, puntos1);
                        System.out.println(" ");
                        mostrarEquipos(equipo2, jugadores2, puntos2);
                        break;

                    case 4:
                        System.out.println("¿A cuántos goles queréis jugar?");
                        goles = leer.nextInt();
                        jugarPartido(equipo1, jugadores1, puntos1, equipo2, jugadores2, puntos2);
                        marcador[0] = 0; //reiniciamos el marcador después del partido
                        marcador[1] = 0;
                        break;

                    case 5:
                        tandaPenaltis();
                        marcador[0] = 0; //reiniciamos el marcador después de la tanda
                        marcador[1] = 0;
                        break;

                    case 6:
                        System.out.println("MINI FIFA STREET. Juego realizado por Laura Valiente Cruz. 2021");
                        salir = true;
                        break;

                    default:
                        System.out.println("Introduzca una opción válida");
                }

            }catch (InputMismatchException e){
                System.out.println("Debe usar un número");
                leer.next();
            }
        }

        }
    public static String crearEquipo (String [] jugadores,int [][] puntos){
        Scanner leer = new Scanner (System.in);

        System.out.println("¿Cómo se llamará tu equipo?");
        String equipo = leer.nextLine();

        for (int i = 0; i<jugadores.length; i++){
            System.out.println("Nombre del jugador " + (i+1) + ":");
            jugadores[i] = leer.nextLine();

            while (validarNombre(jugadores[i]) == false){
                System.out.println("No se admiten caracteres. Vuelva a intentarlo");
                jugadores[i] = leer.nextLine();
            }
            crearJugador(jugadores[i], i, puntos);

        }
        return equipo;
    }

    public static void crearJugador(String jugadores, int jugador, int [][] puntos){
        Scanner leer = new Scanner (System.in);
        int suma = 0; //Acumulamos los puntos que añade, así podemos pararlo en cuanto se detecte que sobrepasa 100

        System.out.println("Introduzca las características de " + jugadores);
        System.out.println("Hay 4 disponibles: ataque, defensa, tiro y parada. La suma de las 4 no puede superar 100, y ninguna puede tener menos de 15");

        for (int j = 0; j < 4; j++) {
            System.out.println(caracteristicas[j] + ":");
            puntos[jugador][j] = leer.nextInt();

            while (puntos[jugador][j] < 15) {
                System.out.println("No puede haber ninguna característica por debajo de 15. Prueba de nuevo");
                puntos[jugador][j] = leer.nextInt();
            }

            suma += puntos[jugador][j];
            if (suma > 100) {
                System.out.println("Ya ha sobrepasado los 100 puntos. Inténtalo de nuevo");
                crearJugador(jugadores, jugador, puntos);
            }
        }


    }

    public static void mostrarEquipos(String equipo, String [] jugadores, int[][] puntos) {

        System.out.println(equipo + ":");

        for (int i = 0; i <3; i++){
            System.out.print(jugadores[i] + ": ");
            for (int j = 0; j<4; j++) {
                System.out.print(caracteristicas[j] + puntos[i][j] + " ");
            }
            System.out.println("");
        }

    }

    public static void jugarPartido (String equipoAT, String [] jugadoresAT, int [][] puntosAT, String equipoDF, String [] jugadoresDF, int[][] puntosDF){
        Scanner leer = new Scanner (System.in);
        int atacante, defensor, tirador, portero;

        if (marcador[0] == goles || marcador[1] == goles) { //Delimitamos el final de la función (nº goles)
            System.out.println("FINAL DEL PARTIDO: " + equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
            mostrarEstadisticas();

        }else {
            System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);

            System.out.println(equipoAT + " inicia el ataque. ¿Con qué jugador quieres atacar? (Inserta 1, 2 o 3)");
            mostrarEquipos(equipoAT, jugadoresAT, puntosAT);
            atacante = leer.nextInt();
            estadisticas[0]++; //lo almacenamos en las estadísticas
            if (equipoAT.equals(equipo1)){ //Aquí iremos contando el nº de veces que participa un jugador
                mejorJugador1[atacante-1]++;
            }else{
                mejorJugador2[atacante-1]++;
            }

            System.out.println(equipoDF + ", elige un defensor (Inserta 1, 2 o 3)");
            mostrarEquipos(equipoDF, jugadoresDF, puntosDF);
            defensor = leer.nextInt();
            if (equipoDF.equals(equipo1)){
                mejorJugador1[defensor-1]++;
            }else{
                mejorJugador2[defensor-1]++;
            }

            /*Pasamos los parámetros necesarios para calcular si gana ataque o defensa.
            0 y 1 son las posiciones donde están guardadas AT y DF en la matriz de los puntos.*/

            if (calcularJugada(puntosDF, puntosAT, 0, 1, defensor - 1, atacante - 1) == false) {
                System.out.println("Oh, oh... ¡La defensa inquebrantable de " + equipoDF + " recupera la pelota.");
                jugarPartido(equipoDF, jugadoresDF, puntosDF, equipoAT, jugadoresAT, puntosAT);

            } else {
                System.out.println(jugadoresAT[atacante - 1] + " consigue superar al defensa y pasa el balón a: (Inserta 1, 2 o 3)");
                mostrarEquipos(equipoAT, jugadoresAT, puntosAT);
                tirador = leer.nextInt(); //Creamos una variable tirador para poder comparar si es el mismo jugador de la jugada anterior
                estadisticas[1]++;

                while (tirador == atacante) {
                    System.out.println(jugadoresAT[atacante - 1] + ", no seas chupón, pasa el balón a un compañero para que realice el tiro (Inserta 1, 2 o 3)");
                    mostrarEquipos(equipoAT, jugadoresAT, puntosAT);
                    tirador = leer.nextInt();
                }

                if (equipoAT.equals(equipo1)){
                    mejorJugador1[tirador-1]++;
                }else{
                    mejorJugador2[tirador-1]++;
                }

                System.out.println(equipoDF + ", elige a un buen portero (Inserta 1, 2 o 3)");
                mostrarEquipos(equipoDF, jugadoresDF, puntosDF);
                portero = leer.nextInt();

                while (portero == defensor) {
                    System.out.println(jugadoresDF[defensor - 1] + ", no seas agonías. Deja que otro jugador se ponga en la portería");
                    mostrarEquipos(equipoDF, jugadoresDF, puntosDF);
                    portero = leer.nextInt();
                }

                if (equipoDF.equals(equipo1)){
                    mejorJugador1[portero-1]++;
                }else{
                    mejorJugador2[portero-1]++;
                }

                if (calcularJugada(puntosDF, puntosAT, 2, 3, portero - 1, tirador - 1) == false){ //En este caso necesito las posiciones de TR y PA (2, 3)
                    System.out.println("PARADÓN DE " + jugadoresDF[portero - 1]);
                    estadisticas[2]++;
                    jugarPartido(equipoDF, jugadoresDF, puntosDF, equipoAT, jugadoresAT, puntosAT);

                } else {
                    System.out.println("GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOL DE " + jugadoresAT[tirador - 1]);
                    if (equipoAT.equals(equipo1)) { //Como no se sabe si el que marca es el equipo1 o 2, lo busco para subirle su marcador
                        marcador[0]++;
                    } else {
                        marcador[1]++;
                    }
                    jugarPartido(equipoDF, jugadoresDF, puntosDF, equipoAT, jugadoresAT, puntosAT);
                }
            }
        }
    }

    public static void tandaPenaltis (){
        Scanner leer = new Scanner (System.in);
        int porteroP1, porteroP2;

        System.out.println("Bienvenidos a la tanda de penaltis de MINI FIFA STREET");
        System.out.println(equipo1 + ", elige a tu portero");
        mostrarEquipos(equipo1, jugadores1, puntos1);
        porteroP1 = leer.nextInt();

        System.out.println(equipo2 + ", elige a tu portero");
        mostrarEquipos(equipo2, jugadores2, puntos2);
        porteroP2 = leer.nextInt();

        System.out.println("¡" + jugadores1[porteroP1-1] + " y " + jugadores2[porteroP2-1] + " serán los porteros durante la tanda de penaltis!");
        System.out.println("Los penaltis se lanzan de forma alternativa y por orden de jugadores. Por tanto, se juega a 3 rondas");
        System.out.println("Si al final de la tanda estáis empate, repetiremos lanzamientos hasta que solo haya un ganador");
        System.out.println("A JUGAR");


        for (int i = 0; i < jugadores1.length; i++) { //Recorremos el equipo para que tiren uno por uno
            System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
            if (turnoPenalti(equipo1, jugadores1, jugadores2, puntos1, puntos2, porteroP2-1, i) == true){ //turno 1º equipo
                marcador[0]++;
            }
            System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
            if (turnoPenalti(equipo2, jugadores2, jugadores1, puntos2, puntos1, porteroP1-1, i) == true){ //turno 2º equipo
                marcador[1]++;
            }
        }

        while (marcador[0] == marcador[1]){ //este while va repitiendo 2 tiros más cada vez que haya empate

            if (desempate (porteroP2, porteroP1) == false) {
                desempate(porteroP1, porteroP2);
            }else{
                mostrarGanador();
                break;
            }

        }

    mostrarGanador();

    }

    public static boolean validarNombre (String nombreJugador){

        if (!nombreJugador.matches("[a-zA-Z0-9]*")){ //Comprobamos que solo tenga letras y números
            return false;
        }else{
            return true;
        }
    }

    public static boolean calcularJugada (int [][] puntosAT, int[][] puntosDF, int AT, int DF, int defensor, int atacante) {
        /* En esta función calcularemos si gana el ataque o la defensa y si es gol o parada
        // Aunque ponga ataque y defensa, la reutilizo para tiro y parada (tiro = at, parada = DF)
        Depende de quién llame a la función, le pasaré parámetros AT, DF o TR, PA*/
        double ataque, defensa;

        defensa = puntosDF[defensor][DF] * Math.random() * (1 - 1.3) + 1.3;
        ataque = puntosAT[atacante][AT] * Math.random() * (0.8 - 1.2) + 1.2;

        if (defensa >= ataque){
            return false;
        }else{
            return true;
        }
    }

    public static boolean turnoPenalti (String equipo, String [] jugadoresAT, String [] jugadoresDF, int [][] puntosAT, int[][] puntosDF, int portero, int tirador){

        System.out.println("Turno de " + equipo + ". Lanza " + jugadoresAT[tirador]);
        if (calcularJugada(puntosAT, puntosDF, 2, 3, portero, tirador) == false){ //reutilizamos la función del partido (es el mismo cálculo)
            System.out.println("¡Ooooooh, " + jugadoresDF[portero] + " consigue parar el penalti!");
            return false;

        }else{
            System.out.println("¡GOOOOOOOOOOOOL DE " + jugadoresAT[tirador] + "!");
            return true;
        }
    }

    public static boolean desempate (int porteroP2, int porteroP1) {

        System.out.println("Empate, dos tiros más");

        for (int i = 0; i < jugadores1.length; i++) {
            if (turnoPenalti(equipo1, jugadores1, jugadores2, puntos1, puntos2, porteroP2 - 1, i) == true){
                marcador[0]++;
                System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
                if (turnoPenalti(equipo2, jugadores2, jugadores1, puntos2, puntos1, porteroP1 - 1, i) == false){ //si equipo 1 marca y equipo 2 no: ya desempata
                    System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
                    return true;

                }else { //Si eq2 no marca, otra vez hay que desempatar
                    marcador[1]++;
                    System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
                    System.out.println(equipo2 + " vuelve a empatar, dos tiros más");
                    return false;
                }

            }else {
                System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
                if (turnoPenalti(equipo2, jugadores2, jugadores1, puntos2, puntos1, porteroP1 - 1, i) == true) { //Si eq1 no marca, y eq1 sí, ya desempata
                    marcador[1]++;
                    System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
                    return true;

                } else { //Si eq1 y eq2 no marcan, otra vez hay que desempatar
                    marcador[1]++;
                    System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
                    System.out.println(equipo2 + " vuelve a empatar, dos tiros más");
                    return false;
                }
            }
        }
        return desempate(porteroP2, porteroP1);
    }

    public static void mostrarGanador(){

        if (marcador[0] > marcador[1]){
            System.out.println("¡" + equipo1 + " gana la tanda de penaltis!");
            System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);

        }else{
            System.out.println("¡" + equipo2 + " gana la tanda de penaltis!");
            System.out.println(equipo1 + " " + marcador[0] + "-" + marcador[1] + " " + equipo2);
        }
    }

    public static void mostrarEstadisticas (){

        int mejor1 = 0;
        int mejor2 = 0;
        int i1 = 0;
        int i2 = 0;

        for (int i=0; i<estadisticas.length; i++){
            System.out.println(estadisticasString[i] + estadisticas[i]);
        }

        for (int i = 0; i<mejorJugador1.length; i++) {
            if (mejorJugador1[i] > mejor1) {
                mejor1 = mejorJugador1[i];
                i1 = i;
            }
        }

        for (int i = 0; i<mejorJugador2.length; i++){
            if (mejorJugador2[i] > mejor2){
                mejor2 = mejorJugador2[i];
                i2 = i;
            }
        }

        System.out.println("El jugador más usado de " + equipo1 + " ha sido " + jugadores1[i1] + ". Y el de " + equipo2 + " ha sido " + jugadores2[i2]);

    }
}
