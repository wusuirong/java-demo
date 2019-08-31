/*
 * Copyright (c) 2006 JMockit developers
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.internal.faking;

import java.lang.reflect.Type;
import javax.annotation.*;

import mockit.*;
import mockit.asm.classes.*;
import mockit.internal.*;
import mockit.internal.capturing.*;

public final class CaptureOfFakedImplementations extends CaptureOfImplementations<Void>
{
   private final FakeClassSetup fakeClassSetup;

   public CaptureOfFakedImplementations(@Nonnull MockUp<?> fake, @Nonnull Type baseType) {
      fakeClassSetup = new FakeClassSetup(baseType, fake);
   }

   @Nonnull @Override
   protected BaseClassModifier createModifier(@Nullable ClassLoader cl, @Nonnull ClassReader cr, @Nonnull Class<?> baseType, Void typeMetadata) {
      return fakeClassSetup.createClassModifier(cr);
   }

   @Override
   protected void redefineClass(@Nonnull Class<?> realClass, @Nonnull byte[] modifiedClass) {
      fakeClassSetup.applyClassModifications(realClass, modifiedClass);
   }

   public void apply() {
      Class<?> baseType = fakeClassSetup.realClass;

      if (baseType != Object.class) {
         redefineClass(baseType, baseType, null);
      }

      makeSureAllSubtypesAreModified(baseType, false, null);
   }
}
