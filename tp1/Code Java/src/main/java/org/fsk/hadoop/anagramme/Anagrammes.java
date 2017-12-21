package org.fsk.hadoop.anagramme;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * Created by Jairo Duarte on 21/12/2017.
 *
 * Note classe Driver (contient le main du programme Hadoop).
 */
public class Anagrammes {

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
        // Permet à Hadoop de lire ses arguments génériques, récupère les arguments restants dans ourArgs.
        String[] ourArgs=new GenericOptionsParser(conf, args).getRemainingArgs();
        // Obtient un nouvel objet Job: une tâche Hadoop. On fourni la configuration Hadoop ainsi qu'une description
        // textuelle de la tâche.
        Job job=Job.getInstance(conf, "Anagrammes v1.0");

        // Défini les classes driver, map et reduce.
        job.setJarByClass(Anagrammes.class);
        job.setMapperClass(AnagrammesMap.class);
        job.setReducerClass(AnagrammesReduce.class);

        // Défini types clefs/valeurs de notre programme Hadoop.
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Défini les fichiers d'entrée du programme et le répertoire des résultats.
        // On se sert du premier et du deuxième argument restants pour permettre à l'utilisateur de les spécifier
        // lors de l'exécution.
        FileInputFormat.addInputPath(job, new Path(ourArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(ourArgs[1]));

        // On lance la tâche Hadoop. Si elle s'est effectuée correctement, on renvoie 0. Sinon, on renvoie -1.
        if(job.waitForCompletion(true))
            System.exit(0);
        System.exit(-1);

    }
}