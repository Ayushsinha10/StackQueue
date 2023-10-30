package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IDoubleStack;
import interfaces.IStack;

/**
 * Tests array collection implementation.
 */
public class TestArrayDoubleStack extends AbstractFactoryClient {

   private static final int DEFAULT_MAX_SIZE = 10;

   /**
    * Tests that the factory constructs a non-null double stack.
    */
   @Test
   public void factoryReturnsNonNullDoubleStackObject() {
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      assertNotNull(doubleStack1, "Failure: IFactory.makeDoubleStack returns null, expected non-null object");
   }

   @Test
   public void pushpop() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();

      try {
         first.push(3);
         assertEquals(first.pop(), 3);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();

      }

   }

   @Test
   public void pushpopsecond() {
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);

      IStack second = doubleStack1.getSecondStack();
      try {
         second.push(3);

         assertEquals(second.pop(), 3);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void pushpopsecond2() {
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      IStack second = doubleStack1.getSecondStack();
      try {
         second.push(3);
         first.push(18);
         first.push(10);

         assertEquals(second.pop(), 3);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void pushpopfirst2() {
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      IStack second = doubleStack1.getSecondStack();
      try {
         second.push(3);
         first.push(18);
         first.push(10);
         second.push(11);

         assertEquals(first.pop(), 10);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void pushpopmultiple() {
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      try {
         first.push(3);
         first.push(7);
         assertEquals(first.pop(), 7);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void pushpopmultiplesecond() {
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack second = doubleStack1.getSecondStack();
      try {
         second.push(3);
         second.push(7);
         assertEquals(second.pop(), 7);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void pushpopOverflowSecond() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);

      IStack second = doubleStack1.getSecondStack();
      try {
         second.push(3);
         second.push(7);
         second.push(21);
         second.push(18);
         second.push(190);
         second.push(11);

      } catch (StackOverflowException e) {
         thrown = true;
      }
      assertTrue(thrown);
   }

   @Test
   public void pushpopOverflowFirst() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      try {
         first.push(3);
         first.push(7);
         first.push(21);
         first.push(18);
         first.push(190);
         first.push(11);

      } catch (StackOverflowException e) {
         thrown = true;
      }
      assertTrue(thrown);
   }

   @Test
   public void pushpopNoOverflowBoth() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      IStack second = doubleStack1.getSecondStack();
      try {
         first.push(3);
         first.push(7);
         first.push(21);
         first.push(18);
         first.push(190);

         second.push(7);
         second.push(21);
         second.push(18);

         second.push(11);
         second.push(23);

      } catch (StackOverflowException e) {
         thrown = true;
      }
      assertFalse(thrown);
   }

   @Test
   public void pushpopOverflowFirstOdd() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(11);
      IStack first = doubleStack1.getFirstStack();
      try {
         first.push(3);
         first.push(7);
         first.push(21);
         first.push(18);
         first.push(190);
         first.push(11);

      } catch (StackOverflowException e) {
         thrown = true;
      }
      assertTrue(thrown);
   }

   @Test
   public void pushpopNoOverflowBothOdd() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(11);
      IStack first = doubleStack1.getFirstStack();
      IStack second = doubleStack1.getSecondStack();
      try {
         first.push(3);
         first.push(7);
         first.push(21);
         first.push(18);
         first.push(190);

         second.push(7);
         second.push(21);
         second.push(18);
         second.push(23);
         second.push(11);
         second.push(23);

      } catch (StackOverflowException e) {
         thrown = true;
      }
      assertFalse(thrown);
   }

   @Test
   public void pushpopOverflowBothOdd() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(11);
      IStack first = doubleStack1.getFirstStack();
      IStack second = doubleStack1.getSecondStack();
      try {
         first.push(3);
         first.push(7);
         first.push(21);
         first.push(18);
         first.push(190);
         first.push(80);

         second.push(21);
         second.push(18);
         second.push(23);
         second.push(11);
         second.push(23);

      } catch (StackOverflowException e) {
         thrown = true;
      }
      assertTrue(thrown);
   }

   @Test
   public void popEmpty() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();

      try {
         first.pop();
      }

      catch (StackEmptyException e) {
         thrown = true;

      }
      assertTrue(thrown);

   }

   @Test
   public void popEmptySecond() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack second = doubleStack1.getSecondStack();

      try {
        second.pop();
      }

      catch (StackEmptyException e) {
         thrown = true;

      }
      assertTrue(thrown);

   }

   @Test
   public void popEmptySecondFirstNot() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack second = doubleStack1.getSecondStack();
      IStack first = doubleStack1.getFirstStack();

      try {
         first.push(3);
        second.pop();
      }

      catch (StackEmptyException e) {
         thrown = true;

      } catch (StackOverflowException e) {

      }
      assertTrue(thrown);

   }

   @Test
   public void popEmptyFirstSecondNot() {
      boolean thrown = false;
      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack second = doubleStack1.getSecondStack();
      IStack first = doubleStack1.getFirstStack();

      try {
         second.push(3);
         first.pop();
      }

      catch (StackEmptyException e) {
         thrown = true;

      } catch (StackOverflowException e) {

      }
      assertTrue(thrown);

   }

   @Test
   public void pushpushpopop() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();

      try {
         first.push(3);
         first.push(7);
         assertEquals(first.pop(), 7);
         assertEquals(first.pop(), 3);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();

      }

   }
  @Test
   public void pushpushpopopSecond() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getSecondStack();

      try {
         first.push(3);
         first.push(7);
         assertEquals(first.pop(), 7);
         assertEquals(first.pop(), 3);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();

      }

   }

   @Test
   public void size() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();

      try {
         first.push(3);
         first.push(7);

         assertEquals(first.size(), 2);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      }

   }

   @Test
   public void size2() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      IStack second = doubleStack1.getSecondStack();

      try {
         first.push(3);
         first.push(7);
         second.push(13);

         assertEquals(first.size(), 2);
         assertEquals(second.size(), 1);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      }

   }

   @Test
   public void isEmpty() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      IStack second = doubleStack1.getSecondStack();
      assertTrue(first.isEmpty());
      assertTrue(second.isEmpty());

   }

   @Test
   public void clear() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      IStack second = doubleStack1.getSecondStack();

      try {
         first.push(3);
         first.push(7);
         second.push(13);

         assertFalse(first.isEmpty());
         assertFalse(second.isEmpty());
         first.clear();
         assertTrue(first.isEmpty());
         second.clear();
         assertTrue(second.isEmpty());
      } catch (StackOverflowException e) {
         e.printStackTrace();
      }

   }

   @Test
   public void pushpushpopopsize() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();

      try {
         first.push(3);
         first.push(7);
         assertEquals(first.size(), 2);
         assertEquals(first.pop(), 7);
         assertEquals(first.size(), 1);
         assertEquals(first.pop(), 3);
         assertEquals(first.size(), 0);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();

      }

   }

   @Test
   public void pushpushtoptopsize() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();

      try {
         first.push(3);
         first.push(7);
         assertEquals(first.size(), 2);
         assertEquals(first.top(), 7);
         assertEquals(first.size(), 2);
         assertEquals(first.top(), 7);
         assertEquals(first.size(), 2);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();

      }

   }

   @Test
   public void pushpushtotopsizeSecond() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack second = doubleStack1.getSecondStack();

      try {
         second.push(3);
         second.push(7);
         assertEquals(second.size(), 2);
         assertEquals(second.top(), 7);
         assertEquals(second.size(), 2);
         assertEquals(second.top(), 7);
         assertEquals(second.size(), 2);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();

      }

   }

   @Test
   public void pushpopString() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();

      try {
         first.push("Test");
         assertEquals(first.pop(), "Test");
      } catch (StackOverflowException e) {
         e.printStackTrace();
      } catch (StackEmptyException e) {
         e.printStackTrace();

      }

   }

   @Test
   public void pushpopNull() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      boolean thrown = false;
      try {
         first.push(null);

      } catch (StackOverflowException e) {
         e.printStackTrace();
      }

      catch (NullPointerException e) {
         thrown = true;
      }
      assertTrue(thrown);

   }
   @Test
   public void clear2() {

      IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack first = doubleStack1.getFirstStack();
      IStack second = doubleStack1.getSecondStack();

      try {
         first.push(3);
         first.push(7);
         first.push(23);
         first.push(34);
         first.push(22);
         second.push(13);
         second.push(34);
         second.push(55);
         second.push(88);
         second.push(11);
         assertFalse(first.isEmpty());
         assertFalse(second.isEmpty());
         first.clear();
         assertTrue(first.isEmpty());
         assertFalse(second.isEmpty());
         second.clear();
         assertTrue(second.isEmpty());
      } catch (StackOverflowException e) {
         e.printStackTrace();
      }

   }

}
