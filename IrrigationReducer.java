import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class IrrigationReducer extends Reducer<Text, Text, Text, Text> {
    private Text result = new Text();

    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // Assume threshold for soil moisture is 30%
        double moistureThreshold = 30.0;

        boolean irrigationNeeded = false;

        for (Text val : values) {
            // Each value contains: Soil Moisture, Temperature, Humidity, pH Level, Light Intensity, Irrigation Status
            String[] sensorValues = val.toString().split(",");
            double soilMoisture = Double.parseDouble(sensorValues[0]);

            // Check if soil moisture is below the threshold, irrigation needed
            if (soilMoisture < moistureThreshold) {
                irrigationNeeded = true;
            }
        }

        // Emit the result: Sensor ID and whether irrigation is needed
        result.set(irrigationNeeded ? "Irrigation Needed" : "No Irrigation Needed");
        context.write(key, result);
    }
}

