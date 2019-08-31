package mockit.asm.classes;

import javax.annotation.*;

import mockit.asm.*;
import mockit.asm.fields.*;
import mockit.asm.jvmConstants.*;
import mockit.asm.methods.*;
import mockit.asm.types.*;

/**
 * A visitor to visit a Java class, in the following order:<br>
 * <tt>visit</tt> (<tt>visitAnnotation</tt>)* (<tt>visitInnerClass</tt> | <tt>visitField</tt> | <tt>visitMethod</tt>)* <tt>visitEnd</tt>.
 */
public class ClassVisitor extends BaseWriter
{
   protected ClassVisitor() {}

   /**
    * Visits the header of the class.
    *
    * @param version the class version
    * @param access the class's access flags (see {@link Access})
    * @param name the internal name of the class
    * @param additionalInfo additional class information
    */
   public void visit(int version, int access, @Nonnull String name, @Nonnull ClassInfo additionalInfo) {}

   /**
    * Visits information about an inner class, which is not necessarily a member of the class being visited.
    *
    * @param name      the internal name of an inner class
    * @param outerName the internal name of the class to which the inner class belongs; <tt>null</tt> for not member classes
    * @param innerName the (simple) name of the inner class inside its enclosing class; <tt>null</tt> for anonymous inner classes
    * @param access    the access flags of the inner class as originally declared in the enclosing class
    */
   public void visitInnerClass(@Nonnull String name, @Nullable String outerName, @Nullable String innerName, int access) {}

   /**
    * Visits a field of the class.
    *
    * @param access    the field's access flags (see {@link Access})
    * @param name      the field's name
    * @param desc      the field's descriptor (see {@link JavaType})
    * @param signature the field's signature; <tt>null</tt> when the field's type does not use generic types
    * @param value     the field's initial value; <tt>null</tt> when the field does not have an initial value; otherwise, must be an
    * {@link Integer}, a {@link Float}, a {@link Long}, a {@link Double} or a {@link String} (for <tt>int</tt>, <tt>float</tt>,
    * <tt>long</tt> or <tt>String</tt> fields respectively);
    * <em>this parameter is only used for static fields</em>; its value is ignored for non static fields, which must be initialized through
    * bytecode instructions in constructors or methods
    * @return a visitor to visit field annotations and attributes, or <tt>null</tt> if this class visitor is not interested in visiting
    * these annotations and attributes
    */
   @Nullable
   public FieldVisitor visitField(
      int access, @Nonnull String name, @Nonnull String desc, @Nullable String signature, @Nullable Object value) { return null; }

   /**
    * Visits a method of the class. This method <i>must</i> return a new {@link MethodVisitor} instance (or <tt>null</tt>) each time it is
    * called, i.e., it should not return a previously returned visitor.
    *
    * @param access     the method's access flags (see {@link Opcodes})
    * @param name       the method's name
    * @param desc       the method's descriptor (see {@link JavaType})
    * @param signature  the method's signature, <tt>null</tt> if the method parameters, return type and exceptions do not use generic types
    * @param exceptions the internal names of the method's exception classes
    * @return an object to visit the byte code of the method, or <tt>null</tt> if this class visitor is not interested in visiting the code
    * of this method
    */
   @Nullable
   public MethodVisitor visitMethod(
      int access, @Nonnull String name, @Nonnull String desc, @Nullable String signature, @Nullable String[] exceptions) { return null; }

   /**
    * Returns the bytecode of the class that was built with this class visitor.
    */
   public byte[] toByteArray() { return null; }
}
