:: call javac src\\main\\java\\com\\czechtutor\\Main.java
:: call java -cp src\\main\\java com.czechtutor.Main
call mvn exec:java -Dexec.mainClass="com.czechtutor.Main"