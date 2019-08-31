package mockit;

import java.io.*;
import java.math.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

import static java.util.Arrays.*;

import org.junit.*;
import org.junit.rules.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import static mockit.internal.util.Utilities.*;

@SuppressWarnings("Since15")
public final class ExpectationsUsingReturnTypeConversionTest
{
   static class Collaborator {
      int getInt() { return -1; }
      Integer getInteger() { return -1; }
      short getShort() { return -1; }
      Short getShortWrapper() { return -1; }
      long getLong() { return -1; }
      Long getLongWrapper() { return -1L; }
      byte getByte() { return -1; }
      Byte getByteWrapper() { return -1; }
      float getFloat() { return -1.0F; }
      Float getFloatWrapper() { return -1.0F; }
      double getDouble() { return -1.0; }
      Double getDoubleWrapper() { return -1.0; }
      char getChar() { return '1'; }
      Character getCharacter() { return '1'; }
      boolean getBoolean() { return true; }
      Boolean getBooleanWrapper() { return Boolean.TRUE; }

      StringBuilder getStringBuilder() { return null; }
      InputStream getInputStream() { return null; }
      ByteArrayInputStream getByteArrayInputStream() { return null; }
      byte[] getByteArray() { return null; }
      Reader getReader() { return null; }
      StringReader getStringReader() { return null; }

      BigDecimal getBigDecimal() { return null; }
      BigInteger getBigInteger() { return null; }
      AtomicInteger getAtomicInteger() { return null; }
      AtomicLong getAtomicLong() { return null; }
   }

   @Rule public final ExpectedException thrown = ExpectedException.none();
   @Mocked Collaborator mock;

   @Test
   public void attemptToReturnValueNotCompatibleWithPrimitiveReturnType() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("String");
      thrown.expectMessage("int");

