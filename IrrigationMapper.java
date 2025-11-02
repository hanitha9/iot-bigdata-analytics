import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class IrrigationMapper extends Mapper<Object, Text, Text, Text> {
    private Text sensorID = new Text();
    private Text sensorData = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Split the line by commas
        String[] parts = value.toString().split(",");

        // Skip header or invalid rows
        if (parts.length != 8 || parts[0].equalsIgnoreCase("timestamp")) return;

        sensorID.set(parts[1]);  // Sensor ID
        String data = parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6]; // Relevant columns
        sensorData.set(data);

        // Emit sensor ID and sensor data
        context.write(sensorID, sensorData);
    }
}

