package com.kbhit.orangebox.trading.dbsetup;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

public class DbSetupUtils {

    public static final Operation DISABLE_FOREIGN_KEY_CHECKS = Operations.sql("SET FOREIGN_KEY_CHECKS = 0");

    public static final Operation ENABLE_FOREIGN_KEY_CHECKS = Operations.sql("SET FOREIGN_KEY_CHECKS = 1");

}
