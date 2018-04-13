package org.mozilla.javascript.tests;

import org.mozilla.javascript.ScriptRuntime;

import junit.framework.TestCase;

/**
 * The function ScriptRuntime.testUint32String is not thoroughly tested.
 * This tests the method with both exceptional and unexceptional cases.
 * @author thehans
 *
 */
public class TestUint32StringTest extends TestCase {
  public void testEmptyString() {
    assertEquals(-1L, ScriptRuntime.testUint32String(""));
  }
  
  public void testNegative() {
    assertEquals(-1L, ScriptRuntime.testUint32String("-1"));
    assertEquals(-1L, ScriptRuntime.testUint32String("-8000"));
    assertEquals(-1L, ScriptRuntime.testUint32String("-999999999999999"));
  }
  
  public void testSmall() {
    assertEquals(0L, ScriptRuntime.testUint32String("0"));
    assertEquals(21L, ScriptRuntime.testUint32String("21"));
    assertEquals(100001L, ScriptRuntime.testUint32String("100001"));
  }
  
  public void testMax() {
    assertEquals(4294967295L, ScriptRuntime.testUint32String("4294967295"));
  }
  
  public void testOverflow() {
    assertEquals(-1L, ScriptRuntime.testUint32String("4294967296"));
    assertEquals(-1L, ScriptRuntime.testUint32String("4294967297"));
    assertEquals(-1L, ScriptRuntime.testUint32String("9999999999"));
    assertEquals(-1L, ScriptRuntime.testUint32String("98765432101"));
  }
  
  public void testOctal() {
    // Octal is not accepted by this function
    assertEquals(-1L, ScriptRuntime.testUint32String("00"));
    assertEquals(-1L, ScriptRuntime.testUint32String("0235"));
    assertEquals(-1L, ScriptRuntime.testUint32String("00477736"));
  }
  
  public void testInvalidCharacters() {
    assertEquals(-1L, ScriptRuntime.testUint32String("zero"));
    assertEquals(-1L, ScriptRuntime.testUint32String("12bucklemyshoe"));
    assertEquals(-1L, ScriptRuntime.testUint32String("\uF355"));
  }
}
