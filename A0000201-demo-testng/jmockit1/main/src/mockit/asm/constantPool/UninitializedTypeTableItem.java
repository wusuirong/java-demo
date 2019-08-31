package mockit.asm.constantPool;

import javax.annotation.*;

import static mockit.asm.constantPool.TypeTableItem.SpecialType.UNINIT;

public final class UninitializedTypeTableItem extends TypeTableItem
{
   @Nonnegative int offset;

   UninitializedTypeTableItem() { type = UNINIT; }

   UninitializedTypeTableItem(@Nonnegative int index, @Nonnull UninitializedTypeTableItem item) {
      super(index, item);
      offset = item.offset;
   }

   @Nonnegative
   public int getOffset() { return offset; }

   /**
    * Sets the type and bytecode offset of this uninitialized type table item.
    *
    * @param type   the internal name to be added to the type table.
    * @param offset the bytecode offset of the NEW instruction that created the UNINITIALIZED type value.
    */
   void set(@Nonnull String type, @Nonnegative int offset) {
      typeDesc = type;
      this.offset = offset;
      setHashCode(type.hashCode() + offset);
   }

   @Override
   boolean isEqualTo(@Nonnull Item item) {
      UninitializedTypeTableItem other = (UninitializedTypeTableItem) item;
      return other.offset == offset && other.typeDesc.equals(typeDesc);
   }
}
