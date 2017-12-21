package hadoop.tp1.anagrammes;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Jairo Duarte on 21/12/2017.
 *
 * Class Mapper
 */
public class AnagrammesMap extends Mapper<Object, Text, Text, Text> {

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        char[] mot = value.toString().toUpperCase().toCharArray();
        Arrays.sort(mot);

        context.write(new Text(mot.toString()),value);
    }
}
