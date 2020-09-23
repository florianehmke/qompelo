package com.github.florianehmke.qompelo.testing;

import javax.transaction.UserTransaction;

import static com.github.florianehmke.qompelo.domain.Player.create;
import static com.github.florianehmke.qompelo.testing.util.Random.EASY_RANDOM;
import static com.github.florianehmke.qompelo.testing.util.Transaction.doInTransaction;

public class Player {

  public static String createTestUser(UserTransaction transaction) {
    var userName = EASY_RANDOM.nextObject(String.class);
    var passWord = EASY_RANDOM.nextObject(String.class);

    doInTransaction(transaction, () -> create(userName, passWord));

    return userName;
  }
}
