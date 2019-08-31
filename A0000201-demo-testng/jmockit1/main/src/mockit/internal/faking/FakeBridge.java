/*
 * Copyright (c) 2006 JMockit developers
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.internal.faking;

import java.lang.reflect.*;
import javax.annotation.*;

import mockit.internal.*;
import mockit.internal.state.*;

public final class FakeBridge extends ClassLoadingBridge
{
   @Nonnull public static final ClassLoadingBridge MB = new FakeBridge();

   private FakeBridge() { super("$FB"); }

   @Nonnull @Override
   public Object invoke(@Nullable Object faked, Method method, @Nonnull Object[] args) {
      if (TestRun.isInsideNoMockingZone()) {
         return false;
      }

      TestRun.enterNoMockingZone();

      try {
         String fakeClassDesc = (String) args[0];

         if (notToBeMocked(faked, fakeClassDesc)) {
            return false;
         }

         Integer fakeStateIndex = (Integer) args[1];
         return TestRun.updateFakeState(fakeClassDesc, fakeStateIndex);
      }
      finally {
         TestRun.exitNoMockingZone();
      }
   }
}
