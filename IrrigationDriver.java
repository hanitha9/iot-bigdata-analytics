import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class IrrigationDriver {
    public static void main(String[] args) throws Exception {
        // Check if input and output paths are provided
        if (args.length != 2) {
            System.err.println("Usage: IrrigationDriver <input path> <output path>");
            System.exit(-1);
        }

        // Create configuration and set up the job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Irrigation Analysis");

        // Set driver, mapper, and reducer classes
        job.setJarByClass(IrrigationDriver.class);
        job.setMapperClass(IrrigationMapper.class);
        job.setReducerClass(IrrigationReducer.class);

        // Set output key and value types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Submit the job and wait for completion
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

