package mockit.asm.types;

import java.lang.reflect.*;
import javax.annotation.*;

/**
 * A Java field or method type. This class can be used to make it easier to manipulate type and method descriptors.
 */
@SuppressWarnings("ClassReferencesSubclass")
public abstract class JavaType
{
   private static final JavaType[] NO_ARGS = {};

   /**
    * The length of the internal name of this Java type.
    */
   @Nonnegative final int len;

   /**
    * Constructs a Java type.
    *
    * @param len the length of this descriptor.
    */
   JavaType(@Nonnegative int len) { this.len = len; }

   /**
    * Returns the Java type corresponding to the given type descriptor.
    *
    * @param typeDescriptor a field or method type descriptor.
    */
   @Nonnull
   public static JavaType getType(@Nonnull String typeDescriptor) {
      return getType(typeDescriptor.toCharArray(), 0);
   }

   /**
    * Returns the Java types corresponding to the argument types of the given method descriptor.
    */
   @Nonnull
   public static JavaType[] getArgumentTypes(@Nonnull String methodDescriptor) {
      char[] buf = methodDescriptor.toCharArray();
      int off = 1;
      int size = 0;

      while (true) {
         char c = buf[off++];

         if (c == ')') {
            break;
         }
         else if (c == 'L') {
            off = findNextTypeTerminatorCharacter(buf, off);
            size++;
         }
         else if (c != '[') {
            size++;
         }
      }

      return getArgumentTypes(buf, size);
   }

   @Nonnegative
   private static int findNextTypeTerminatorCharacter(@Nonnull char[] desc, @Nonnegative int i) {
      while (desc[i++] != ';') {}
      return i;
   }

   @Nonnull
   private static JavaType[] getArgumentTypes(@Nonnull char[] buf, @Nonnegative int argCount) {
      if (argCount == 0) {
         return NO_ARGS;
      }

      JavaType[] argTypes = new JavaType[argCount];
      int off = 1;

      for (int i = 0; buf[off] != ')'; i++) {
         JavaType argType = getType(buf, off);
         argTypes[i] = argType;
         off += argType.len + (argType instanceof ObjectType ? 2 : 0);
      }

      return argTypes;
   }

   /**
    * Returns the Java type corresponding to the return type of the given method descriptor.
    */
   @Nonnull
   public static JavaType getReturnType(@Nonnull String methodDescriptor) {
      char[] buf = methodDescriptor.toCharArray();
      return getType(buf, methodDescriptor.indexOf(')') + 1);
   }

   /**
    * Computes the size of the arguments and of the return value of a method.
    *
    * @param desc the descriptor of a method.
    * @return the size of the arguments of the method (plus one for the implicit <tt>this</tt> argument),
    * <tt>argSize</tt>, and the size of its return value, <tt>retSize</tt>, packed into a single
    * <tt>int i = (argSize << 2) | retSize</tt> (<tt>argSize</tt> is therefore equal to <tt>i >> 2</tt>, and
    * <tt>retSize</tt> to <tt>i & 0x03</tt>).
    */
   public static int getArgumentsAndReturnSizes(@Nonnull String desc) {
      int argSize = 1;
      int i = 1;

      while (true) {
         char currentChar = desc.charAt(i++);

         if (currentChar == ')') {
            char nextChar = desc.charAt(i);
            return argSize << 2 | (nextChar == 'V' ? 0 : isDoubleSizePrimitiveType(nextChar) ? 2 : 1);
         }
         else if (currentChar == 'L') {
            i = findNextTypeTerminatorCharacter(desc, i);
            argSize++;
         }
         else if (currentChar == '[') {
            i = findStartOfArrayElementType(desc, i);
            char arrayElementType = desc.charAt(i);

            if (isDoubleSizePrimitiveType(arrayElementType)) {
               argSize--;
            }
         }
         else if (isDoubleSizePrimitiveType(currentChar)) {
            argSize += 2;
         }
         else {
            argSize++;
         }
      }
   }

   private static boolean isDoubleSizePrimitiveType(char typeCode) { return typeCode == 'D' || typeCode == 'J'; }

   @Nonnegative
   private static int findNextTypeTerminatorCharacter(@Nonnull String desc, @Nonnegative int i) {
      while (desc.charAt(i++) != ';') {}
      return i;
   }

   @Nonnegative
   private static int findStartOfArrayElementType(@Nonnull String desc, @Nonnegative int i) {
      while (desc.charAt(i) == '[') { i++; }
      return i;
   }

   /**
    * Returns the Java type corresponding to the given type descriptor. For method descriptors, <tt>buf</tt> is supposed
    * to contain nothing more than the descriptor itself.
    *
    * @param buf a buffer containing a type descriptor.
    * @param off the offset of this descriptor in the previous buffer.
    */
   @Nonnull
   static JavaType getType(@Nonnull char[] buf, @Nonnegative int off) {
      PrimitiveType primitiveType = PrimitiveType.getPrimitiveType(buf[off]);

      if (primitiveType != null) {
         return primitiveType;
      }

      return ReferenceType.getReferenceType(buf, off);
   }

