<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Университеты</title>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">

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
      background-color: #f7f9fc;
      margin: 0;
      padding: 0;
      color: #333;
      line-height: 1.6;
    }

    h1, h2 {
      text-align: center;
      color: #2c3e50;
      margin: 20px 0;
    }

    h1 {
      font-size: 36px;
      font-weight: 500;
    }

    h2 {
      font-size: 28px;
      color: #3498db;
      font-weight: 400;
    }

    /* Стилизация формы */
    form {
      max-width: 800px;
      margin: 40px auto;
      padding: 30px;
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    }

    form p {
      margin: 20px 0;
    }

    input[type="text"] {
      width: 100%;
      padding: 14px;
      margin-top: 8px;
      border: 2px solid #3498db;
      border-radius: 6px;
      font-size: 1em;
      transition: border 0.3s ease, box-shadow 0.3s ease;
    }

    input[type="text"]:focus {
      border-color: #2980b9;
      outline: none;
      box-shadow: 0 0 8px rgba(41, 128, 185, 0.5);
    }

    small {
      font-size: 0.9em;
      color: #888;
    }

    .button-container {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      margin-top: 30px;
    }

    input[type="submit"] {
      padding: 12px 30px;
      margin: 10px;
      border: none;
      border-radius: 5px;
      background-color: #3498db;
      color: white;
      font-size: 1.1em;
      cursor: pointer;
      transition: background-color 0.3s ease, transform 0.2s ease;
    }

    input[type="submit"]:hover {
      background-color: #2980b9;
      transform: translateY(-2px);
    }

    input[type="submit"]:active {
      transform: translateY(2px);
    }

    /* Стилизация информации по университетам */
    .university-info {
      max-width: 800px;
      margin: 40px auto;
      padding: 20px;
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    }

    .university-info p {
      font-size: 1.1em;
      color: #333;
      margin: 5px 0;
    }

    .university-list {
      max-width: 800px;
      margin: 30px auto;
      padding: 20px;
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    }

    .university-item {
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

    .university-item:hover {
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

    /* Адаптивность */
    @media (max-width: 768px) {
      form {
        padding: 20px;
      }

      .button-container {
        flex-direction: column;
      }

      .university-info, .university-list {
        padding: 15px;
      }
    }
  </style>
</head>
<body>

<h1>Университеты</h1>

<!-- Форма для ввода данных студента -->
<form action="" method="post" name="universityform" id="universityform">
  <p>
    <input type="text" name="id" id="id" value="" />
    <small> ID университета </small>
  </p>
  <p>
    <input type="text" name="name" id="name" value="" />
    <small> Название </small>
  </p>
  <p>
    <input type="text" name="city" id="city" value="" />
    <small> Город </small>
  </p>
  <p>
    <input type="text" name="address" id="address" value="" />
    <small> Адрес </small>
  </p>
  <p>
    <input type="text" name="year_of_creation" id="year_of_creation" value="" />
    <small> Год основания (yyyy-MM-dd) </small>
  </p>

  <div class="button-container">
    <input name="get" type="submit" id="get" value="Получить университет по номеру" />
    <input name="create" type="submit" id="create" value="Внести в базу новый университет" />
    <input name="update" type="submit" id="update" value="Обновить университет" />
    <input name="delete" type="submit" id="delete" value="Удалить университет из базы данных" />
    <input name="getAll" type="submit" id="getAll" value="Получить все университеты" />
  </div>
</form>

<h2>По ID университета:</h2>
<div class="university-info">
  <p>ID: ${id}</p>
  <p>NAME: ${name}</p>
  <p>CITY: ${city}</p>
  <p>ADDRESS: ${address}</p>
  <p>YEAR_OF_CREATION: ${year_of_creation}</p>
</div>

<div class="university-list">
  <h2>Список университетов</h2>
  <c:forEach var="university" items="${universities}">
    <div class="university-item">
      <pre><c:out value="${university.toString()}" /></pre>
    </div>
  </c:forEach>
</div>

</body>
</html>