      new Expectations() {{ mock.getInt(); result = "test"; }};
   }

   @Test
   public void attemptToReturnValueNotCompatibleWithPrimitiveWrapperReturnType() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Boolean");
      thrown.expectMessage("Float");

      new Expectations() {{ mock.getFloatWrapper(); result = true; }};
   }

   @Test
   public void attemptToReturnValueNotCompatibleWithBooleanReturnType() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Integer");
      thrown.expectMessage("boolean");

      new Expectations() {{ mock.getBoolean(); result = 123; }};
   }

   @Test
   public void attemptToReturnValueNotCompatibleWithBooleanWrapperReturnType() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Character");
      thrown.expectMessage("Boolean");

      new Expectations() {{ mock.getBooleanWrapper(); result = 'a'; }};
   }

   @Test
   public void attemptToReturnValueOfReferenceTypeNotAssignableToReturnType() {
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage("Collaborator");
      thrown.expectMessage("InputStream");

      new Expectations() {{ mock.getInputStream(); result = mock; }};
   }

   @Test
   public void convertNumberValueToWiderNumericalReturnType() {
      new Expectations() {{
         mock.getShort(); result = (byte) 51;
         mock.getShortWrapper(); result = 'z';
         mock.getInt(); result = 'A';
         mock.getInteger(); result = (byte) 123;
         mock.getLong(); result = 52; result = 'b';
         mock.getLongWrapper(); result = (short) -50;
         mock.getFloat(); result = (short) 1234; result = 'a';
         mock.getFloatWrapper(); result = (byte) -123;
         mock.getDouble(); result = 54321; result = 'á';
         mock.getDoubleWrapper(); result = (short) -10203;
         mock.getChar(); result = (byte) 66;
         mock.getCharacter(); result = (byte) 48;
      }};

      assertEquals(51, mock.getShort());
      assertEquals('z', mock.getShortWrapper().shortValue());
      assertEquals(65, mock.getInt());
      assertEquals(123, mock.getInteger().intValue());
      assertEquals(52, mock.getLong());
      assertEquals('b', mock.getLong());
      assertEquals(-50, mock.getLongWrapper().longValue());
      assertEquals(1234.0F, mock.getFloat(), 0);
      assertEquals('a', mock.getFloat(), 0);
      assertEquals(-123.0F, mock.getFloatWrapper(), 0);
      assertEquals(54321.0, mock.getDouble(), 0);
      assertEquals('á', mock.getDouble(), 0);
      assertEquals(-10203.0, mock.getDoubleWrapper(), 0);
      assertEquals('B', mock.getChar());
      assertEquals('0', mock.getCharacter().charValue());
   }

   @Test
   public void convertNumberValueToNarrowerNumericalReturnTypeWhenTheActualValueFitsTheReturnType() {
      new Expectations() {{
         mock.getByte(); result = 23; result = 'C';
         mock.getByteWrapper(); result = (short) 127;
         mock.getShort(); result = 51;
         mock.getShortWrapper(); result = -50L;
         mock.getInt(); result = 123L;
         mock.getInteger(); result = 12345L;
         mock.getFloat(); result = 1234.0;
         mock.getFloatWrapper(); result = -123.45;
         mock.getChar(); result = 66;
         mock.getCharacter(); result = 48L;
      }};

      assertEquals(23, mock.getByte());
      assertEquals('C', mock.getByte());
      assertEquals(127, mock.getByteWrapper().byteValue());
      assertEquals(51, mock.getShort());
      assertEquals(-50, mock.getShortWrapper().shortValue());
      assertEquals(123, mock.getInt());
      assertEquals(12345, mock.getInteger().intValue());
      assertEquals(1234.0F, mock.getFloat(), 0);
      assertEquals(-123.45F, mock.getFloatWrapper(), 0);
      assertEquals('B', mock.getChar());
      assertEquals('0', mock.getCharacter().charValue());
   }

   @Test @SuppressWarnings({"NumericCastThatLosesPrecision", "CharUsedInArithmeticContext"})
   public void convertNumberValueToNarrowerNumericalReturnTypeWhenTheActualValueDoesNotFitTheReturnType() {
      new Expectations() {{
         mock.getByte(); result = 230;
         mock.getByteWrapper(); result = 'ç';
         mock.getShort(); result = 51000;
         mock.getShortWrapper(); result = -5000000000L;
         mock.getInt(); result = -12300000000L;
         mock.getInteger(); result = 12345678901L;
         mock.getFloat(); result = 1234543212345.678901234567890;
         mock.getFloatWrapper(); result = -1234567890.9876543210;
         mock.getChar(); result = 66000000;
         mock.getCharacter(); result = Character.MAX_VALUE + 1;
      }};

      assertEquals((byte) 230, mock.getByte());
      assertEquals((byte) 'ç', mock.getByteWrapper().byteValue());
      assertEquals((short) 51000, mock.getShort());
      assertEquals((short) -5000000000L, mock.getShortWrapper().shortValue());
      assertEquals((int) -12300000000L, mock.getInt());
      assertEquals((int) 12345678901L, mock.getInteger().intValue());
      assertEquals(1234543212345.67890123F, mock.getFloat(), 0);
      assertEquals(-1234567890.9876543210F, mock.getFloatWrapper(), 0);
      assertEquals((char) 66000000, mock.getChar());
      assertEquals(0, mock.getCharacter().charValue());
   }

   @Test
   public void convertRecordedTextualResultForMethodsWithEligibleReturnTypes() throws Exception {
      assertNull(mock.getStringBuilder());

      final String text = "Some textual value";

      new Expectations() {{
         mock.getStringBuilder(); result = text;
         mock.getInputStream(); result = text;
         mock.getByteArrayInputStream(); result = text;
         mock.getByteArray(); result = text;
         mock.getReader(); result = text;
         mock.getStringReader(); result = text;
      }};

      assertEquals(text, mock.getStringBuilder().toString());

      byte[] buf = new byte[text.getBytes().length];
      mock.getInputStream().read(buf);
      assertArrayEquals(text.getBytes(), buf);

      mock.getByteArrayInputStream().read(buf);
      assertArrayEquals(text.getBytes(), buf);

      byte[] bytes = mock.getByteArray();
      assertArrayEquals(text.getBytes(), bytes);

      char[] cbuf = new char[text.length()];
      mock.getReader().read(cbuf);
      assertArrayEquals(text.toCharArray(), cbuf);

      mock.getStringReader().read(cbuf);
      assertArrayEquals(text.toCharArray(), cbuf);
   }

   @Test
   public void convertTextualAndNumericalResultsToNumberSubtypes() {
      assertNull(mock.getBigDecimal());
      assertNull(mock.getBigInteger());
      assertNull(mock.getAtomicInteger());
      assertNull(mock.getAtomicLong());

      new Expectations() {{
         mock.getBigDecimal(); result = "1.50"; result = 123; result = 56L; result = -4.125;
         mock.getBigInteger(); result = "123"; result = 567L;
         mock.getAtomicInteger(); result = 1234;
         mock.getAtomicLong(); result = 12345L;
      }};

      assertEquals(new BigDecimal("1.50"), mock.getBigDecimal());
      assertEquals(new BigDecimal("123"), mock.getBigDecimal());
      assertEquals(new BigDecimal("56"), mock.getBigDecimal());
      assertEquals(new BigDecimal("-4.125"), mock.getBigDecimal());
      assertEquals(new BigInteger("123"), mock.getBigInteger());
      assertEquals(BigInteger.valueOf(567L), mock.getBigInteger());
      assertEquals(1234, mock.getAtomicInteger().intValue());
      assertEquals(12345L, mock.getAtomicLong().longValue());
   }

   static class Java8Collaborator {
      Optional<String> getOptionalValue() { return Optional.empty(); }
      Stream<String> getStream() { return null; }
   }

   @Test
   public void convertValueToOptionalOfValue(@Mocked final Java8Collaborator mock2) {
      assumeTrue(JAVA8);
      new Expectations() {{ mock2.getOptionalValue(); result = "Test"; }};

      Optional<String> value = mock2.getOptionalValue();

      assertTrue(value.isPresent());
      assertEquals("Test", value.get());
   }

   @Test
   public void convertSingleValueToStream(@Mocked final Java8Collaborator mock2) {
      assumeTrue(JAVA8);
      new Expectations() {{ mock2.getStream(); result = "Test"; }};

      Iterator<String> values = mock2.getStream().iterator();

      assertEquals("Test", values.next());
      assertFalse(values.hasNext());
   }

   @Test
   public void convertCollectionToStream(@Mocked final Java8Collaborator mock2) {
      assumeTrue(JAVA8);
      new Expectations() {{ mock2.getStream(); result = asList("Test", " abc "); }};

      Stream<String> values = mock2.getStream();

      assertEquals(2, values.count());
   }

   @Test
   public void convertArrayToStream(@Mocked final Java8Collaborator mock2) {
      assumeTrue(JAVA8);
      final String[] values = {"Test", " abc "};
      new Expectations() {{ mock2.getStream(); result = values; }};

      Object[] resultingValues = mock2.getStream().toArray();

      assertArrayEquals(values, resultingValues);
   }
}