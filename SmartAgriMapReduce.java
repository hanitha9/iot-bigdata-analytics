import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SmartAgriMapReduce {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Smart Agriculture MapReduce");

        job.setJarByClass(SmartAgriMapReduce.class);
        job.setMapperClass(SmartAgriMapper.class);
        job.setReducerClass(SmartAgriReducer.class);

        // Set Input Path (dataset file on HDFS)
        FileInputFormat.addInputPath(job, new Path("/user/hanitha/smart_agriculture/smart_agriculture_dataset.csv"));

        // Set Output Path (results folder on HDFS)
        FileOutputFormat.setOutputPath(job, new Path("/user/hanitha/smart_agriculture/output"));

        // Submit the job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
