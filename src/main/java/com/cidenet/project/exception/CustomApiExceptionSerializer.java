package com.cidenet.project.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomApiExceptionSerializer extends StdSerializer<ApiException> {

  public CustomApiExceptionSerializer() {
    super(ApiException.class);
  }

  public void serialize(ApiException apiException, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeRaw(apiException.toString());
    jgen.close();
  }
}
