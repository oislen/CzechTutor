:: call javac src\\main\\java\\com\\czechtutor\\Main.java
:: call java -cp src\\main\\java com.czechtutor.Main
call mvnw exec:java -Dexec.mainClass="com.czechtutor.Lesson"
:: call mvnw compile test
:: call mvnw clean compile test package
:: call mvnw clean install
:: call mvnw spring-boot:run