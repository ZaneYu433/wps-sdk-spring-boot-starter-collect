package net.thewesthill.wps;

import org.springframework.beans.BeanUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public final class CommonUtil {

    public static String getRFC1123Date() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(new Date());
    }

    public static MultiValueMap<String, Object> pojoCover(Object pojo) {
        return PojoToQueryParamUtils.covert(pojo);
    }
}

final class PojoToQueryParamUtils {

    public static MultiValueMap<String, Object> covert(Object pojo) {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        if (pojo == null) {
            return multiValueMap;
        }

        Class<?> pojoClass = pojo.getClass();
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(pojoClass);

        for (PropertyDescriptor pd : propertyDescriptors) {
            String fieldName = pd.getName();
            Method getterMethod = pd.getReadMethod();

            if ("class".equals(fieldName) || getterMethod == null) {
                continue;
            }

            try {
                Object fieldValue = getterMethod.invoke(pojo);

                if (isNullOrEmpty(fieldValue)) {
                    continue;
                }

                String paramValue;
                if (fieldValue.getClass().isArray()) {
                    List<?> arrayList = Arrays.asList((Object[]) fieldValue);
                    paramValue = String.join(",", arrayList.stream().map(Object::toString).toArray(String[]::new));
                } else {
                    paramValue = fieldValue.toString();
                }
                multiValueMap.add(fieldName, paramValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return multiValueMap;
    }

    private static boolean isNullOrEmpty(Object value) {

        if (value == null) {
            return true;
        }

        if (value instanceof String) {
            return ((String) value).trim().isEmpty();
        }

        if (value.getClass().isArray()) {
            return ((Object[]) value).length == 0;
        }

        return false;
    }
}