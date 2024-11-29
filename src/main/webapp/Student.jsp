<%--
  Created by IntelliJ IDEA.
  User: Untamed
  Date: 20.11.2024
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Студенты</title>

    <!-- Подключение стилей и скриптов для уведомлений -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            // Проверяем, есть ли переданное сообщение об ошибке
            var successMessage = "<%= request.getAttribute("successMessage") != null ? request.getAttribute("successMessage") : "" %>";
            var errorMessage = "<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>";

            // Если есть сообщение об успехе, показываем его как успешное уведомление
            if (successMessage) {
                toastr.success(successMessage, 'Successfully');
            }

            // Если есть сообщение об ошибке, показываем его как ошибочное уведомление (красное)
            if (errorMessage) {
                toastr.error(errorMessage, 'Error');
            }
        });
    </script>

    <style>
        /* Общие стили */
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f0f3f5;
            margin: 0;
            padding: 0;
            color: #333;
            line-height: 1.6;
        }

        h1, h2 {
            text-align: center;
            color: #2c3e50;
        }

        h1 {
            font-size: 36px;
            margin-top: 50px;
        }

        h2 {
            font-size: 28px;
            margin-top: 40px;
            color: #3498db;
        }

        /* Стилизация формы */
        form {
            max-width: 700px;
            margin: 40px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        form p {
            margin: 15px 0;
        }

        input[type="text"] {
            width: 100%;
            padding: 12px;
            border: 2px solid #3498db;
            border-radius: 5px;
            font-size: 1em;
            transition: border 0.3s ease;
        }

        input[type="text"]:focus {
            border-color: #2980b9;
            outline: none;
        }

        small {
            font-size: 0.9em;
            color: #888;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            padding: 12px 30px;
            margin: 10px 5px;
            border: none;
            border-radius: 5px;
            background-color: #3498db;
            color: white;
            font-size: 1.1em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        /* Стилизация информации по студентам */
        .student-info, .student-list {
            max-width: 700px;
            margin: 30px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        .student-info p {
            font-size: 1.1em;
            color: #333;
        }

        .student-item {
            background-color: #ecf0f1;
            padding: 5px;
            margin-bottom: 12px;
            border-radius: 6px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s ease;
            font-family: 'Roboto', sans-serif;
            color: #333;
            font-size: 1.2em;
            white-space: pre-wrap;  /* Для корректного отображения строк, если их много */
        }

        .student-item:hover {
            background-color: #dfe6e9;
        }

        pre {
            margin: 0;
            padding: 0;
            font-size: 1em;
            color: #555;
            white-space: pre-wrap;
            word-wrap: break-word;
        }
    </style>
</head>
<body>

<h1>Студенты</h1>

<!-- Форма для ввода данных студента -->
<form action="" method="post" name="studentform" id="studentform">
    <p>
        <input type="text" name="id" id="id" value="" size="25" />
        <small> ID студента </small>
    </p>
    <p>
        <input type="text" name="name" id="name" value="" size="25" />
        <small> Имя </small>
    </p>
    <p>
        <input type="text" name="surname" id="surname" value="" size="25" />
        <small> Фамилия </small>
    </p>
    <p>
        <input type="text" name="patronymic" id="patronymic" value="" size="25" />
        <small> Отчество </small>
    </p>
    <p>
        <input type="text" name="birthday" id="birthday" value="" size="25" />
        <small> Дата рождения (yyyy-MM-dd) </small>
    </p>
    <p>
        <input type="text" name="directionId" id="directionId" value="" size="25" />
        <small> Номер направления </small>
    </p>

    <div class="button-container">
        <input name="get" type="submit" id="get" value="Получить студента по номеру" />
        <input name="create" type="submit" id="create" value="Внести в базу нового студента" />
        <input name="update" type="submit" id="update" value="Обновить студента" />
        <input name="delete" type="submit" id="delete" value="Удалить студента из базы данных" />
        <input name="getAll" type="submit" id="getAll" value="Получить все" />
    </div>
</form>

<!-- Информация по ID студента -->
<h2>По ID студента:</h2>
<div class="student-info">
    <p>ID: ${id}</p>
    <p>NAME: ${name}</p>
    <p>SURNAME: ${surname}</p>
    <p>PATRONYMIC: ${patronymic}</p>
    <p>BIRTHDAY: ${birthday}</p>
    <p>DIRECTION_ID: ${direction_id}</p>
</div>

<!-- Список студентов -->
<div class="student-list">
    <h2>Список студентов</h2>
    <c:forEach var="student" items="${students}">
        <div class="student-item">
            <pre><c:out value="${student.toString()}" /></pre>
        </div>
    </c:forEach>
</div>

</body>
</html>