/*
 * Copyright (c) 2006 JMockit developers
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.internal.injection;

import java.lang.reflect.*;
import java.util.*;
import javax.annotation.*;

import static mockit.internal.util.Utilities.getClassType;

final class MultiValuedProvider extends InjectionProvider
{
   @Nonnull private final List<InjectionProvider> individualProviders;

   MultiValuedProvider(@Nonnull Type elementType) {
      super(elementType, "");
      individualProviders = new ArrayList<>();
   }

   void addInjectable(@Nonnull InjectionProvider provider) {
      individualProviders.add(provider);
   }

   @Nonnull @Override
   public Class<?> getClassOfDeclaredType() { return getClassType(declaredType); }

   @Nonnull @Override
   public Object getValue(@Nullable Object owner) {
      List<Object> values = new ArrayList<>(individualProviders.size());

      for (InjectionProvider provider : individualProviders) {
         Object value = provider.getValue(owner);
         values.add(value);
      }

      return values;
   }
}
