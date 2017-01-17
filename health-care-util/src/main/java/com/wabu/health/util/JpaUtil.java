package com.wabu.health.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class JpaUtil {

	/**
	 * 解析排序字段
	 * @param sort 例如 "id,-createdAt"
	 * @return 
	 */
	public static List<Order> pareseSort(String sort) {
		
		String[] sortArray = sort.split(",");
		List<org.springframework.data.domain.Sort.Order> orders = new ArrayList<>();
		for (String sortField : sortArray) {
			if (sortField.startsWith("-")) {//以"-"开头表示降序
				String sortProp = sortField.substring(1);//截取排序字段名
				orders.add(new org.springframework.data.domain.Sort.Order(Direction.DESC, sortProp));
			} else {
				orders.add(new org.springframework.data.domain.Sort.Order(sortField));
			}
		}
		
		return orders;
	}
}
