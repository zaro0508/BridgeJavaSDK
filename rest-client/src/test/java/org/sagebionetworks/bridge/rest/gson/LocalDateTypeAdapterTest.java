package org.sagebionetworks.bridge.rest.gson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import org.sagebionetworks.bridge.rest.RestUtils;
import org.sagebionetworks.bridge.rest.model.ConsentSignature;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

@RunWith(PowerMockRunner.class)
public class LocalDateTypeAdapterTest {
    
    private static final String DATE_STRING = "2015-10-13";
    
    @Mock
    JsonReader reader;
    
    @Mock
    JsonWriter writer;
    
    @Test
    public void itWorks() throws Exception {
        LocalDateTypeAdapter adapter = new LocalDateTypeAdapter();
        
        doReturn(DATE_STRING).when(reader).nextString();
        
        LocalDate result = adapter.read(reader);
        assertEquals(DATE_STRING, result.toString());
        
        adapter.write(writer, LocalDate.parse(DATE_STRING));
        
        verify(writer).value(DATE_STRING);
    }
    
    @Test
    public void worksWithGson() {
        LocalDate result = RestUtils.GSON.fromJson("\""+DATE_STRING+"\"", LocalDate.class);
        assertEquals(DATE_STRING, result.toString());
    }
    
    @Test
    public void handlesNull() {
        DateTime result = RestUtils.GSON.fromJson("", DateTime.class);
        assertNull(result);
        
        result = RestUtils.GSON.fromJson((String)null, DateTime.class);
        assertNull(result);
    }
    
    @Test
    public void handlesNullField() {
        ConsentSignature signature = new ConsentSignature();
        assertEquals("{}", RestUtils.GSON.toJson(signature));
        
    }
}

