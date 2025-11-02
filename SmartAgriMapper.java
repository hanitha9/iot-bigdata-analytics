public class SmartAgriMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text word = new Text();
    private IntWritable one = new IntWritable(1);

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        // Process the input line and split it accordingly.
        String[] fields = line.split(",");
        if (fields.length > 1) {
            word.set(fields[0]);  // Example: Use the first field as the key.
            context.write(word, one);
        }
    }
}
