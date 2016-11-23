package com.algernoon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@SuppressWarnings("unchecked")
public class JsonUtil {
	
	private static ObjectMapper objectMapper = null;

	static {
		objectMapper = new ObjectMapper();
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
		//objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		//设置序列化是默认值和null不进行序列化。
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		//objectMapper.setSerializationInclusion(Include.NON_DEFAULT);
		objectMapper.setDateFormat(new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"));
	}
	/**
	 * 直接序列化不过滤字段
	 * @param value 待序列化的实体对象
	 * @return
	 */
	public static String jsonSerialization(Object value) {
		
		try {
			return objectMapper.writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException("解析对象错误");  
		}
	}
	/**
	 * 设置过滤字段的序列化
	 * @param value 待序列化的实体对象
	 * @param filterName 过滤器名
	 * @param properties 过滤的实体属性
	 * @return
	 */
	public static String jsonSerialization(Object value, String filterName, String...properties) {
		
		try {
			return filter(filterName,properties).writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException("解析对象错误");  
		}
	}
	
	public static <T> T jsonDeserialization(String json, Class<?> clazz){
		try {
			return  (T)objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException("反序列化对象错误");  
		}
	}
	
	private static ObjectMapper filter(String filterName, String...properties){
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter
				(filterName, SimpleBeanPropertyFilter.serializeAllExcept(properties));
		objectMapper.setFilters(filterProvider);
		
		return objectMapper;
	}
	
	/**
	 * 复杂类型json反序列化 ,比如List,Map
	 * @param json
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static <T> T jsonDeserialization(String json, Class<?> collectionClass, Class<?>... elementClasses){
		try {
			JavaType javaType = getJavaType(collectionClass,elementClasses);
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			throw new RuntimeException("反序列化对象错误");
		}
	}
	
	private static JavaType getJavaType(Class<?> collectionClass, Class<?>... elementClasses){
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
	public static void main(String[] args) throws ParseException {
/*		String privateRoleList = "1,2,3";
		List<String> tempList = Arrays.asList(privateRoleList.split(","));
		List<String> menuList = new ArrayList<String>(tempList);
		PrintLogUtils.println(menuList.remove("0"));*/
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("dArg1", 1);
		paramMap.put("dArg2", "007");
		paramMap.put("dArg3", "高富帅");
		Map<Integer,Map<String,Object>> map = new HashMap<Integer,Map<String,Object>>();
		map.put(1, paramMap);
		map.put(2, paramMap);
		String json = jsonSerialization(map);
		System.out.println(json);
		map = jsonDeserialization(json, HashMap.class, Integer.class,Map.class);
		System.out.println(map.get(1).get("dArg2"));
		//PrintLogUtils.println(json);
		/*String json = jsonSerialization(list);
		List<Map<String,Object>> list2 = jsonDeserialization(json, ArrayList.class, Demo.class);
		PrintLogUtils.println(list2.size());*/
	}
}
