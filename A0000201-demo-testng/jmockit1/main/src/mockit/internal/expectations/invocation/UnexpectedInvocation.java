/*
 * Copyright (c) 2006 JMockit developers
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.internal.expectations.invocation;

import javax.annotation.*;

/**
 * Thrown to indicate that one or more unexpected invocations occurred during the test.
 */
public final class UnexpectedInvocation extends Error
{
   public UnexpectedInvocation(@Nonnull String detailMessage) { super(detailMessage); }

   @Override
   public String toString() { return getMessage(); }
}
