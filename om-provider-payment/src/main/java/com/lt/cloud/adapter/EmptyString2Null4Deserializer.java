package com.lt.cloud.adapter;

import java.lang.reflect.Type;

import org.springframework.util.StringUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
public class EmptyString2Null4Deserializer<T> implements JsonDeserializer<T>{

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if (StringUtils.isEmpty(json.getAsString())) {
			if ("class java.lang.Double".equals(typeOfT.toString())) {
				return (T) Double.valueOf("0.0");
			}
			return null;
		}
		switch (typeOfT.toString()) {
		case "class java.lang.Long":
			
			return (T) Long.valueOf(json.getAsString());
		case "class java.lang.Integer":
			
			return (T) Integer.valueOf(json.getAsString());
		case "class java.lang.Double":
			
			return (T) Double.valueOf(json.getAsString());	
		default:
			break;
		}
		return (T) json.getAsJsonObject();
        
	}


}

