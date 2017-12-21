package org.fsk.hadoop.anagramme;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Jairo Duarte on 21/12/2017.
 *
 * Class Reduce
 */
public class AnagrammesReduce extends Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        Iterator<Text> iterator = values.iterator();
        String mots="";
        while (iterator.hasNext())
            mots +=", "+iterator.next();

        context.write(new Text(mots),new Text("Ces mots sont des Anagrammes."));
    }
}
