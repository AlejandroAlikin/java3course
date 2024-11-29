<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>База данных университетов</title>

    <style>
        /* Общие стили страницы */
        body {
            font-family: 'Arial', sans-serif;
            background: #f0f8ff; /* Светлый фон с оттенками небесного */
            color: #333;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            text-align: center;
        }

        /* Заголовок */
        h1 {
            font-size: 3em;
            color: #4A90E2; /* Небесный синий цвет */
            margin-bottom: 40px;
            text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1); /* Легкая тень для заголовка */
        }

        /* Стиль ссылок (кнопок) */
        a {
            font-size: 1.2em;
            color: white;
            text-decoration: none;
            background-color: #4A90E2; /* Небесный синий */
            padding: 15px 30px;
            border-radius: 25px;
            margin: 10px;
            transition: all 0.3s ease;
        }

        /* Эффекты для кнопок */
        a:hover {
            background-color: #357ABD; /* Темный синий при наведении */
            transform: translateY(-5px); /* Подъем кнопки при наведении */
        }

        a:active {
            background-color: #2C6E99; /* Еще темнее при нажатии */
            transform: translateY(1px); /* Легкий эффект нажатия */
        }

        /* Адаптивность */
        @media (max-width: 600px) {
            h1 {
                font-size: 2em;
            }
            a {
                font-size: 1em;
                padding: 10px 20px;
            }
        }
    </style>
</head>
<body>

<h1>База данных университетов</h1>

<div>
    <a href="student-servlet">Студенты</a>
    <a href="university-servlet">Университеты</a>
</div>

</body>
</html>