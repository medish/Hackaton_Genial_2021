import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileConstraintGenerator {

    //ID max de l'enseignant
    public static int MAX_TEACHER_ID = 8;
    //ID max de la salle
    public static int MAX_ROOM_ID = 22;
    //Nombre de contraintes à génerer par fichier
    public static int NB_CONSTRAINTS = 30;
    public static String[] minutes = {"00","15","30","45"};
    //TODO GERER les autres selecteurs que ens:id
    public String [] selecteurs = {"ens:id:","room:id:"};
    public static boolean correct = true;

    public static String [] liste_cours = {"Algorithmique","Base de données avancée","Génie Logiciel",
            "Droit de l informatique","Anglais","Composants mobiles",
            "Langage a objets avancé","Protocoles réseaux",
            "Sécurité","Initiation a la programmation","Internet et outils",
            "Concepts informatiques","Programmation orientée objets",
            "Langage C","Outils logiques","Mathématiques",
            "Mathématiques discrètes","Compléments POO","Programmation fonctionelle",
            "Base de données"};


    public static void create_time_constraints() throws IOException {
        String output_file_name = "output_time_constraints.csv";
        File foutput = new File("src/output/"+output_file_name);
        int version = 1;
        while(foutput.exists()){
            version++;
            foutput = new File("src/output/outpout_time_constraints"+version+".csv");
        }
        boolean created = foutput.createNewFile();
        if (created)
            System.out.println("Création de "+foutput.getName()+" dans le fichier output");

        FileWriter fw = new FileWriter(foutput.getPath());
        for (int i = 0;i< NB_CONSTRAINTS;i++){
            String new_constraints = "";
            Random r = new Random();

            //Tirage de l'enseignant
            int result = r.nextInt(MAX_TEACHER_ID-1) + 2;
            //Tirage du jour de la semaine
            int result_day = r.nextInt(5) + 1;
            //Souhaite ou ne souhaite pas
            int result_wants = r.nextInt(2);
            boolean wants = result_wants == 1;

            //Heure de début de créeanu
            int result_hour_start = r.nextInt(23) ;
            int result_minutes_start = r.nextInt(minutes.length) ;

            //Heure de fin de créneau
            int result_hour_end = r.nextInt(24) ;
            int result_minutes_end = r.nextInt(minutes.length) ;

            if (correct){
                while (result_hour_start>result_hour_end)
                    result_hour_end = r.nextInt(24) ;
            }

            //Tirage de l'id de la salle
            int room_selected = r.nextInt(MAX_ROOM_ID)+1 ;
            //poids de la contrainte
            int poids_contraints = r.nextInt(101);


            new_constraints = "ens:id:"+result+";"+wants+";"+result_day+";"+result_hour_start+":"+
                    minutes[result_minutes_start]+":00;"+result_hour_end+":"+minutes[result_minutes_end]+":00;"
                    +"room:id:"+room_selected+";"+poids_contraints;


            fw.write(new_constraints);
            fw.write("\n");

        }
        fw.close();
    }


    //Creation d'un fichier csv de contrainte de precedence
    public static void create_precedence_constraints() throws IOException {
        String output_file_name = "output_precedence_constraints.csv";
        File foutput = new File("src/output/"+output_file_name);
        int version = 1;
        while(foutput.exists()){
            version++;
            foutput = new File("src/output/outpout_precedence_constraints"+version+".csv");
        }
        boolean created = foutput.createNewFile();
        if (created)
            System.out.println("Création de "+foutput.getName()+" dans le fichier output");
        FileWriter fw = new FileWriter(foutput.getPath());
        for (int i = 0;i< NB_CONSTRAINTS;i++){
            String new_constraints = "";
            Random r = new Random();

            //Tirage du cours 1
            int cours = r.nextInt(FileConstraintGenerator.liste_cours.length);
            //Tirage du jour de la semaine
            int result_day = r.nextInt(2);
            String precedence="before";
            if (result_day==1)
                precedence="after";

            //Souhaite ou ne souhaite pas
            int result_wants = r.nextInt(2);
            boolean wants = result_wants == 1;

            //Souhaite ou ne souhaite pas (strictement)
            int strict = r.nextInt(2);
            boolean wants_strict = strict == 1;

            //Deuxieme cours de la contrainte
            int cours2 = r.nextInt(FileConstraintGenerator.liste_cours.length) ;


            //Ne pas avoir 2 fois le même cours dans la contraintes
            if (correct){
                while (cours2==cours)
                    cours2 = r.nextInt(liste_cours.length) ;
            }

            //le poid de la contrainte
            int poid_contraints = r.nextInt(101);

            //ligne à ecrire
            new_constraints = "cours:nom:"+liste_cours[cours]+";"+wants+";"+precedence+";"
                    +wants_strict+";"+"cours:nom:"+liste_cours[cours2]+";"+poid_contraints;


            fw.write(new_constraints);
            fw.write("\n");

        }
        fw.close();

    }

    //Generateur de Contraintes de temps
    public static void main(String[] args) throws IOException {
        System.out.println("Generation du fichier de contrainte de temps");
        create_time_constraints();
        System.out.println("Generation du fichier de contrainte de précedence");
        create_precedence_constraints();

    }
}