   /**
    * Returns the binary name of the class corresponding to this type. This method must not be used on method types.
    */
   @Nonnull
   public abstract String getClassName();

   // ------------------------------------------------------------------------
   // Conversion to type descriptors
   // ------------------------------------------------------------------------

   /**
    * Returns the descriptor corresponding to this Java type.
    */
   @Nonnull
   public final String getDescriptor() {
      StringBuilder buf = new StringBuilder();
      getDescriptor(buf);
      return buf.toString();
   }

   /**
    * Appends the descriptor corresponding to this Java type to the given string buffer.
    *
    * @param typeDesc the string builder to which the descriptor must be appended
    */
   abstract void getDescriptor(@Nonnull StringBuilder typeDesc);

   // -------------------------------------------------------------------------------------------------------
   // Direct conversion from classes to type descriptors, and vice-versa, without intermediate JavaType objects
   // -------------------------------------------------------------------------------------------------------

   /**
    * Returns the internal name of the given class. The internal name of a class is its fully qualified name, as
    * returned by Class.getName(), where '.' are replaced by '/'.
    *
    * @param aClass an object or array class
    */
   @Nonnull
   public static String getInternalName(@Nonnull Class<?> aClass) {
      return aClass.getName().replace('.', '/');
   }

   /**
    * Returns the descriptor corresponding to the given Java type.
    *
    * @param aClass an object class, a primitive class or an array class
    */
   @Nonnull
   public static String getDescriptor(@Nonnull Class<?> aClass) {
      StringBuilder buf = new StringBuilder();
      getDescriptor(buf, aClass);
      return buf.toString();
   }

   /**
    * Returns the descriptor corresponding to the given constructor.
    */
   @Nonnull
   public static String getConstructorDescriptor(@Nonnull Constructor<?> constructor) {
      StringBuilder buf = getMemberDescriptor(constructor.getParameterTypes());
      buf.append('V');
      return buf.toString();
   }

   @Nonnull
   private static StringBuilder getMemberDescriptor(@Nonnull Class<?>[] parameterTypes) {
      StringBuilder buf = new StringBuilder();
      buf.append('(');

      for (Class<?> parameterType : parameterTypes) {
         getDescriptor(buf, parameterType);
      }

      buf.append(')');
      return buf;
   }

   /**
    * Returns the descriptor corresponding to the given method.
    */
   @Nonnull
   public static String getMethodDescriptor(@Nonnull Method method) {
      StringBuilder buf = getMemberDescriptor(method.getParameterTypes());
      getDescriptor(buf, method.getReturnType());
      return buf.toString();
   }

   /**
    * Appends the descriptor of the given class to the given string builder.
    */
   private static void getDescriptor(@Nonnull StringBuilder buf, @Nonnull Class<?> aClass) {
      Class<?> d = aClass;

      while (true) {
         if (d.isPrimitive()) {
            char typeCode = PrimitiveType.getPrimitiveType(d).getTypeCode();
            buf.append(typeCode);
            return;
         }
         else if (d.isArray()) {
            buf.append('[');
            d = d.getComponentType();
         }
         else {
            ReferenceType.getDescriptor(buf, d);
            return;
         }
      }
   }

   // ------------------------------------------------------------------------
   // Corresponding size and opcodes
   // ------------------------------------------------------------------------

   /**
    * Returns the size of values of this type. This method must not be used for method types.
    *
    * @return the size of values of this type, i.e., 2 for <tt>long</tt> and <tt>double</tt>, 0 for <tt>void</tt> and 1
    * otherwise.
    */
   @Nonnegative
   public abstract int getSize();

   /**
    * Returns a JVM instruction opcode adapted to this Java type. This method must not be used for method types.
    *
    * @param opcode a JVM instruction opcode. This opcode must be one of ILOAD, ISTORE, IALOAD, IASTORE, IADD, ISUB,
    *               IMUL, IDIV, IREM, INEG, ISHL, ISHR, IUSHR, IAND, IOR, IXOR and IRETURN.
    * @return an opcode that is similar to the given opcode, but adapted to this Java type. For example, if this type is
    * <tt>float</tt> and <tt>opcode</tt> is IRETURN, this method returns FRETURN.
    */
   public abstract int getOpcode(int opcode);

   public abstract int getLoadOpcode();
   public abstract int getConstOpcode();

   /**
    * Returns a string representation of this type.
    *
    * @return the descriptor of this type.
    */
   @Override
   public final String toString() {
      return getDescriptor();
   }
}
