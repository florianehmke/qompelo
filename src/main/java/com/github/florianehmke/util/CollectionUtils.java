package com.github.florianehmke.util;

import java.util.Collection;

public class CollectionUtils {

  public static <T> T addAndReturn(Collection<T> collection, T item) {
    collection.add(item);
    return item;
  }
}
