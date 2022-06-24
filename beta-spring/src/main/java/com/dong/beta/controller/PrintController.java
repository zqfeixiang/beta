package com.dong.beta.controller;

import com.dong.beta.controller.domain.model.City;
import com.dong.beta.controller.domain.model.Province;
import com.dong.beta.controller.domain.model.ZipCode;
import com.dong.beta.controller.domain.model.Address;
import com.dong.beta.controller.domain.model.Car;
import com.dong.beta.controller.domain.model.Person;
import com.dong.beta.controller.domain.model.PersonDO;
import com.dong.beta.controller.domain.model.House;
import com.dong.beta.controller.vo.ResponseModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.oracle.tools.packager.Log;

import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dzq
 * @date 2022/6/22 15:46
 */
@RestController
@Slf4j
public class PrintController {

    private static final String PACKAGE_NAME = "com.dong.beta.controller.domain.model";
    private String key;
    private Object value;

    @ApiOperation("打印类指定属性的value")
    @GetMapping("/printModelProperty")
    public ResponseModel<Map<String, Object>> printModelProperty(@ApiParam(value = "House") @RequestParam String concept,
                                                    @ApiParam("blockCode") @RequestParam String attribute) throws Exception {
        Map<String, Object> map = new HashMap<>();
        PersonDO personDO = buildPersonDO();
        Person person = personDO.getPerson();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(personDO);
        System.out.println(s);
        Map<String, Object> objectMap = getAttributeValue(person, concept, attribute);
        map.put("attribute", concept + "." + attribute);
        map.put("value", objectMap.get("value"));
        return ResponseModel.successResponse(map);
    }

    @ApiOperation("打印json指定key的value")
    @GetMapping("/printJsonKey")
    public ResponseModel<Map<String, Object>> printJsonKey(@ApiParam(value = "House") @RequestParam String concept,
                                                           @ApiParam("blockCode") @RequestParam String attribute) throws Exception {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        PersonDO personDO = buildPersonDO();
        String personDOStr = mapper.writeValueAsString(personDO);
        JsonNode jsonNode = mapper.readTree(personDOStr);
        StringBuilder builder = new StringBuilder();
        key = concept + "." + attribute;
        traverse(jsonNode, builder);
        map.put("attribute", concept + "." + attribute);
        map.put("value", value);
        return ResponseModel.successResponse(map);
    }

    private void traverse(JsonNode jsonNode, StringBuilder builder) {
        if (jsonNode.getNodeType() == JsonNodeType.OBJECT) {
            traverseObject(jsonNode, builder);
        } else if (jsonNode.getNodeType() == JsonNodeType.ARRAY) {
            traverseObject(jsonNode.get(0), builder);
        }
    }

    private void traverseObject(JsonNode jsonNode, StringBuilder builder) {
        jsonNode.fieldNames().forEachRemaining(fieldName -> {
            StringBuilder builder1 = new StringBuilder(builder);
            JsonNode childNode = jsonNode.get(fieldName);
            printNode(childNode, fieldName, builder1);
            if (traversable(childNode)) {
                traverse(childNode, builder1);
            }
        });
    }

    private boolean traversable(JsonNode node) {
        return node.getNodeType() == JsonNodeType.OBJECT || node.getNodeType() == JsonNodeType.ARRAY;
    }

    private void printNode(JsonNode node, String fieldName, StringBuilder builder) {
        if (traversable(node)) {
            builder.append(fieldName).append(".");
        }else {
            builder.append(fieldName);
            if (builder.toString().toLowerCase().contains(key.toLowerCase())) {
                getValue(node);
            }
        }
        log.info("{}", builder);
    }

    private void getValue(JsonNode node) {
        if (node.getNodeType() == JsonNodeType.STRING) {
            value = node.textValue();
        }else if(node.getNodeType() == JsonNodeType.NUMBER) {
            value = node.numberValue();
        }
    }

    private Map<String, Object> getAttributeValue(Object obj, String concept, String attribute) throws IllegalAccessException {
        Map<String, Object> resultMap = new HashMap<>();
        Object proValue = null;
        boolean loopFlag = true;
        resultMap.put("loopFlag", loopFlag);
        resultMap.put("value", proValue);

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String proName = field.getName();
            if (concept.equalsIgnoreCase(clazz.getSimpleName()) && attribute.equalsIgnoreCase(proName)) {
                proValue = field.get(obj);
                resultMap.put("loopFlag", false);
                resultMap.put("value", proValue);
                return resultMap;
            } else if (List.class.isAssignableFrom(field.getType())) {
                Map<String, Object> objMap = getAttributeValue(((List) ReflectionUtils.getField(field, obj)).get(0), concept, attribute);
                if (!(boolean) objMap.get("loopFlag")) {
                    return objMap;
                }
            } else if (field.getType().getPackage().getName().startsWith(PACKAGE_NAME)) {
                Map<String, Object> objMap = getAttributeValue(ReflectionUtils.getField(field, obj), concept, attribute);
                if (!(boolean) objMap.get("loopFlag")) {
                    return objMap;
                }
            }
        }
        return resultMap;
    }

    private PersonDO buildPersonDO() {
        PersonDO personDO = new PersonDO();
        Person person = new Person();
        List<House> houses = new ArrayList<>();
        House house = new House();
        house.setBlockCode("Block 18, Room 101");
        houses.add(house);
        person.setHouse(houses);

        Address address = new Address();
        address.setAddress("ShangHai");
        address.setStatus("active");
        List<ZipCode> zipCodes = new ArrayList<>();
        ZipCode zipCode1 = new ZipCode();
        zipCode1.setCreateDate(new Date());
        zipCode1.setCode("100200");
        List<Province> provinces = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        City city = new City();
        city.setName("SuZhou");
        city.setCityCode(010);
        cities.add(city);
        Province province = new Province();
        province.setCityList(cities);
        province.setName("JiangSu");
        provinces.add(province);
        zipCode1.setProvinceList(provinces);
        zipCodes.add(zipCode1);
        address.setZipCodes(zipCodes);
        person.setAddress(address);

        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        car.setSeqNo(88888);
        cars.add(car);
        person.setCar(cars);

        personDO.setVersion(1);
        personDO.setPerson(person);
        return personDO;
    }
}
