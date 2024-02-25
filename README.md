<h1 align="center">Проект практикума</h1>
<h3 align="center">Ui тестирование</h3>

<p>
В проекте используется:
</p> 
<ul>
<li>
Selenium
</li>
<li>
сборщик Maven
</li>
<li>
тестовый фреймворк JUnit 5
</li>
</ul>
<p>
Тесты проверяют:
</p>
<ul>
<li>
добавление пользователя со случайным именем
</li>
<li>
проверка сортировки пользователей по имени
</li>
<li>
удаление пользователей равной или ближайшей к среднеарифмитической длине имени
</li>
</ul>  
<p>
Запуск тестов из корня проекта:
</p>
<br>
<code>mvn clean test</code>
<p>
<br>
Запуск отчета в Allure из папки ./target
</p>
<br>
<code>allure serve</code>
<br>
<br>

<p>
В папке <code>test/resources/jenkins-setting</code> находятся скрины с настройкой jenkins локально и скрины allure отчета
</p>

