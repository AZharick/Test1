import MitshyxLabs.Book;
import MitshyxLabs.Main;
import org.junit.jupiter.api.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

   private static long testStartTime;
   static int[] valuesForMultiplying = {9,3,27};

      @Test
   public void assertAll() {
      Book book = new Book("Slime", "Lovecraft", 6);
      Assertions.assertAll(
              () -> assertEquals("Slime", book.getName()),
              () -> assertEquals("Lovecraft", book.getAuthor()),
              () -> assertTrue(book.getPages()>0, "A book cannot have negative number of pages!")
      );
   }

   @ParameterizedTest
   @MethodSource
   public void autoParamTest(int a, int b, int expected) {
         int result = Main.multiply(a,b);
         assertEquals(expected, result);
   }

   public static Stream<Arguments> autoParamTest() {
      return Stream.of(
              Arguments.of(9,3,27),
              Arguments.of(3,3,9)
      );
   }

   @ParameterizedTest
   @CsvSource (value = {
           "5,3,15",
           "20,5,100"
    })
   public void autoParamTest2(int a, int b, int expected) {
      int result = Main.multiply(a,b);
      assertEquals(expected, result);
   }

   @ParameterizedTest
   @CsvFileSource (resources = "arguments.csv")
   public void multiplyTest(String a, String b, String expected) {
      int result = Main.multiply(Integer.parseInt(a), Integer.parseInt(b));
      assertEquals(Integer.parseInt(expected), result);
   }

   @Test
   @DisplayName("Custom name: DIVISION")
   public void testDivision() {
      final int i1 = 28;                           //подготовка исходных данных
      final int i2 = 7;
      final double expected = 4;
      final double result = Main.divide(i1, i2);   //запуск проверяемого Метода
      assertEquals(expected,result);               //обработка (сравнение) результатов

      Exception e = assertThrows(ArithmeticException.class, () -> Main.divide(1, 0));
   }

   @Test
   @Timeout(2)
   public void testLinesMatch() {
      final List<String> list1 = new ArrayList<>();
      list1.add("aaa");
      list1.add("bbb");
      assertLinesMatch(list1, Main.cloneList(list1));

      assertTimeout(Duration.ofMinutes(1), () -> Main.cloneList(list1));
   }

   @Test
   @Disabled
   public void unfinishedTest() {
      Assertions.fail("THIS TEST ISN'T FINISHED!");
   }

   //fixture setting - настройка тестового окружения
   @BeforeAll
   public static void initTests() {
      System.out.println("Initializing tests...");
   }

   @BeforeEach
   public void getDateTime() {
      String datePattern = "[dd-MMMM-yyyy]-[HH-mm-ss]";
      DateFormat d = new SimpleDateFormat(datePattern);
      Date today = Calendar.getInstance().getTime();
      System.out.println(d.format(today));

      //setting timer
      testStartTime = System.nanoTime();
   }

   @AfterAll
   public static void finishTests() {
      System.out.println("All tests completed!");
   }

   @AfterEach
   public void endTest() {
      System.out.println("This test is completed in " + (System.nanoTime()-testStartTime));
   }

}