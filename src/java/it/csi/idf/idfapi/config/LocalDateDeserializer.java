package it.csi.idf.idfapi.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    	DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(parser.readValueAs(String.class), dtFormatter);
    }
}
