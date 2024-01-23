import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        // Create SparkConf object
        SparkConf conf = new SparkConf()
                .setAppName("WordCount")
                .setMaster("local");

        // Create JavaSparkContext object
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Read Cs.txt and Novel.txt files
        JavaRDD<String> csLines = sc.textFile("D:\\ebook\\ebook_back\\se3353_25_spark_java\\se3353_25_spark_java\\src\\main\\resources\\CS.txt");
        JavaRDD<String> novelLines = sc.textFile("D:\\ebook\\ebook_back\\se3353_25_spark_java\\se3353_25_spark_java\\src\\main\\resources\\Children.txt");

        JavaRDD<String> FamousLines = sc.textFile("D:\\ebook\\ebook_back\\se3353_25_spark_java\\se3353_25_spark_java\\src\\main\\resources\\Famous.txt");
        JavaRDD<String> FictionLines = sc.textFile("D:\\ebook\\ebook_back\\se3353_25_spark_java\\se3353_25_spark_java\\src\\main\\resources\\Fiction.txt");
        // Combine both RDDs
        JavaRDD<String> allLines = csLines.union(novelLines.union(FamousLines.union(FictionLines)));
        JavaRDD<String> words = allLines
                .flatMap(line -> Arrays.asList(line.toLowerCase().replaceAll("[.,!\"():]", " ").split(" ")).iterator());
        JavaRDD<String> filteredWords = words.filter(word -> !word.isEmpty() && (word.equals("java") || word.equals("c++") || word.equals("literature") || word.equals("programmer")));
        JavaPairRDD<String, Integer> wordCounts = filteredWords.mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((x, y) -> x + y);
        Map<String, Integer> wordCountsMap = wordCounts.collectAsMap();

        // Output results
        for (Map.Entry<String, Integer> entry : wordCountsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Save results to output.txt
        wordCounts.coalesce(1).saveAsTextFile("D:\\ebook\\ebook_back\\se3353_25_spark_java\\se3353_25_spark_java\\src\\main\\resources\\output");

        // Close JavaSparkContext object
        sc.close();
    }
}
