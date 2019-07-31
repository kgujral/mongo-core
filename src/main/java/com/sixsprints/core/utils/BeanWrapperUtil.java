package com.sixsprints.core.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;

public class BeanWrapperUtil {

  public static void copyProperties(Object src, Object target, Iterable<String> props) {

    BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
    BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(target);

    props.forEach(p -> trgWrap.setPropertyValue(p, srcWrap.getPropertyValue(p)));

  }

  public static Object getValue(Object obj, String prop) {
    BeanWrapper wrap = PropertyAccessorFactory.forBeanPropertyAccess(obj);
    return wrap.getPropertyValue(prop);
  }

  public static void copyNonNullProperties(Object src, Object target) {
    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
  }

  private static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<String>();
    for (java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null || srcValue.toString().isEmpty())
        emptyNames.add(pd.getName());
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }

}
