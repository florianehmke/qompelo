package com.github.florianehmke.qompelo.testing.util;

import javax.transaction.UserTransaction;

public class Transaction {

  public static void doInTransaction(UserTransaction transaction, Runnable callback) {
    try {
      transaction.begin();
      callback.run();
      transaction.commit();
    } catch (Exception e) {
      System.out.println("Catastrophic Failure: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
